package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="userTable")
public class UserModel {

    @Id
    private Long studentNr;
    @Column(name = "username")
    private String username;

    public UserModel(Long studentNr, String username) {
        this.studentNr = studentNr;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return studentNr == userModel.studentNr && Objects.equals(username, userModel.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentNr, username);
    }
}
