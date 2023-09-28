package com.itlize.korera.Service;

import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        Iterable<User> allUsers =  this.userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(User user: allUsers){
            users.add(user);
        }

        return users;
    }

    @Override
    public User getUserProfileByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    @Override
    public Boolean saveNewUser(User user) {
        String username = user.getUsername();
        user.setCreated_date(new Date());
        if(!this.userRepository.existsUserByUsername(username)) {
            try {
                this.userRepository.save(user);
            } catch (Exception e) {
                System.out.println("Error when saving new user: " + e);
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean deleteUser(String username) {
        User user = this.userRepository.findByUsername(username);
        if(user == null) return false;
        try{
            this.userRepository.delete(user);
        }catch(Exception e){
            System.out.println("Error when deleting user: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean resetPassword(String username,String passwords) {
        try {
            User user = this.userRepository.findByUsername(username);
            user.setPassword(passwords);
        }catch(Exception e){
            System.out.println("Error when resetting passwords:  "+e);
            return false;
        }
        return true;
    }
}
