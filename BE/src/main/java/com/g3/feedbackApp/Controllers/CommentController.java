package com.g3.feedbackApp.Controllers;

import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/comment")
public class CommentController {

    private ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
}
