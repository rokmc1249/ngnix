package com.sparta.team2project.comments.entity;

import com.sparta.team2project.comments.dto.CommentsRequestDto;
import com.sparta.team2project.commons.timestamped.TimeStamped;
import com.sparta.team2project.posts.entity.Posts;
import com.sparta.team2project.replies.entity.Replies;
import com.sparta.team2project.users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comments extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="comments_id")
    private  Long id;

    private String email;

    private String nickname;

    @Column(nullable = false, length = 500)
    private String contents;

    @ManyToOne
//  @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @OneToMany (mappedBy = "comments", orphanRemoval = true)
    private List<Replies> repliesList= new ArrayList<>();


    public Comments(CommentsRequestDto requestDto, Users users, Posts posts) {
        this.contents = requestDto.getContents();
        this.posts = posts;
        this.email = users.getEmail();
        this.nickname = users.getNickName();
    }

    public void update(CommentsRequestDto requestDto, Users users) {
        this.contents = requestDto.getContents();
        this.email = users.getEmail();
        this.nickname = users.getNickName();
    }
}