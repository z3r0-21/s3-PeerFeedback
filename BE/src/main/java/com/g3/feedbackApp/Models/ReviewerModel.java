package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class ReviewerModel {
    private Long id;
    private Long postId;
    private int userId;

    public ReviewerModel(Long id, Long postId, int userId){
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerModel that = (ReviewerModel) o;
        return id == that.id && postId == that.postId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, userId);
    }
}
