package com.g3.feedbackApp.Controllers;


import com.g3.feedbackApp.Models.Converters.PostConverter;
import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class PostController {

    private IPostService postService;
    private PostConverter postConverter;

    public PostController(IPostService postService) {
        this.postService = postService;
        postConverter = new PostConverter();
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO) {
        PostModel modelToAdd = postConverter.convertPostDTOToPostModel(postDTO);
        if(postService.createPost(modelToAdd, postDTO.getFilePath())){
            PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToAdd, postDTO.getFilePath());
            return ResponseEntity.ok().body(dtoToReturn);
        }
        String message = "Something went wrong, post not created";
        return new ResponseEntity(message, HttpStatus.CONFLICT);
    }
}
