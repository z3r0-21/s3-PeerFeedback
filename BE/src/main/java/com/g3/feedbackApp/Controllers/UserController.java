package com.g3.feedbackApp.Controllers;

import com.g3.feedbackApp.Models.UserModel;
import com.g3.feedbackApp.Services.Interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")

public class UserController {
    private IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }


    @GetMapping("{studentNr}")
    public ResponseEntity<UserModel> getUserPath(@PathVariable(value = "studentNr") int studentNr) {
        UserModel userModel = userService.getUserByStudentNr((long)studentNr);

        if(userModel != null) {
            return ResponseEntity.ok().body(userModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(
            @RequestParam(value = "userId") Optional<Long> userId,
            @RequestParam(value = "postId") Optional<Long> postId) {
        List<UserModel> userModels = null;
        if(userId.isPresent()) {
            if(postId.isPresent())
            {
                userModels = userService.getAvailableUsersEditPost(postId.get(), userId.get());
            }
            else {
                userModels = userService.getAvailableUsersNewPost(userId.get());
            }
        }
        else{
            userModels = userService.getUserModels();
        }
        return ResponseEntity.ok().body(userModels);
    }


    @DeleteMapping("{studentNr}")
    public ResponseEntity deleteUser(@PathVariable int studentNr) {
        if(userService.deleteUserModel((long)studentNr)) {
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<UserModel> createStudent(@RequestBody UserModel userModel) {
        if (!userService.addUserModel(userModel)){
            String entity =  "The user with the student number: " + userModel.getStudentNr() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "users" + "/" + userModel.getStudentNr(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

//    @PostMapping()
//    public ResponseEntity<UserModel> createStudentByParam(@RequestParam("studentNr") int studentNr, @RequestParam("name") String name) {
//        UserModel userModel = new UserModel(studentNr, name);
//        if (!userService.addUserModel(userModel)){
//            String entity =  "The user with the studentNr: " + userModel.getPcn() + " already exists.";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        } else {
//            String url = "users" + "/" + userModel.getPcn(); // url of the created student
//            URI uri = URI.create(url);
//            return new ResponseEntity(uri,HttpStatus.CREATED);
//        }
//    }

    @PutMapping("{studentNr}")
    public ResponseEntity<UserModel> updateStudent(@PathVariable("studentNr") int studentNr,
                                                   @RequestParam("username") String username
                                                   ) {

        UserModel userModel = userService.getUserByStudentNr((long)studentNr);

        if (userModel == null){
            return new ResponseEntity("Please provide a valid student number.",HttpStatus.NOT_FOUND);
        }


        userModel.setUsername(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/identity")
    public ResponseEntity<String> getMyAccount(@RequestHeader("x-ms-client-principal-name") String name){
        return ResponseEntity.ok().body(name);
    }
}
