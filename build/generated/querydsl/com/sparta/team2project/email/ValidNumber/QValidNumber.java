package com.sparta.team2project.email.ValidNumber;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QValidNumber is a Querydsl query type for ValidNumber
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QValidNumber extends EntityPathBase<ValidNumber> {

    private static final long serialVersionUID = 1522907590L;

    public static final QValidNumber validNumber1 = new QValidNumber("validNumber1");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> time = createNumber("time", Long.class);

    public final NumberPath<Integer> validNumber = createNumber("validNumber", Integer.class);

    public QValidNumber(String variable) {
        super(ValidNumber.class, forVariable(variable));
    }

    public QValidNumber(Path<? extends ValidNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValidNumber(PathMetadata metadata) {
        super(ValidNumber.class, metadata);
    }

}

