package com.g3.feedbackApp.Models.DTOS;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
public class CommentDTO {

    private Integer commentId;
    private Integer versionId;
    private String text;
    private boolean isSolution;

    public CommentDTO(Integer commentId, Integer versionId, String text){
        this.commentId = commentId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }
}
