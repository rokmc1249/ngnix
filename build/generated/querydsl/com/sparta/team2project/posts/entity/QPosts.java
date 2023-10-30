package com.sparta.team2project.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosts is a Querydsl query type for Posts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosts extends EntityPathBase<Posts> {

    private static final long serialVersionUID = -151744601L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosts posts = new QPosts("posts");

    public final com.sparta.team2project.commons.timestamped.QTimeStamped _super = new com.sparta.team2project.commons.timestamped.QTimeStamped(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeNum = createNumber("likeNum", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<PostCategory> postCategory = createEnum("postCategory", PostCategory.class);

    public final ListPath<PostsPictures, QPostsPictures> postsPicturesList = this.<PostsPictures, QPostsPictures>createList("postsPicturesList", PostsPictures.class, QPostsPictures.class, PathInits.DIRECT2);

    public final StringPath subTitle = createString("subTitle");

    public final StringPath title = createString("title");

    public final com.sparta.team2project.users.QUsers users;

    public final NumberPath<Integer> viewNum = createNumber("viewNum", Integer.class);

    public QPosts(String variable) {
        this(Posts.class, forVariable(variable), INITS);
    }

    public QPosts(Path<? extends Posts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosts(PathMetadata metadata, PathInits inits) {
        this(Posts.class, metadata, inits);
    }

    public QPosts(Class<? extends Posts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.sparta.team2project.users.QUsers(forProperty("users")) : null;
    }

}

