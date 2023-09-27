package com.itlize.korera.Controller;

import com.itlize.korera.Entities.User;
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

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){
        Iterable<User> allUsers =  this.userService.getAllUsers();
        List<User> users = new ArrayList<>();
        for(User user: allUsers){
            users.add(user);
        }
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/username")
    public ResponseEntity<User> getUserInfo(@RequestParam(value="username") String username){
        return ResponseEntity.ok().body(this.userService.getUserProfileByUsername(username));

    }

    @PostMapping("/submituser")
    public ResponseEntity<String> saveNewUser(@RequestBody User user){
        String username = user.getUsername();
        user.setCreated_date(new Date());
        if(!this.userService.isUserExist(username) &&  this.userService.saveNewUser(user)){
            return ResponseEntity.ok().body("New user successfully saved");
        }
        return ResponseEntity.status(501).body("Error when saving new user");

    }
    @DeleteMapping("/deleteuser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
       User user = this.userService.getUserProfileByUsername(username);
       if(user == null){
           return ResponseEntity.status(404).body("Can't locate the user profile");
       }else{
           this.userService.deleteUser(user);
       }
        return ResponseEntity.status(200).body("Delete user profile successfully: "+ username);

    }


}
