package com.sparta.team2project.profile;

import com.sparta.team2project.profile.dto.AboutMeRequestDto;
import com.sparta.team2project.users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    private Users users;

    public Profile(Users users) {
        this.users = users;
    }

    // 자기소개
    @Column(nullable = true)
    private String aboutMe;

    public void updateAboutMe(AboutMeRequestDto requestDto) {
        this.aboutMe = requestDto.getAboutMe();
    }
}

