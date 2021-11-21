package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.ReviewerModel;

import java.util.List;

public interface IDataSourceReviewer {
    public ReviewerModel getReviewerById(Long id);
    public List<ReviewerModel> getReviewers();
    public boolean deleteReviewerById(Long id);
    public boolean addReviewer(ReviewerModel reviewer);
    public boolean updateReviewer(ReviewerModel reviewer);
}
