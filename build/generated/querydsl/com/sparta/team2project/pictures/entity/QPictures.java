package com.sparta.team2project.pictures.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPictures is a Querydsl query type for Pictures
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPictures extends EntityPathBase<Pictures> {

    private static final long serialVersionUID = 803534027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPictures pictures = new QPictures("pictures");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath pictureContentType = createString("pictureContentType");

    public final NumberPath<Long> pictureSize = createNumber("pictureSize", Long.class);

    public final StringPath picturesName = createString("picturesName");

    public final StringPath picturesURL = createString("picturesURL");

    public final com.sparta.team2project.schedules.entity.QSchedules schedules;

    public QPictures(String variable) {
        this(Pictures.class, forVariable(variable), INITS);
    }

    public QPictures(Path<? extends Pictures> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPictures(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPictures(PathMetadata metadata, PathInits inits) {
        this(Pictures.class, metadata, inits);
    }

    public QPictures(Class<? extends Pictures> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedules = inits.isInitialized("schedules") ? new com.sparta.team2project.schedules.entity.QSchedules(forProperty("schedules"), inits.get("schedules")) : null;
    }

}

