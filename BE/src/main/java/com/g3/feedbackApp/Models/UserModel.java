package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserModel {


    private int studentNr;
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;

    public UserModel(int studentNr, String firstName, String lastName, String nickName, String email) {

        this.studentNr = studentNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return studentNr == userModel.studentNr && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(nickName, userModel.nickName) && Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNr, firstName, lastName, nickName, email);
    }
}
