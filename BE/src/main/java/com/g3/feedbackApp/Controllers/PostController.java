package com.g3.feedbackApp.Controllers;


import com.g3.feedbackApp.Models.Converters.PostConverter;
import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.DTOS.PostDTOToReturn;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService postService;
    private final PostConverter postConverter;
    private ModelMapper modelMapper;


    public PostController(IPostService postService) {
        postConverter = new PostConverter();
        this.modelMapper = new ModelMapper();
    }

    @GetMapping()
    public ResponseEntity<List<PostDTOToReturn>> getPosts(@RequestParam(value = "idOP") Optional<Long> idOP) {
        List<PostModel> postModels = null;
        if(idOP.isPresent()) {
            postModels = postService.getMyPosts(idOP.get());
        }
        else{
            postModels = postService.getAllPosts();
        }
        return getListPostDTOToReturn(postModels);
    }

    @GetMapping("/postsToReview")
    public ResponseEntity<List<PostDTOToReturn>> getPostsToReview(@RequestParam(value = "reviewerId") Optional<Long> reviewerId) {
        List<PostModel> postModels = null;
        if(reviewerId.isPresent()) {
            postModels = postService.getPostsToReview(reviewerId.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }

        return getListPostDTOToReturn(postModels);
    }

    private ResponseEntity<List<PostDTOToReturn>> getListPostDTOToReturn(List<PostModel> postModels) {
        if(postModels != null){
            List<PostDTOToReturn> postsDTO = new ArrayList<>();
            for (PostModel postModel: postModels) {
                postsDTO.add(preparePostDTOToReturn(postModel));
            }
            return ResponseEntity.ok().body(postsDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<PostDTOToReturn> getPostWithId(@PathVariable(value = "id") Long id) {
        PostModel modelToGet = postService.getPostWithId(id);
        return ResponseEntity.ok().body(preparePostDTOToReturn(modelToGet));
    }

    @GetMapping("/version/{id}")
    public ResponseEntity<VersionModel> getVersionWithId(@PathVariable(value = "id") Long id) {
        VersionModel modelToReturn = postService.getVersionWithId(id);
        if(modelToReturn != null){
            return ResponseEntity.ok().body(modelToReturn);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PostDTOToReturn> createNewPost(@RequestBody PostDTO postDTO) {

        PostModel modelToAdd = postConverter.convertPostDTOWithoutIdToPostModel(postDTO);
        if (postService.createPost(modelToAdd, postDTO.getFilePath(), postDTO.getReviewersIds())) {
            return ResponseEntity.ok().body(preparePostDTOToReturn(modelToAdd));
        }
        return  ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PostDTOToReturn> updatePost(@RequestBody PostDTO postDTO){
        PostModel modelWithUpdates = postConverter.convertPostDTOToModelForUpdate(postDTO);
        PostModel updatedModel = postService.updatePost(modelWithUpdates, postDTO.getReviewersIds());
        if(updatedModel != null){
            return ResponseEntity.ok().body(preparePostDTOToReturn(updatedModel));
        }
        return ResponseEntity.notFound().build();
    }

    private PostDTOToReturn preparePostDTOToReturn(PostModel model){
        List<VersionModel> versionModelList = postService.getVersionsForPost(model.getPostId());
        List<Long> reviewersIds = postService.getReviewersIdsForPost(model.getPostId().intValue());
        PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(model, versionModelList, reviewersIds);
        PostDTOToReturn postDTOToReturn = modelMapper.map(dtoToReturn, PostDTOToReturn.class);
        return postDTOToReturn;
    }
}
