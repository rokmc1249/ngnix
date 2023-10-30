package com.sparta.team2project.tags.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTags is a Querydsl query type for Tags
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTags extends EntityPathBase<Tags> {

    private static final long serialVersionUID = 1316142483L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTags tags = new QTags("tags");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.team2project.posts.entity.QPosts posts;

    public final StringPath purpose = createString("purpose");

    public QTags(String variable) {
        this(Tags.class, forVariable(variable), INITS);
    }

    public QTags(Path<? extends Tags> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTags(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTags(PathMetadata metadata, PathInits inits) {
        this(Tags.class, metadata, inits);
    }

    public QTags(Class<? extends Tags> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new com.sparta.team2project.posts.entity.QPosts(forProperty("posts"), inits.get("posts")) : null;
    }

}

