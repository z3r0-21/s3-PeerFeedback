package com.g3.feedbackApp.Services.Interfaces;


import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.UserModel;

import java.util.List;

public interface IReviewerService {
        public ReviewerModel getReviewerById(Long id);
        public List<ReviewerModel> getReviewers();
        List<UserModel>  getReviewersOnPost(Long postId);
        public boolean deleteReviewerById(Long id);
        public boolean addReviewer(ReviewerModel reviewer);
        public boolean updateReviewer(ReviewerModel reviewer);
        void deleteAllReviewersByPostId(Long postId);
}
