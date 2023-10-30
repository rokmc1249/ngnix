package com.sparta.team2project.comments.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.team2project.comments.entity.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentsResponseDto {
    private Long commentId;
    private String contents;
    private String email;
    private String nickname;
    private String checkUser;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;



    public CommentsResponseDto(Comments comments) {
        this.commentId = comments.getId();
        this.contents = comments.getContents();
        this.createAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.email = comments.getEmail();
        this.nickname = comments.getNickname();
    }

    public CommentsResponseDto(Comments comments, String checkUser) {
        this.commentId = comments.getId();
        this.contents = comments.getContents();
        this.createAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.email = comments.getEmail();
        this.nickname = comments.getNickname();
        this.checkUser = checkUser;
    }
}

