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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class PostController {

    private static final String documentDirectory = System.getProperty("user.dir") + "/documents/";

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

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDTO> createNewPost(@ModelAttribute PostDTO postDTO) {
        //create the document path for the uploaded file.
        createDocumentPath(postDTO);
//        postDTO.setIdOP(0);

        PostModel modelToAdd = postConverter.convertPostDTOWithoutIdToPostModel(postDTO);
        if (postService.createPost(modelToAdd, postDTO.getFilePath(), postDTO.getReviewersIds())) {
            List<VersionModel> versionModelList = postService.getVersionsForPost(modelToAdd.getPostId());
            List<Long> reviewersIds = postService.getReviewersIdsForPost(modelToAdd.getPostId().intValue());
            PostDTO dtoToReturn = postConverter.convertPostModelToPostDTO(modelToAdd, versionModelList, reviewersIds);
            return ResponseEntity.ok().body(dtoToReturn);
        }
        return  ResponseEntity.notFound().build();
    }

    private void makeDirectoryIfNotExist() {
        File directory = new File(PostController.documentDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private void createDocumentPath(PostDTO postDTO) {
        //Create directory for images
        makeDirectoryIfNotExist();
        //create file path
        Path documentPath = Paths.get(documentDirectory,
                postDTO.getTitle().concat(".").concat(Objects.requireNonNull(FilenameUtils.getExtension(postDTO.getUploadedFile().getOriginalFilename()))));
        //assign file to filepath
        try {
            Files.write(documentPath, postDTO.getUploadedFile().getBytes());
            postDTO.setFilePath(documentPath);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
