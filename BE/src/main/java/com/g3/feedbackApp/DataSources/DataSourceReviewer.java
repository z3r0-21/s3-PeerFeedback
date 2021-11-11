package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Repository.IReviewerRepository;
import com.g3.feedbackApp.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataSourceReviewer implements IDataSourceReviewer {

    @Autowired
    IReviewerRepository reviewerRepository;


    @Override
    public ReviewerModel getReviewerById(int id) {
        return reviewerRepository.getReviewerModelById(Long.valueOf(id));
    }

    @Override
    public List<ReviewerModel> getReviewers() {
        return reviewerRepository.findAll();
    }


    @Override
    public boolean deleteReviewerById(int id) {
        reviewerRepository.delete(reviewerRepository.getReviewerModelById(Long.valueOf(id)));

        if(reviewerRepository.getReviewerModelById(Long.valueOf(id)) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean addReviewer(ReviewerModel reviewerModel) {
        if (reviewerRepository.getReviewerModelById(reviewerModel.getId()) != null){
            return false;
        }
        reviewerRepository.save(reviewerModel);
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
