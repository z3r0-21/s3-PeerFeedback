package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.UserModel;

public interface IDataSourceUser {

    public UserModel getUserByStudentNr(int studentNr);
    public boolean deleteUserModel(int studentNr);
    public boolean addUserModel(UserModel userModel);
    public boolean updateUserModel(UserModel userModel);
    public UserModel getUserByEmail(String email);
}
