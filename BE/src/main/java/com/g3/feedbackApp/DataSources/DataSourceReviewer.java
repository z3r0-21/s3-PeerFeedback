package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Repository.IReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataSourceReviewer implements IDataSourceReviewer {

    @Autowired
    IReviewerRepository reviewerRepository;


    @Override
    public ReviewerModel getReviewerById(Long id) {
        return reviewerRepository.getReviewerModelById(id);
    }

    @Override
    public List<ReviewerModel> getReviewers() {
        return reviewerRepository.findAll();
    }


    @Override
    public boolean deleteReviewerById(Long id) {
        ReviewerModel modelToDelete = reviewerRepository.getReviewerModelById(id);
        if(modelToDelete != null){
            reviewerRepository.delete(modelToDelete);
            return reviewerRepository.getReviewerModelById(id) == null;
        }
        return false;
    }

    @Override
    public boolean addReviewer(ReviewerModel reviewerModel) {
        if (reviewerRepository.getReviewerModelById(reviewerModel.getId()) != null) {
            return false;
        }
        reviewerRepository.save(reviewerModel);
        return true;
    }

    @Override
    public boolean updateReviewer(ReviewerModel reviewer) {
        ReviewerModel old = this.getReviewerById(reviewer.getId());
        if (old == null) {
            return false;
        }
        old.setUserId(reviewer.getUserId());
        return true;
    }
}
