package com.sparta.team2project.tripdate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTripDate is a Querydsl query type for TripDate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTripDate extends EntityPathBase<TripDate> {

    private static final long serialVersionUID = 1193638183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripDate tripDate = new QTripDate("tripDate");

    public final DatePath<java.time.LocalDate> chosenDate = createDate("chosenDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.team2project.posts.entity.QPosts posts;

    public final ListPath<com.sparta.team2project.schedules.entity.Schedules, com.sparta.team2project.schedules.entity.QSchedules> schedulesList = this.<com.sparta.team2project.schedules.entity.Schedules, com.sparta.team2project.schedules.entity.QSchedules>createList("schedulesList", com.sparta.team2project.schedules.entity.Schedules.class, com.sparta.team2project.schedules.entity.QSchedules.class, PathInits.DIRECT2);

    public QTripDate(String variable) {
        this(TripDate.class, forVariable(variable), INITS);
    }

    public QTripDate(Path<? extends TripDate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTripDate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTripDate(PathMetadata metadata, PathInits inits) {
        this(TripDate.class, metadata, inits);
    }

    public QTripDate(Class<? extends TripDate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new com.sparta.team2project.posts.entity.QPosts(forProperty("posts"), inits.get("posts")) : null;
    }

}

