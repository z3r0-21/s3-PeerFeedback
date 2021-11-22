package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    IDataSourceUser userData;

    public UserService(IDataSourceUser userData) {
        this.userData = userData;
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
