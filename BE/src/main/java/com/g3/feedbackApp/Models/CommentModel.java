package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class CommentModel {

    private Integer commentId;
    private Integer versionId;
    private String text;
    private boolean isSolution;

    public CommentModel(Integer commentId, Integer versionId, String text){
        this.commentId = commentId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }
}
