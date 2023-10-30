package com.sparta.team2project.comments.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComments is a Querydsl query type for Comments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComments extends EntityPathBase<Comments> {

    private static final long serialVersionUID = -446247863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComments comments = new QComments("comments");

    public final com.sparta.team2project.commons.timestamped.QTimeStamped _super = new com.sparta.team2project.commons.timestamped.QTimeStamped(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public final com.sparta.team2project.posts.entity.QPosts posts;

    public final ListPath<com.sparta.team2project.replies.entity.Replies, com.sparta.team2project.replies.entity.QReplies> repliesList = this.<com.sparta.team2project.replies.entity.Replies, com.sparta.team2project.replies.entity.QReplies>createList("repliesList", com.sparta.team2project.replies.entity.Replies.class, com.sparta.team2project.replies.entity.QReplies.class, PathInits.DIRECT2);

    public QComments(String variable) {
        this(Comments.class, forVariable(variable), INITS);
    }

    public QComments(Path<? extends Comments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComments(PathMetadata metadata, PathInits inits) {
        this(Comments.class, metadata, inits);
    }

    public QComments(Class<? extends Comments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new com.sparta.team2project.posts.entity.QPosts(forProperty("posts"), inits.get("posts")) : null;
    }

}

