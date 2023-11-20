package com.sparta.team2project.notify;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class NotifyDto{
    private final String id;
    private final String name;
    private final String content;
    private final String notificationType;
    private final Boolean isRead;
    private final LocalDateTime createdAt;


    public NotifyDto(Notify notify) {
        this.id = Long.toString(notify.getId());
        this.name = notify.getReceiver().getNickName();
        this.content = notify.getContent();
        this.notificationType = notify.getNotificationType();
        this.isRead = notify.getIsRead();
        this.createdAt = notify.getCreatedAt();
    }
}
