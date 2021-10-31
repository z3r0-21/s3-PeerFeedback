package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewerModel {

    private int connId;
    private int postId;
    private int reviewerId;

    public ReviewerModel(int connId, int postId, int reviewerId){
        this.connId = connId;
        this.postId = postId;
        this.reviewerId = reviewerId;
    }
}
