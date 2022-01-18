package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IReviewerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewerService implements IReviewerService {
    IDataSourceReviewer reviewerData;
    IDataSourceUser userData;

    public ReviewerService(IDataSourceReviewer reviewerData, IDataSourceUser userData) {
        this.reviewerData = reviewerData;
        this.userData = userData;
    }

    @Override
    public ReviewerModel getReviewerById(Long id) {
        return this.reviewerData.getReviewerById(id);
    }

    @Override
    public List<ReviewerModel> getReviewers(){ return this.reviewerData.getReviewers();}

    @Override
    public List<UserModel> getReviewersOnPost(Long postId) {
        List<ReviewerModel> reviewerModels = getReviewers().stream().
                filter(reviewerModel -> reviewerModel.getPostId().equals(postId)).
                collect(Collectors.toList());
        List<UserModel> userModels = new ArrayList<>();
        for (ReviewerModel reviewerModel:reviewerModels){
            userModels.add(userData.getUserByStudentNr(reviewerModel.getUserId()));
        }
        return userModels;
    }


    @Override
    public boolean deleteReviewerById(Long id) {
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

    @Override
    @Transactional
    public void deleteAllReviewersByPostId(Long postId) {
        reviewerData.deleteAllReviewersByPostId(postId);
    }
}
