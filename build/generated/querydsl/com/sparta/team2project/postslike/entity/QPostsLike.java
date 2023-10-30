package com.sparta.team2project.postslike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostsLike is a Querydsl query type for PostsLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostsLike extends EntityPathBase<PostsLike> {

    private static final long serialVersionUID = 47229095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostsLike postsLike = new QPostsLike("postsLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.sparta.team2project.posts.entity.QPosts posts;

    public final com.sparta.team2project.users.QUsers users;

    public QPostsLike(String variable) {
        this(PostsLike.class, forVariable(variable), INITS);
    }

    public QPostsLike(Path<? extends PostsLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostsLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostsLike(PathMetadata metadata, PathInits inits) {
        this(PostsLike.class, metadata, inits);
    }

    public QPostsLike(Class<? extends PostsLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new com.sparta.team2project.posts.entity.QPosts(forProperty("posts"), inits.get("posts")) : null;
        this.users = inits.isInitialized("users") ? new com.sparta.team2project.users.QUsers(forProperty("users")) : null;
    }

}

