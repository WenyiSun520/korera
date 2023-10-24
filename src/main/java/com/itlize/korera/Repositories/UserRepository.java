package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //GET
    List<User> findUsersByLname(String lname); // Get user list by last name
    User findByUsername(String username); // Get user by username(id)
   // user.userID, user.username, user.created_date, proj.projectId, proj.projectNumber
//    @Query("SELECT new com.itlize.korera.Entities.UserDTO(user.userID, user.username, user.created_date) " +
//            "FROM User user " +
//            "LEFT JOIN user.projects proj " +
//            "WHERE user.username = :username")
//    List<UserDTO> getUserByUserID(@Param("userId")String username);


    //Delete
    void deleteUserByUsername(String username);

    //Exist
    Boolean existsUserByUsername(String username);

}
