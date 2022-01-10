package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    IDataSourceUser userData;
    IDataSourcePost postData;

    public UserService(IDataSourceUser userData, IDataSourcePost postData) {
        this.userData = userData;
        this.postData = postData;
    }

    @Override
    public UserModel getUserByStudentNr(Long studentNr) {
        return userData.getUserByStudentNr(studentNr);
    }

    @Override
    public List<UserModel> getUserModels() {
        return userData.getUserModels();
    }

    @Override
    public List<UserModel> getAvailableUsersNewPost(Long userId) {
        return getUserModels().stream().filter(user -> !user.getStudentNr().equals(userId)).
                collect(Collectors.toList());
    }
    @Override
    public List<UserModel> getAvailableUsersEditPost(Long postId, Long userId) {
        List<UserModel> availableUsersEditPost = getAvailableUsersNewPost(userId);
        List<ReviewerModel> reviewersForPost = postData.getReviewersForPost(postId.intValue());
        for (int i=0;i<reviewersForPost.size();i++){
            availableUsersEditPost.remove(getUserByStudentNr(reviewersForPost.get(i).getUserId()));
        }
        return availableUsersEditPost;
    }


    @Override
    public UserModel getUserByEmail(String email) {
        return userData.getUserByEmail(email);
    }

    @Override
    public boolean deleteUserModel(Long pcn) {
        return userData.deleteUserModel(pcn);
    }

    @Override
    public boolean addUserModel(UserModel userModel) {
        return userData.addUserModel(userModel);
    }

    @Override
    public boolean updateUserModel(UserModel userModel) {
        return userData.addUserModel(userModel);
    }
}
