package com.sparta.team2project;

import com.sparta.team2project.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    private PostsService postsService;

    @org.junit.jupiter.api.Test
    void test() {
        String keyword = "e";
        postsService.getKeywordPosts(keyword);
    }
}
