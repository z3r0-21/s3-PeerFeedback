package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Services.Interfaces.IReviewerService;

public class ReviewerService implements IReviewerService {
    IDataSourceReviewer reviewerData;

    public ReviewerService(IDataSourceReviewer reviewerData) {
        this.reviewerData = reviewerData;
    }

    @Override
    public ReviewerModel getReviewerById(int id) {
        return this.reviewerData.getReviewerById(id);
    }


    @Override
    public boolean deleteReviewerById(int id) {
        return this.reviewerData.deleteReviewerById(id);
    }

    @Override
    public boolean addReviewer(ReviewerModel userModel) {
        return this.reviewerData.addReviewer(userModel);
    }

    @Override
    public boolean updateReviewer(ReviewerModel reviewer) {
        return this.reviewerData.updateReviewer(reviewer);
    }
}
