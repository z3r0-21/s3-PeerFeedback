package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

    UserModel getUserModelByStudentNr(Long nr);
    UserModel getUserModelByEmail(String email);
}
