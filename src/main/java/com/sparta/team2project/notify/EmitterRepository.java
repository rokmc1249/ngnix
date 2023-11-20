package com.sparta.team2project.notify;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    
    //Emitter 저장
    SseEmitter save(String emitterId, SseEmitter sseEmitter);
    // 이벤트 저장
    void saveEventCache(String emitterId, Object event);
    // 해당 유저와 관련된 모든 Emitter 찾기
    Map<String, SseEmitter> findAllEmitterStartWithByUsersId(String usersId);
    // 해당 유저와 관련된 모든 이벤트 찿기
    Map<String, Object> findAllEventCacheStartWithByUsersId(String usersId);
    // Emitter 지우기
    void deleteById(String id);
    // 해당 유저와 관련된 모든 Emitter 지우기
    void deleteAllEmitterStartWithId(String userId);
    // 해당 유저와 관련된 모든 이벤트 지우기
    void deleteAllEventCacheStartWithId(String userId);
}
