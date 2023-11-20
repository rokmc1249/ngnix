package com.sparta.team2project.posts.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.team2project.comments.entity.Comments;
import com.sparta.team2project.comments.entity.QComments;
import com.sparta.team2project.commons.exceptionhandler.CustomException;
import com.sparta.team2project.commons.exceptionhandler.ErrorCode;
import com.sparta.team2project.posts.dto.RankRequestDto;
import com.sparta.team2project.posts.entity.Posts;
import com.sparta.team2project.postslike.repository.PostsLikeRepository;
import com.sparta.team2project.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static com.sparta.team2project.posts.entity.QPosts.posts;
import static com.sparta.team2project.postslike.entity.QPostsLike.postsLike;
import static com.sparta.team2project.schedules.entity.QSchedules.schedules;
import static com.sparta.team2project.tags.entity.QTags.tags;
import static com.sparta.team2project.tripdate.entity.QTripDate.tripDate;

@RequiredArgsConstructor
public class PostsRepositoryImpl implements PostsRepositoryCustom {

    private final JPAQueryFactory factory;
    private final BooleanExpression titleNotNull = posts.title.isNotNull();
    private final BooleanExpression contentsNotNull = posts.contents.isNotNull();


    @Override
    public Page<Posts> findAllPosts(Pageable pageable) {
        List<Posts> result = factory
                .select(posts)
                .from(posts)
                .where(titleNotNull.and(contentsNotNull))
                .orderBy(posts.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = factory
                .select(posts.count()).from(posts)
                .where(titleNotNull.and(contentsNotNull))
                .fetchOne();

        return new PageImpl<>(result, pageable, totalCount);
    }
    @Override
    public Set<Posts> searchKeyword(String keyword){
        BooleanExpression titleContains = posts.title.contains(keyword);
        BooleanExpression tagsContains = tags.purpose.contains(keyword);
        BooleanExpression placeNameContains = schedules.placeName.contains(keyword);
        BooleanExpression contentsContains = schedules.contents.contains(keyword);
        Set<Posts> results= new HashSet<>(factory.selectFrom(posts)
                .leftJoin(tags).on(tags.posts.eq(posts))
                .leftJoin(tripDate).on(tripDate.posts.eq(posts))
                .leftJoin(schedules).on(schedules.tripDate.eq(tripDate))
                .where(titleNotNull.and(contentsNotNull).and
                        (titleContains.or(tagsContains).or(placeNameContains).or(contentsContains)))
                .fetch());
        return results;

    }
    @Override
    public Page<Posts> findUsersLikePosts(Users existUser,Pageable pageable){
        List<Posts> result = factory
                .select(posts)
                .from(posts)
                .leftJoin(postsLike).on(postsLike.posts.eq(posts)).fetchJoin()
                .where(postsLike.users.eq(existUser).and(postsLike.posts.id.in( JPAExpressions.select(posts.id)
                                .from(posts)
                                .where(posts.title.isNotNull().and(posts.contents.isNotNull()))
                        ))
                )
                .orderBy(postsLike.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = factory
                .select(postsLike.count()).from(postsLike)
                .where(postsLike.users.eq(existUser))
                .fetchOne();


        return new PageImpl<>(result, pageable, totalCount);


    }

    @Override
    public List<Long>  findUsersLikePostsId(Users existUser){
        return factory
                .select(posts.id)
                .from(posts)
                .leftJoin(postsLike).on(postsLike.posts.eq(posts)).fetchJoin()
                .where(postsLike.users.eq(existUser).and(postsLike.posts.id.in( JPAExpressions.select(posts.id)
                                .from(posts)
                                .where(posts.title.isNotNull().and(posts.contents.isNotNull()))
                        ))
                )
                .orderBy(postsLike.id.desc())
                .fetch();
    }

    @Override
    public List<RankRequestDto> findRank(){
        QComments comments = QComments.comments;
           List<Tuple> results = factory
                .select(posts,comments.posts.id.count())
                .from(posts)
                .leftJoin(comments).on(comments.posts.id.eq(posts.id)).fetchJoin()
                .where(titleNotNull.and(contentsNotNull))
                   .groupBy(posts.id)
                .orderBy(posts.likeNum.desc())
                .orderBy(posts.createdAt.desc())
                   .limit(3)
                .fetch();

           List<RankRequestDto> postsList = new ArrayList<>();
           for(Tuple result: results) {
               Posts postEntity = result.get(0,Posts.class);
               Long num = result.get(1, Long.class);

               int commentsNum = num== null ? 0:num.intValue();

               if(postEntity==null){
                   throw new CustomException(ErrorCode.POST_NOT_EXIST);
               }
               else {

                   postsList.add(new RankRequestDto(postEntity, commentsNum));
               }
           }
           return postsList;
    }
}
