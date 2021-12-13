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
        if(postModels != null){
            List<PostDTO> postDTOs = new ArrayList<>();
            for (PostModel postModel: postModels) {
                List<VersionModel> versionModelList = postService.getVersionsForPost(postModel.getPostId());
                List<Long> reviewersIds = postService.getReviewersIdsForPost(postModel.getPostId().intValue());
                PostDTO postDtoToReturn = postConverter.convertPostModelToPostDTO(postModel, versionModelList, reviewersIds);
                postDTOs.add(postDtoToReturn);
            }
            List<PostDTOToReturn> postsDTO = modelMapper.map(postDTOs, new TypeToken<List<PostDTOToReturn>>() {}.getType());
            return ResponseEntity.ok().body(postsDTO);
        }
        return ResponseEntity.notFound().build();
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

        if(postModels != null){
            List<PostDTO> postDTOs = new ArrayList<>();
            for (PostModel postModel: postModels) {
                List<VersionModel> versionModelList = postService.getVersionsForPost(postModel.getPostId());
                List<Long> reviewersIds = postService.getReviewersIdsForPost(postModel.getPostId().intValue());
                PostDTO postDtoToReturn = postConverter.convertPostModelToPostDTO(postModel, versionModelList, reviewersIds);
                postDTOs.add(postDtoToReturn);
            }
            List<PostDTOToReturn> postsDTO = modelMapper.map(postDTOs, new TypeToken<List<PostDTOToReturn>>() {}.getType());
            return ResponseEntity.ok().body(postsDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<PostDTOToReturn> getPostWithId(@PathVariable(value = "id") Long id) {
        PostModel modelToGet = postService.getPostWithId(id);
        List<VersionModel> versionModelList = postService.getVersionsForPost(id);
        List<Long> reviewersIds = postService.getReviewersIdsForPost(id.intValue());
        PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToGet, versionModelList, reviewersIds);
        PostDTOToReturn postDTOToReturn = modelMapper.map(dtoToReturn, PostDTOToReturn.class);
        return ResponseEntity.ok().body(postDTOToReturn);
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
            List<VersionModel> versionModelList = postService.getVersionsForPost(modelToAdd.getPostId());
            List<Long> reviewersIds = postService.getReviewersIdsForPost(modelToAdd.getPostId().intValue());
            PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToAdd, versionModelList, reviewersIds);
            PostDTOToReturn postDTOToReturn = modelMapper.map(dtoToReturn, PostDTOToReturn.class);
            return ResponseEntity.ok().body(postDTOToReturn);
        }
        return  ResponseEntity.notFound().build();
    }
}
