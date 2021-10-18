package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.Models.UserModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FakeDataSourceUser {
    @Getter
    private final List<UserModel> userModels = new ArrayList<>();

    public FakeDataSourceUser(){
        UserModel user1 = new UserModel(123, "Anny");
        UserModel user2 = new UserModel( 8923,  "Danny");
        UserModel user3 = new UserModel(9218, "Kakashi");
        UserModel user4 = new UserModel(93818, "Yannick");
        UserModel user5 = new UserModel(94727, "David");

        this.userModels.add(user1);
        this.userModels.add(user2);
        this.userModels.add(user3);
        this.userModels.add(user4);
        this.userModels.add(user5);
    }

    public UserModel getUserByPcn(int pcn){
        for (UserModel userModel : userModels) {
            if (userModel.getPcn() == pcn)
                return userModel;
        }
        return null;
    }

    public UserModel getUserByEmail(String email){
        for (UserModel userModel : userModels) {
            if (userModel.getSchoolEmail() == email)
                return userModel;
        }
        return null;
    }
    public boolean deleteUserModel(int pcn) {
        UserModel userModel = getUserByPcn(pcn);
        if (userModel == null){
            return false;
        }

        return userModels.remove(userModel);
    }

    public boolean add(UserModel userModel) {
        if (this.getUserByPcn(userModel.getPcn()) != null){
            return false;
        }
        userModels.add(userModel);
        return true;
    }

    public boolean update(UserModel userModel) {
        UserModel old = this.getUserByPcn(userModel.getPcn());
        if (old == null) {
            return false;
        }
        old.setPcn(userModel.getPcn());
        return true;
    }
}
