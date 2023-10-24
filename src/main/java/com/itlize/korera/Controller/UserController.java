package com.itlize.korera.Controller;

import com.itlize.korera.DTO.UserDTO;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> users = this.userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/username")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam(value="username") String username){
        return ResponseEntity.ok().body(this.userService.getUserProfile(username));

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
