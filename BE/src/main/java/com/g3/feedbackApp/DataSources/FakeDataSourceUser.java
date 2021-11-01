package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataSourceUser implements IDataSourceUser {

    private final List<UserModel> userModels = new ArrayList<>();


    public FakeDataSourceUser(){
        UserModel user1 = new UserModel(1234567, "J", "C", "Jj",
                "j.c@fontys.com" );
        UserModel user2 = new UserModel( 8923899, "Adan", "Canakita", "Ak47",
                "a.canakita@fontys.com");
        UserModel user3 = new UserModel(9218907, "Marathon", "Iraniq", "Miami",
                "m.iraniq@fontys.com");
        UserModel user4 = new UserModel(9381890, "Donna", "Fisher", "King",
                "d.Fisher93818@fontys.com");
        UserModel user5 = new UserModel( 9472789, "Tianna", "Urumuqi", "Tiger",
                "t.Urumuqi94727@fontys.com");

        this.userModels.add(user1);
        this.userModels.add(user2);
        this.userModels.add(user3);
        this.userModels.add(user4);
        this.userModels.add(user5);
    }



    @Override
    public UserModel getUserByStudentNr(int studentNr){
        for (UserModel userModel : userModels) {
            if (userModel.getStudentNr() == studentNr)
                return userModel;
        }
        return null;
    }

    @Override
    public List<UserModel> getUserModels(){
        return this.userModels;
    }

    public UserModel getUserByEmail(String email){
        for (UserModel userModel : userModels) {
            if (userModel.getEmail() == email)
                return userModel;
        }
        return null;
    }

    @Override
    public boolean deleteUserModel(int studentNr) {
        UserModel userModel = getUserByStudentNr(studentNr);
        if (userModel == null){
            return false;
        }

        return userModels.remove(userModel);
    }

    @Override
    public boolean addUserModel(UserModel userModel) {
        if (this.getUserByStudentNr(userModel.getStudentNr()) != null){
            return false;
        }
        userModels.add(userModel);
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
