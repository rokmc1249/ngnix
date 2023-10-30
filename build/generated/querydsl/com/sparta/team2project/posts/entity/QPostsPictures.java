package com.sparta.team2project.posts.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostsPictures is a Querydsl query type for PostsPictures
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostsPictures extends EntityPathBase<PostsPictures> {

    private static final long serialVersionUID = -1913675780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostsPictures postsPictures = new QPostsPictures("postsPictures");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosts posts;

    public final StringPath postsPictureContentType = createString("postsPictureContentType");

    public final NumberPath<Long> postsPictureSize = createNumber("postsPictureSize", Long.class);

    public final StringPath postsPicturesName = createString("postsPicturesName");

    public final StringPath postsPicturesURL = createString("postsPicturesURL");

    public QPostsPictures(String variable) {
        this(PostsPictures.class, forVariable(variable), INITS);
    }

    public QPostsPictures(Path<? extends PostsPictures> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostsPictures(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostsPictures(PathMetadata metadata, PathInits inits) {
        this(PostsPictures.class, metadata, inits);
    }

    public QPostsPictures(Class<? extends PostsPictures> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.posts = inits.isInitialized("posts") ? new QPosts(forProperty("posts"), inits.get("posts")) : null;
    }

}

