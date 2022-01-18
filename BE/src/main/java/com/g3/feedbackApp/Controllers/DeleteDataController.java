package com.g3.feedbackApp.Controllers;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import com.g3.feedbackApp.Services.Interfaces.IReviewerService;
import com.g3.feedbackApp.Services.Interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/deleteData")
public class DeleteDataController {
    private IUserService userService;
    private IPostService postService;
    private IReviewerService reviewerService;
    private ICommentService commentService;

    public DeleteDataController(IUserService userService, IPostService postService, IReviewerService reviewerService, ICommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.reviewerService = reviewerService;
        this.commentService = commentService;
    }

    private void deletePostData(Long postId) {
        postService.deleteAllCommentsByPostId(postId);
        postService.deleteAllVersionsByPostId(postId);

        reviewerService.deleteAllReviewersByPostId(postId);

        postService.deletePostModel(postId);
    }


    @DeleteMapping("post/{postId}")
    public ResponseEntity deletePost (@PathVariable int postId) {
        deletePostData(((long) postId));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("user/{userId}")
    public ResponseEntity deleteUser (@PathVariable int userId) {
        List<PostModel> posts = postService.getAllPostsByIdOp(((long) userId));
        for (int i = 0; i < posts.size(); i++) {
            deletePostData(posts.get(i).getPostId());    
        }
        userService.deleteUserModel(((long) userId));
        return ResponseEntity.ok().build();
    }
}
