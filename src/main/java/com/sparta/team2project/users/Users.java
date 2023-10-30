package com.sparta.team2project.users;

import com.sparta.team2project.commons.entity.UserRoleEnum;
import com.sparta.team2project.profile.dto.PasswordRequestDto;
import com.sparta.team2project.profile.dto.ProfileNickNameRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;

    private Long kakaoId;

    @Column(nullable = false)
    private String profileImg;

    // 회원 가입
    public Users(String email, String nickName, String password, UserRoleEnum userRole, String profileImg) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.userRole = userRole;
        this.profileImg = profileImg;
    }

    // 생성자 추가: 프로필 정보만 갖는 경우
    public Users(String nickName, String profileImg) {
        this.nickName = nickName;
        this.profileImg = profileImg;
    }


    public Users(String email, String password, String nickName, UserRoleEnum userRole, Long kakaoId) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.userRole = userRole;
        this.kakaoId =kakaoId;
    }


    // 프로필 정보 업데이트 (닉네임)
    public void updateNickName(ProfileNickNameRequestDto requestDto) {
        if (requestDto.getUpdateNickName() != null) {
            this.nickName = requestDto.getUpdateNickName();
        }
    }
    // 프로필 정보 업데이트 (프로필이미지)
    public void updateProfileImg(String picturesURL) {
        if (picturesURL!= null) {
            this.profileImg = picturesURL;
        }
    }

    public void updatePassword(PasswordRequestDto requestDto, PasswordEncoder passwordEncoder) {
        // 비밀번호를 인코딩하여 저장
        this.password = passwordEncoder.encode(requestDto.getUpdatePassword());
    }

    public Users kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}
