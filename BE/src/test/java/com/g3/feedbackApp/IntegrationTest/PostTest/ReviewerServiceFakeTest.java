package com.g3.feedbackApp.IntegrationTest.PostTest;

import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Services.Interfaces.IReviewerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ReviewerServiceFakeTest {

    @Autowired
    IReviewerService reviewerService;

    ReviewerModel reviewerModel;

    @BeforeEach
    void setUp(){
        reviewerModel = new ReviewerModel(1L, 1L);
        reviewerService.addReviewer(reviewerModel);
    }

    @Test
    void getReviewerWithId(){
        ReviewerModel reviewer = reviewerService.getReviewerById(reviewerModel.getId());

        Assertions.assertEquals(reviewerModel , reviewer);
    }

    @Test
    void getReviewerWithIdInvalidId(){
        ReviewerModel reviewer = reviewerService.getReviewerById(100L);

        Assertions.assertNull(reviewer);
    }

    @Test
    void addReviewerExistingReviewer(){
        boolean addResult = reviewerService.addReviewer(reviewerModel);

        Assertions.assertFalse(addResult);
    }

    @Test
    void updateReviewerSuccessful(){
        boolean result = reviewerService.updateReviewer(reviewerModel);

        Assertions.assertTrue(result);
    }

    @Test
    void updateReviewerNotAssigned(){
        boolean result = reviewerService.updateReviewer(new ReviewerModel(1L, 100L));

        Assertions.assertFalse(result);
    }

    @Test
    void deleteReviewerSuccessful(){
        boolean result = reviewerService.deleteReviewerById(reviewerModel.getId());

        Assertions.assertTrue(result);
    }

    @Test
    void deleteReviewerNotAssigned(){
        boolean result = reviewerService.deleteReviewerById(100L);
        Assertions.assertFalse(result);
    }
}
