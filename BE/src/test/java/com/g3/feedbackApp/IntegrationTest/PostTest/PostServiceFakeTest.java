package com.g3.feedbackApp.IntegrationTest.PostTest;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.StyledEditorKit;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
public class PostServiceFakeTest {

    @Autowired
    private IPostService postService;

    PostModel postModel;

    @BeforeEach
    void setUp(){
        postModel = new PostModel(1,"First Post", "code", "my first code post", LocalDate.now(), null, "");
    }


    @Test
    public void getPostModelWithIdSuccessfulTest(){
        //create list of reviewers to perform create post method
        List<Long> reviewersIds = new ArrayList<>();
        reviewersIds.add(1L);

        postService.createPost(postModel, reviewersIds);

        assertEquals(postModel, postService.getPostWithId(1L));
    }

    @Test
    public void getPostModelWithIdFailTest(){
        PostModel postModel = postService.getPostWithId(100L);
        assertNull(postModel);
    }

    @Test
    void createPostSuccessfulTest(){
        Path path = Path.of("");
        ArrayList<Long> reviewerIds = new ArrayList<>(Arrays.asList(1L,2L,3L));

        postService.createPost(postModel, reviewerIds);

        assertEquals(postModel, postService.getPostWithId(1L));

    }

    @Test
    void createPostInvalidPostObjectTest(){
        PostModel postModel = new PostModel(1,"", "code", "my first code post", LocalDate.now(), null, "");
        ArrayList<Long> reviewerIds = new ArrayList<>(Arrays.asList(1L,2L,3L));

        Boolean resultInvalidTitle = postService.createPost(postModel, reviewerIds);

        assertFalse(resultInvalidTitle);
    }

    //BELOW TEST METHOD SUCCEEDS BUT CRASHES PIPELINE
//    @Test
//    void getVersionWithIdSuccessFul(){
//        //create new version for postModel from setUp
//        postService.createVersion(postModel.getPostId(), Path.of(""));
//        //Get version
//        VersionModel version = postService.getVersionWithId(1L);
//
//        //Check if post id is same as post object in setUp method
//        assertEquals(postModel.getPostId(), version.getPostId());
//    }

    @Test
    void getVersionForPostId(){
        //create new version for postModel from setUp
        postService.createVersion(postModel.getPostId(), postModel.getFileLink());
        //Get versions for postModel in setUp method
        List<VersionModel> versionList = postService.getVersionsForPost(postModel.getPostId());

        assertEquals(postModel.getPostId(), versionList.get(0).getPostId());
    }
}
