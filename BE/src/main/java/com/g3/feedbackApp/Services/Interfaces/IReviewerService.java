package com.g3.feedbackApp.Services.Interfaces;


import com.g3.feedbackApp.Models.ReviewerModel;

public interface IReviewerService {
        public ReviewerModel getReviewerById(int id);
        public boolean deleteReviewerById(int id);
        public boolean addReviewer(ReviewerModel reviewer);
        public boolean updateReviewer(ReviewerModel reviewer);
}
