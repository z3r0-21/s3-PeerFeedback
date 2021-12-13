package com.g3.feedbackApp.Controllers;


import com.g3.feedbackApp.Models.Converters.PostConverter;
import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<PostDTO>> getPosts(@RequestParam(value = "idOP") Optional<Long> idOP) {
        List<PostModel> postModels = null;
        if(idOP.isPresent()) {
            postModels = postService.getMyPosts(idOP.get());
        }
        else{
            postModels = postService.getAllPosts();
        }

        if(postModels != null){
            List<PostDTO> postDTOs = modelMapper.map(postModels, new TypeToken<List<PostDTO>>() {}.getType());
            return ResponseEntity.ok().body(postDTOs);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/postsToReview")
    public ResponseEntity<List<PostDTO>> getPostsToReview(@RequestParam(value = "reviewerId") Optional<Long> reviewerId) {
        List<PostModel> postModels = null;
        if(reviewerId.isPresent()) {
            postModels = postService.getPostsToReview(reviewerId.get());
        }
        else{
            return ResponseEntity.badRequest().build();
        }

        if(postModels != null){
            List<PostDTO> postDTOs = modelMapper.map(postModels, new TypeToken<List<PostDTO>>() {}.getType());
            return ResponseEntity.ok().body(postDTOs);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostWithId(@PathVariable(value = "id") Long id) {
        PostModel modelToGet = postService.getPostWithId(id);
        List<VersionModel> versionModelList = postService.getVersionsForPost(id);
        List<Long> reviewersIds = postService.getReviewersIdsForPost(id.intValue());
        PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToGet, versionModelList, reviewersIds);
        return ResponseEntity.ok().body(dtoToReturn);
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
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO) {

        PostModel modelToAdd = postConverter.convertPostDTOWithoutIdToPostModel(postDTO);
        if (postService.createPost(modelToAdd, postDTO.getFilePath(), postDTO.getReviewersIds())) {
            List<VersionModel> versionModelList = postService.getVersionsForPost(modelToAdd.getPostId());
            List<Long> reviewersIds = postService.getReviewersIdsForPost(modelToAdd.getPostId().intValue());
            PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToAdd, versionModelList, reviewersIds);
            return ResponseEntity.ok().body(dtoToReturn);
        }
        return  ResponseEntity.notFound().build();
    }
}
