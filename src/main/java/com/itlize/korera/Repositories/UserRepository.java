package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //GET
    List<User> findUsersByLname(String lname); // Get user list by last name
    User findByUsername(String username); // Get user by username(id)

    //Delete
    void deleteUserByUsername(String username);

    //Exist
    Boolean existsUserByUsername(String username);

}
