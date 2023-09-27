package com.itlize.korera;

import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveOneUser(){
        User snappy = new User("snappy@gamil.com", "snappy", "sun","123456!", new Date());
        this.userRepository.save(snappy);
    }
    @Test
    public void saveAllUser(){
        User winnie = new User("winnieee.sun@gamil.com", "winnie","sun", "123456!", new Date());
        User siqi = new User("siqi@gamil.com", "siqi","chen", "123456!", new Date());
        User mark = new User("mark@gamil.com", "mark","li", "123456!", new Date());
        List<User> userList = new ArrayList<>();
        userList.add(winnie);
        userList.add(siqi);
        userList.add(mark);
        Iterable<User> allUser = userList;
        this.userRepository.saveAll(allUser);
    }
    @Test
    public void getUser(){
        Iterable<User> allUser = this.userRepository.findAll();
        allUser.forEach(System.out::println);
    }
    @Test
    public void getUserByLname(){
        Iterable<User> list = this.userRepository.findUsersByLname("sun");
        list.forEach(System.out::println);

    }
    @Test
    public void getUserByUsername(){
        User user =  this.userRepository.findByUsername("winnieee.sun@gmail.com");
        System.out.println(user);
    }
    @Test
    public void deleteUserById(){
        // get a rendom id from database
        try {
       this.userRepository.deleteById((long)402);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @Transactional
    @Test
    public void deleteUserByUserName(){
//        try {
            this.userRepository.deleteUserByUsername("siqi@gamil.com");
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }

    @Test
    public void deleteAll(){
        try {
        this.userRepository.deleteAll();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
