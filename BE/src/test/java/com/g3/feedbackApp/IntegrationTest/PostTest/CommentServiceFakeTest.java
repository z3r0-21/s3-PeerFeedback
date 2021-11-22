package com.g3.feedbackApp.IntegrationTest.PostTest;

import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceFakeTest {

    @Autowired
    ICommentService commentService;
    @Autowired
    IPostService postService;

    CommentModel commentModel;

    @BeforeEach
    void setUp(){
        postService.createPost(new PostModel(1,"First Post", "code", "my first code post", LocalDate.now(), null), Path.of(""), new ArrayList<>());
        commentModel = new CommentModel(1L, 1L, "My first comment");
    }


    @Test
    void createCommentSuccessful(){
        CommentModel createdComment = commentService.createComment(commentModel);

        Assertions.assertNotNull(createdComment);
    }

    @Test
    void createCommentInvalidVersionId(){
        CommentModel comment = new CommentModel(1L, 500L, "Comment with invalid version");
        CommentModel modelToBeNull = commentService.createComment(comment);

        Assertions.assertNull(modelToBeNull);
    }

    @Test
    void createCommentInvalidText(){
        CommentModel comment = new CommentModel(1L, 1L, "");
        CommentModel modelToBeNull = commentService.createComment(comment);

        Assertions.assertNull(modelToBeNull);
    }
    @Test
    void getCommentWithIdSuccessFul(){
        commentService.createComment(commentModel);
        CommentModel commentToGet = commentService.getCommentWithId(commentModel.getCommentId());

        Assertions.assertEquals("My first comment", commentToGet.getText());
    }
}
