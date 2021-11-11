package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository userRepository;


    @Override
    public UserModel getUserByStudentNr(int studentNr){
        return userRepository.getUserModelByStudentNr(studentNr);
    }

    @Override
    public List<UserModel> getUserModels(){
        return userRepository.findAll();
    }

    public UserModel getUserByEmail(String email){
        return userRepository.getUserModelByEmail(email);
    }

    @Override
    public boolean deleteUserModel(int studentNr) {
        UserModel user = userRepository.getUserModelByStudentNr(studentNr);
        if (user == null){
            return false;
        }

        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean addUserModel(UserModel userModel) {
        userRepository.save(userModel);
        return true;
    }

    @Override
    public boolean updateUserModel(UserModel userModel) {
        UserModel old = this.getUserByStudentNr(userModel.getStudentNr());
        if (old == null) {
            return false;
        }
        old.setStudentNr(userModel.getStudentNr());
        return true;
    }
}
