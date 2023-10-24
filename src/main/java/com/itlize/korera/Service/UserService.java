package com.itlize.korera.Service;

import com.itlize.korera.DTO.UserDTO;
import com.itlize.korera.Entities.User;


import java.util.List;


public interface UserService {
//    List<User> getAllUsers();
   List<UserDTO> getAllUsers();

   User findByUsername(String username);

    UserDTO getUserProfile(String username);
    User getUserProfileByUsername(String username); // get single userinformation from username(email)

    Boolean saveNewUser(User user); // save a new user info to database

    Boolean deleteUser(String username);// delete a user account

    Boolean resetPassword(String username, String passwords);// change user password


}
