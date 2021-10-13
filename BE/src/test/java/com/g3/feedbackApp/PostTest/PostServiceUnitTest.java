package com.g3.feedbackApp.PostTest;

import com.g3.feedbackApp.DataSources.FakeDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import com.g3.feedbackApp.Services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PostServiceUnitTest {

    private IPostService postService;
    private IDataSourcePost dataSource;

    @BeforeEach
    public void preparePostServiceUnitTest(){
        this.dataSource = new FakeDataSourcePost();
        this.postService = new PostService(dataSource);
    }

    @Test
    public void getPostModelWithIdSuccessfulTest(){
        PostModel postModel = postService.getPostWithId(1);

        assertEquals(1, postModel.getPostId());
    }

    @Test
    public void getPostModelWithIdFailTest(){
        PostModel postModel = postService.getPostWithId(100);

        assertNull(postModel);
    }
}
