package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.UserModel;

import java.util.List;

public interface IDataSourceUser {

    public UserModel getUserByStudentNr(Long studentNr);
    public List<UserModel> getUserModels();
    public boolean deleteUserModel(Long studentNr);
    public boolean addUserModel(UserModel userModel);
    public boolean updateUserModel(UserModel userModel);
    public UserModel getUserByEmail(String email);
}
