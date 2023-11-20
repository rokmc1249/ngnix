package com.sparta.team2project.notify;

import com.sparta.team2project.commons.timestamped.TimeStamped;
import com.sparta.team2project.users.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



















@Entity
@Table(name = "notify")
@Getter
@NoArgsConstructor
public class Notify extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String content;

    private String url;

    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    private String notificationType;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users receiver;


    public Notify(Users receiver, String notificationType, String content, String url, Boolean isRead) {
        this.receiver = receiver;
        this.notificationType = notificationType;
        this.content = content;
        this.url = url;
        this.isRead = isRead;
    }

    public void update(boolean read) {
        isRead = read;
    }
}
