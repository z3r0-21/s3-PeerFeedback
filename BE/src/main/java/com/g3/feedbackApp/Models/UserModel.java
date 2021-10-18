package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class UserModel {
    @Getter
    @Setter
    private int Pcn;
    @Getter
    @Setter
    private String Name;
    @Getter
    @Setter
    private String SchoolEmail;

    public UserModel(int Pcn, String Name){
        this.Pcn = Pcn;
        this.Name = Name;
        this.SchoolEmail = String.valueOf(Pcn) + "@fontys.com";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Pcn == userModel.Pcn && Objects.equals(Name, userModel.Name) && Objects.equals(SchoolEmail, userModel.SchoolEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Pcn, Name, SchoolEmail);
    }
}
