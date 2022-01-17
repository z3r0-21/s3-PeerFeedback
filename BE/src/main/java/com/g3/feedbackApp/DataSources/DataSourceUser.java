package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Repository.IPostRepository;
import com.g3.feedbackApp.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IPostRepository postRepository;


    @Override
    public UserModel getUserByStudentNr(Long studentNr){
        return userRepository.getUserModelByStudentNr(studentNr);
    }

    @Override
    public List<UserModel> getUserModels(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteUserModel(Long studentNr) {
        UserModel user = userRepository.getUserModelByStudentNr(studentNr);
        if (user == null){
            return false;
        }
        int idop = studentNr.intValue();
        postRepository.deleteAllByIdOP(idop);
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean addUserModel(UserModel userModel) {
        Long studentNr = userModel.getStudentNr();
        userModel.setStudentNr(studentNr);
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
        userRepository.save(old);
        return true;
    }
}
