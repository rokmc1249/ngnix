package com.sparta.team2project.schedules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedules is a Querydsl query type for Schedules
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedules extends EntityPathBase<Schedules> {

    private static final long serialVersionUID = 1535996615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchedules schedules = new QSchedules("schedules");

    public final StringPath contents = createString("contents");

    public final NumberPath<Integer> costs = createNumber("costs", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.sparta.team2project.pictures.entity.Pictures, com.sparta.team2project.pictures.entity.QPictures> picturesList = this.<com.sparta.team2project.pictures.entity.Pictures, com.sparta.team2project.pictures.entity.QPictures>createList("picturesList", com.sparta.team2project.pictures.entity.Pictures.class, com.sparta.team2project.pictures.entity.QPictures.class, PathInits.DIRECT2);

    public final StringPath placeName = createString("placeName");

    public final StringPath referenceURL = createString("referenceURL");

    public final EnumPath<SchedulesCategory> schedulesCategory = createEnum("schedulesCategory", SchedulesCategory.class);

    public final StringPath timeSpent = createString("timeSpent");

    public final com.sparta.team2project.tripdate.entity.QTripDate tripDate;

    public QSchedules(String variable) {
        this(Schedules.class, forVariable(variable), INITS);
    }

    public QSchedules(Path<? extends Schedules> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchedules(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchedules(PathMetadata metadata, PathInits inits) {
        this(Schedules.class, metadata, inits);
    }

    public QSchedules(Class<? extends Schedules> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tripDate = inits.isInitialized("tripDate") ? new com.sparta.team2project.tripdate.entity.QTripDate(forProperty("tripDate"), inits.get("tripDate")) : null;
    }

}

