package com.itlize.korera.Controller;

import com.itlize.korera.Entities.User;
import com.itlize.korera.Entities.UserDTO;
import com.itlize.korera.Service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class userController {
    private final UserServiceImpl userService;

    public userController(UserServiceImpl userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public ResponseEntity<List<User>> getAllUser(){
//        List<User> users = this.userService.getAllUsers();
//        return ResponseEntity.ok().body(users);
//    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> users = this.userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/username")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam(value="username") String username){
        return ResponseEntity.ok().body(this.userService.getUserProfileWithProject(username));

    }
    @PostMapping("/submit_user")
    public ResponseEntity<String> saveNewUser(@RequestBody User user){
        if(this.userService.saveNewUser(user)){
            return ResponseEntity.ok().body("New user successfully saved");
        }
        return ResponseEntity.status(501).body("Error when saving new user");
    }
    @DeleteMapping("/delete_user/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){

       if(this.userService.deleteUser(username)){
           return ResponseEntity.status(200).body("Delete user profile successfully: "+ username);

       }
        return ResponseEntity.status(404).body("Can't locate the user profile");
    }
}
