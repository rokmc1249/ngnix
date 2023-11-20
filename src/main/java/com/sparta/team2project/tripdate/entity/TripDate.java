package com.sparta.team2project.tripdate.entity;

import com.sparta.team2project.posts.dto.TripDateOnlyRequestDto;
import com.sparta.team2project.posts.entity.Posts;
import com.sparta.team2project.schedules.entity.Schedules;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tripDate")
@Getter
@NoArgsConstructor
public class TripDate {
    // 여행날짜 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate chosenDate;


    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "posts_id",nullable = false)
    private Posts posts;

    @OneToMany(mappedBy = "tripDate", cascade = {CascadeType.REMOVE})
    private List<Schedules> schedulesList = new ArrayList<>();

    public TripDate(TripDateOnlyRequestDto tripDateRequestDto, Posts posts) {
        this.chosenDate = tripDateRequestDto.getChosenDate();
        this.posts = posts;

//        this.subTitle = tripDateRequestDto.getSubTitle();
        //this.schedulesList = tripDateRequestDto.getSchedulesList();


    }

    public void updateTripDate(TripDateOnlyRequestDto tripDateOnlyRequestDto){
        this.chosenDate = tripDateOnlyRequestDto.getChosenDate();

//        this.subTitle = tripDateOnlyRequestDto.getSubTitle();

    }
}
