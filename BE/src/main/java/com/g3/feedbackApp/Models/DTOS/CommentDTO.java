package com.g3.feedbackApp.Models.DTOS;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
public class CommentDTO {

    private Long commentId;
    private Long userId;
    private Long versionId;
    private String text;
    private boolean isSolution;

    public CommentDTO(Long commentId, Long userId, Long versionId, String text){
        this.commentId = commentId;
        this.userId = userId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }
}
