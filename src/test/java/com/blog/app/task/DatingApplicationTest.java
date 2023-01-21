package com.blog.app.task;

import com.blog.app.task.controller.CommentController;
import com.blog.app.task.controller.PostController;
import com.blog.app.task.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DatingApplicationTest {

    @Autowired
    private UserController userController;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;

    @Test
    void contextLoads(){
        assertThat(userController).isNotNull();
        assertThat(postController).isNotNull();
        assertThat(commentController).isNotNull();
    }
}