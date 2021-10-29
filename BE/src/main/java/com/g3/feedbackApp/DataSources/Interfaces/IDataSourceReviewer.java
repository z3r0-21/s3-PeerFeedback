package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.ReviewerModel;

public interface IDataSourceReviewer {
    public ReviewerModel getReviewerById(int id);
    public boolean deleteReviewerById(int id);
    public boolean addReviewer(ReviewerModel reviewer);
    public boolean updateReviewer(ReviewerModel reviewer);
}
