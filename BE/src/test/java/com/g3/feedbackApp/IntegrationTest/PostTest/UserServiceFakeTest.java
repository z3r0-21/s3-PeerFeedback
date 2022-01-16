package com.g3.feedbackApp.IntegrationTest.PostTest;

import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceFakeTest {

    @Autowired
    IUserService userService;

    UserModel user;

    @BeforeEach
    void setUp(){
        user = new UserModel(1L,"Maarten");
        userService.addUserModel(user);
    }

    @AfterEach
    void clear(){
        userService.deleteUserModel(user.getStudentNr());
    }

    @Test
    void getUserByStudentNmr(){
        UserModel model = userService.getUserByStudentNr(user.getStudentNr());

        Assertions.assertEquals(user, model);
    }

    @Test
    void deleteUserSuccessful(){
        boolean result = userService.deleteUserModel(user.getStudentNr());

        Assertions.assertTrue(result);
    }

    @Test
    void deleteUserNonExisting(){
        boolean result = userService.deleteUserModel(100L);

        Assertions.assertFalse(result);
    }

    @Test
    void updateUserSuccessful(){
        boolean result = userService.updateUserModel(user);

        Assertions.assertTrue(result);
    }

}
