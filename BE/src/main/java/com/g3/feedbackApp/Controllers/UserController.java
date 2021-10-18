package com.g3.feedbackApp.Controllers;

import com.g3.feedbackApp.DataSources.FakeDataSourceUser;
import com.g3.feedbackApp.Models.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")

public class UserController {
    private static final FakeDataSourceUser fakeDataSourceUser = new FakeDataSourceUser();



    @GetMapping("{pcn}")
    public ResponseEntity<UserModel> getUserPath(@PathVariable(value = "pcn") int pcn) {
        UserModel userModel = fakeDataSourceUser.getUserByPcn(pcn);

        if(userModel != null) {
            return ResponseEntity.ok().body(userModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("{Email}")
//    public ResponseEntity<UserModel> getUserPathByEmail(@PathVariable(value = "Email") String email) {
//        UserModel userModel = fakeDataSourceUser.getUserByEmail(email);
//
//        if(userModel != null) {
//            return ResponseEntity.ok().body(userModel);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {

        List<UserModel> userModels = fakeDataSourceUser.getUserModels();

        if(userModels != null) {
            return ResponseEntity.ok().body(userModels);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pcn}")
    public ResponseEntity deletePost(@PathVariable int pcn) {
        fakeDataSourceUser.deleteUserModel(pcn);
        return ResponseEntity.ok().build();

    }

//    @PostMapping()
//    public ResponseEntity<UserModel> createStudent(@RequestBody UserModel userModel) {
//        if (!fakeDataSourceUser.add(userModel)){
//            String entity =  "The user with the pcn: " + userModel.getPcn() + " already exists.";
//            return new ResponseEntity(entity, HttpStatus.CONFLICT);
//        } else {
//            String url = "users" + "/" + userModel.getPcn(); // url of the created student
//            URI uri = URI.create(url);
//            return new ResponseEntity(uri,HttpStatus.CREATED);
//        }
//
//    }

    @PostMapping()
    public ResponseEntity<UserModel> createStudentByParam(@RequestParam("pcn") int pcn, @RequestParam("name") String name) {
        UserModel userModel = new UserModel(pcn, name);
        if (!fakeDataSourceUser.add(userModel)){
            String entity =  "The user with the pcn: " + userModel.getPcn() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "users" + "/" + userModel.getPcn(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }

    @PutMapping("{pcn}")
    public ResponseEntity<UserModel> updateStudent(@PathVariable("pcn") int pcn, @RequestParam("name") String name) {

        UserModel userModel = fakeDataSourceUser.getUserByPcn(pcn);

        if (userModel == null){
            return new ResponseEntity("Please provide a valid pcn.",HttpStatus.NOT_FOUND);
        }


        userModel.setName(name);
        return ResponseEntity.noContent().build();
    }
}
