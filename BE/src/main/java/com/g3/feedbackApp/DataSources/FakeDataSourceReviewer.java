package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataSourceReviewer {
/*
    private final List<ReviewerModel> reviewerModelList = new ArrayList<>();
    private static int reviewerConnIdCounter = 1;

    public FakeDataSourceReviewer() {
//        this.reviewerModelList.add(new ReviewerModel(1, 1, 1234567));
//        this.reviewerModelList.add(new ReviewerModel(2, 1, 2345669));

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
        if (this.getReviewerById(userModel.getId().intValue()) != null){
            return false;
        }
        userModel.setId((long) reviewerConnIdCounter);
        reviewerModelList.add(userModel);
        reviewerConnIdCounter++;
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

 */
}
