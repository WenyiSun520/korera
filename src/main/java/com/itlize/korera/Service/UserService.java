package com.itlize.korera.Service;

import com.itlize.korera.Entities.User;


import java.util.List;


public interface UserService {
    List<User> getAllUsers();// get all users
    User getUserProfileByUsername(String username); // get single userinformation from username(email)
    Boolean isUserExist(String username);// check if DB has the user account

    Boolean saveNewUser(User user); // save a new user info to database

    Boolean deleteUser(User user);// delete a user account

    Boolean resetPassword(String username, String passwords);// change user password


}
