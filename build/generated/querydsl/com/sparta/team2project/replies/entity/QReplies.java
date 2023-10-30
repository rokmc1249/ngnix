package com.sparta.team2project.replies.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReplies is a Querydsl query type for Replies
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplies extends EntityPathBase<Replies> {

    private static final long serialVersionUID = -1812944633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReplies replies = new QReplies("replies");

    public final com.sparta.team2project.commons.timestamped.QTimeStamped _super = new com.sparta.team2project.commons.timestamped.QTimeStamped(this);

    public final com.sparta.team2project.comments.entity.QComments comments;

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public QReplies(String variable) {
        this(Replies.class, forVariable(variable), INITS);
    }

    public QReplies(Path<? extends Replies> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplies(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplies(PathMetadata metadata, PathInits inits) {
        this(Replies.class, metadata, inits);
    }

    public QReplies(Class<? extends Replies> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comments = inits.isInitialized("comments") ? new com.sparta.team2project.comments.entity.QComments(forProperty("comments"), inits.get("comments")) : null;
    }

}

