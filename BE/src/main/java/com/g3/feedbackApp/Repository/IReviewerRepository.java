package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.ReviewerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewerRepository extends JpaRepository<ReviewerModel, Long> {

    ReviewerModel getReviewerModelById(Long id);
    List<ReviewerModel> getReviewerModelsByPostId(Long id);
    void deleteAllByPostId(Long postId);
}
