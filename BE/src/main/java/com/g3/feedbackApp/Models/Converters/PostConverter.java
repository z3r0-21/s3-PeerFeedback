package com.g3.feedbackApp.Models.Converters;

import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.PostModel;

public class PostConverter {

    public PostModel convertPostDTOToPostModel(PostDTO postDTO){
        return new PostModel(postDTO.getPostId(), postDTO.getIdOP(), postDTO.getTitle(), postDTO.getCategory(), postDTO.getDescription(), postDTO.getPostDate(), postDTO.getResolveDate());
    }

    public PostDTO convertPostModelToPostDTO(PostModel postModel, String filePath){
        return new PostDTO(postModel.getPostId(), postModel.getIdOP(), postModel.getTitle(), postModel.getCategory(), postModel.getDescription(), postModel.getPostDate(), postModel.getResolveDate(), filePath);
    }
}
