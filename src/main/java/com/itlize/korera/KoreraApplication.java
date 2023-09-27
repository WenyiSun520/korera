package com.itlize.korera;

import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class KoreraApplication {

//    private static final Logger log = LoggerFactory.getLogger(KoreraApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KoreraApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(UserRepository userRepository){
//        return(args -> {
//            userRepository.save(new User("winnieee.sun@gmail.com","123456", new Date()));
//            userRepository.save(new User("siqiii@gmail.com","123456", new Date()));
//            userRepository.save(new User("markkk@gmail.com","123456", new Date()));
//            userRepository.save(new User("snappy.sun@gmail.com","123456", new Date()));
//
//            Iterable<User> users = userRepository.findAll();
//            for (User user : users) {
//               log.info(user.toString());
//            }
//
//        });
//    }

}
