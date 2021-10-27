package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IUserService;

public class UserService implements IUserService {

    IDataSourceUser userData;

    public UserService(IDataSourceUser userData) {
        this.userData = userData;
    }

    @Override
    public UserModel getUserByStudentNr(int studentNr) {
        return userData.getUserByStudentNr(studentNr);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userData.getUserByEmail(email);
    }

    @Override
    public boolean deleteUserModel(int pcn) {
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
