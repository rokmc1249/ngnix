package com.sparta.team2project.notify;

import com.sparta.team2project.commons.dto.MessageResponseDto;
import com.sparta.team2project.commons.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "알람 관련 API", description = "알람 관련 API입니다.")
@RequestMapping("/api")
public class NotifyController {
    private final  NotifyService notifyService;

    @Operation(summary = "서버와 연결", description = "서버와 연결해주는 API 입니다.")
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> subscribe(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return ResponseEntity.ok(notifyService.subscribe(userDetails.getUsers().getNickName(), lastEventId));
    }

    @Operation(summary = "알림 전체 조회", description = "알림 전체 조회 API 입니다.")
    @GetMapping("/notify")
    public ResponseEntity<List<NotifyDto>>getAllNotifications(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(notifyService.getAllNotifications(userDetails.getUsers()));
    }

    @Operation(summary = "알림 읽음", description = "알림 읽음 API 입니다.")
    @GetMapping("/read/{notifyId}")
    public ResponseEntity<MessageResponseDto>readNotification(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long notifyId){
        return ResponseEntity.ok(notifyService.readNotification(userDetails.getUsers(),notifyId));
    }

    @Operation(summary = "알림 삭제", description = "알림 삭제 API 입니다.")
    @DeleteMapping("/notify/{notifyId}")
    public ResponseEntity<MessageResponseDto>deleteNotification(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long notifyId ){
        return ResponseEntity.ok(notifyService.deleteNotification(userDetails.getUsers(),notifyId));
    }


}

