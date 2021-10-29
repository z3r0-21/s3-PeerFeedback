package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;

import java.util.ArrayList;
import java.util.List;

public class FakeDataSourceReviewer implements IDataSourceReviewer {

    private final List<ReviewerModel> reviewerModelList = new ArrayList<>();

    public FakeDataSourceReviewer() {
        this.reviewerModelList.add(new ReviewerModel(1, 1, 1234567));
        this.reviewerModelList.add(new ReviewerModel(1, 1, 2345669));
    }

    @Override
    public ReviewerModel getReviewerById(int id) {
        for (ReviewerModel reviewerModel : this.reviewerModelList) {
            if (reviewerModel.getUserId() == id)
                return reviewerModel;
        }
        return null;
    }

    @Override
    public List<ReviewerModel> getReviewers() {
        return this.reviewerModelList;
    }


    @Override
    public boolean deleteReviewerById(int id) {
        ReviewerModel reviewerModel = getReviewerById(id);
        if (reviewerModel == null){
            return false;
        }

        return this.reviewerModelList.remove(reviewerModel);
    }

    @Override
    public boolean addReviewer(ReviewerModel userModel) {
        if (this.getReviewerById(userModel.getUserId()) != null){
            return false;
        }
        this.reviewerModelList.add(userModel);
        return true;
    }

    @Override
    public boolean updateReviewer(ReviewerModel reviewer) {
        ReviewerModel old = this.getReviewerById(reviewer.getUserId());
        if (old == null) {
            return false;
        }
        old.setUserId(reviewer.getUserId());
        return true;
    }
}
