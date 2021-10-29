package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class ReviewerModel {
    private int connId;
    private int postId;
    private int reviewerId;

    public ReviewerModel(int connId, int postId, int reviewerId){
        this.connId = connId;
        this.postId = postId;
        this.reviewerId = reviewerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerModel that = (ReviewerModel) o;
        return connId == that.connId && postId == that.postId && reviewerId == that.reviewerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(connId, postId, reviewerId);
    }
}
