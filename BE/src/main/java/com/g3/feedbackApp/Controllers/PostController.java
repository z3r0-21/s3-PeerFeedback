package com.g3.feedbackApp.Controllers;


import com.g3.feedbackApp.Models.Converters.PostConverter;
import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class PostController {

    private static String documentDirectory = System.getProperty("user.dir") + "/documents/";

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

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDTO> createNewPost(@ModelAttribute PostDTO postDTO) {
        createDocumentPath(postDTO);

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

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private void createDocumentPath(PostDTO postDTO) {
        //Create directory for images
        makeDirectoryIfNotExist(documentDirectory);
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
