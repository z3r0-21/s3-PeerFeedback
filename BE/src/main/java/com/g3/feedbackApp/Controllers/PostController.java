package com.g3.feedbackApp.Controllers;


import com.g3.feedbackApp.Models.Converters.PostConverter;
import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class    PostController {

    private IPostService postService;
    private PostConverter postConverter;

    public PostController(IPostService postService) {
        this.postService = postService;
        postConverter = new PostConverter();
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostWithId(@PathVariable(value = "id") int id) {
        PostModel modelToGet = postService.getPostWithId(id);
        List<VersionModel> versionModelList = postService.getVersionsForPost(id);
        List<Integer> reviewersIds = postService.getReviewersIdsForPost(id);
        PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToGet, versionModelList, reviewersIds);
        return ResponseEntity.ok().body(dtoToReturn);
    }

    @GetMapping("/version/{id}")
    public ResponseEntity<VersionModel> getVersionWithId(@PathVariable(value = "id") int id) {
        VersionModel modelToReturn = postService.getVersionWithId(id);
        if(modelToReturn != null){
            return ResponseEntity.ok().body(modelToReturn);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO) {
        PostModel modelToAdd = postConverter.convertPostDTOToPostModel(postDTO);
        if (postService.createPost(modelToAdd, postDTO.getFilePath(), postDTO.getReviewersIds())) {
            List<VersionModel> versionModelList = postService.getVersionsForPost(modelToAdd.getPostId());
            List<Integer> reviewersIds = postService.getReviewersIdsForPost(modelToAdd.getPostId());
            PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToAdd, versionModelList, reviewersIds);
            return ResponseEntity.ok().body(dtoToReturn);
        }
        String message = "Something went wrong, post not created";
        return new ResponseEntity(message, HttpStatus.CONFLICT);
    }


}
