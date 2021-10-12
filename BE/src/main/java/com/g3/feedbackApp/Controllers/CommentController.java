package com.g3.feedbackApp.Controllers;

import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Models.Converters.CommentConverter;
import com.g3.feedbackApp.Models.DTOS.CommentDTO;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;




@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/comments")
public class CommentController {

    private ICommentService commentService;
    private CommentConverter commentConverter;

    public CommentController(ICommentService commentService) {

        this.commentService = commentService;
        commentConverter = new CommentConverter();
    }

    @PostMapping("/create")
    //POST at http://localhost:XXXX/comments/
    public ResponseEntity<CommentDTO> createMember(@RequestBody CommentDTO commentDTO) {
        CommentModel commentModel = commentConverter.convertCommentDTOToCommentModel(commentDTO);
        if(commentService.createComment(commentModel)){
            CommentDTO commentDTO1 = commentConverter.convertCommentModelToCommentDTO(commentModel);
            return ResponseEntity.ok().body(commentDTO1);
        }
        String message = "Something went wrong, comment not created";
        return new ResponseEntity(message, HttpStatus.CONFLICT);
    }
}