package com.sparta.team2project.notify;

import com.sparta.team2project.commons.dto.MessageResponseDto;
import com.sparta.team2project.commons.entity.UserRoleEnum;
import com.sparta.team2project.commons.exceptionhandler.CustomException;
import com.sparta.team2project.commons.exceptionhandler.ErrorCode;
import com.sparta.team2project.users.UserRepository;
import com.sparta.team2project.users.Users;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotifyService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    // SSE 연결 지속 시간 설정

    private final EmitterRepository emitterRepository;
    private final NotifyRepository notifyRepository;
    private final UserRepository userRepository;


    // [1] subscribe() 수신자의 정보와 마지막 이벤트 식별자 파라미터 받음
    public SseEmitter subscribe(String username, String lastEventId) { // (1-1)
        String emitterId = makeTimeIncludeId(username); // (1-2)
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT)); // (1-3) SseEmitter 객체 생성 및 저장
        // (1-4) SseEmitter가 완료되거나 타임아웃될 때 해당 SseEmitter를 emitterRepository에서 삭제하도록 설정
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        // (1-5) 503 에러를 방지하기 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(username);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [nickname=" + username + "]");

        // (1-6) 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, username, emitterId, emitter);
        }

        return emitter; // (1-7)
    }

    // emitterId 생성
    private String makeTimeIncludeId(String email) { // (3)
        return email + "_" + System.currentTimeMillis();
    }

    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) { // (4)
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data)
            );
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
        }
    }

    private boolean hasLostData(String lastEventId) { // (5) 미수신한 이벤트 전송
        return !lastEventId.isEmpty();
    }

    private void sendLostData(String lastEventId, String userEmail, String emitterId, SseEmitter emitter) { // (6)
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByUsersId(String.valueOf(userEmail));
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

    // [2] send()
    //@Override
    // 알림 생성 및 지정된 수신자에게 알림을 전송 하는 기능
    public void send(Users receiver, String notificationType, String content, String url) {
        Notify notify = new Notify(receiver,notificationType,content,url,false);
        notifyRepository.save(notify);// (2-1)

        String receiverNickname = receiver.getNickName(); // (2-2)
        String eventId = receiverNickname + "_" + System.currentTimeMillis(); // (2-3)
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUsersId(receiverNickname); // (2-4)
        emitters.forEach( // (2-5)
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notify);
                    sendNotification(emitter, eventId, key, new NotifyDto(notify));
                }
        );
    }
    public List<NotifyDto> getAllNotifications(Users users) { // 전체 알람 조회
        List<Notify> notifyList = notifyRepository.findByReceiverAndIsRead(users,false);

        List<NotifyDto>notifyDtoList = new ArrayList<>();
        for(Notify notify:notifyList){
            notifyDtoList.add(new NotifyDto(notify));
        }
        return notifyDtoList;
    }
    @Transactional
    public MessageResponseDto readNotification(Users users, Long id){ // 알람 읽음 표시
        Notify notify = notifyRepository.findByReceiverAndId(users,id);
        notify.update(true);
        return new MessageResponseDto("알림을 읽었습니다.", HttpServletResponse.SC_OK);
    }

    public MessageResponseDto deleteNotification(Users users, Long notifyId) { // 알람 삭제
        Notify notify = checkNotify(notifyId);

        userRepository.findByEmail(users.getEmail()).
                orElseThrow(() -> new CustomException(ErrorCode.ID_NOT_MATCH));
        checkAuthority(users,notify.getReceiver());
        notifyRepository.delete(notify);
        return new MessageResponseDto("알림을 삭제합니다.", HttpServletResponse.SC_OK);

    }
    private Notify checkNotify(Long id) {
        return notifyRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOTIFY_NOT_EXIST));
    }

    private void checkAuthority(Users existUser, Users users){
        if (!existUser.getUserRole().equals(UserRoleEnum.ADMIN)&&!existUser.getEmail().equals(users.getEmail())) {throw new CustomException(ErrorCode.NOT_ALLOWED);
        }
    }
}
