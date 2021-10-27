package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.UserModel;

public interface IUserService {
    public UserModel getUserByStudentNr(int studentNr);
    public UserModel getUserByEmail(String email);
    public boolean deleteUserModel(int studentNr);
    public boolean addUserModel(UserModel userModel);
    public boolean updateUserModel(UserModel userModel);
}
