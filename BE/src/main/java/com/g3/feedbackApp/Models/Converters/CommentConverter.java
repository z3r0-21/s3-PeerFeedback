package com.g3.feedbackApp.Models.Converters;


import com.g3.feedbackApp.Models.DTOS.CommentDTO;
import com.g3.feedbackApp.Models.CommentModel;

public class CommentConverter {

    public CommentModel convertCommentDTOToCommentModel(CommentDTO commentDTO){
        return new CommentModel(commentDTO.getCommentId(), commentDTO.getVersionId(), commentDTO.getText());
    }

    public CommentDTO convertCommentModelToCommentDTO(CommentModel commentModel){
        return new CommentDTO(commentModel.getCommentId(), commentModel.getVersionId(), commentModel.getText());
    }

}
