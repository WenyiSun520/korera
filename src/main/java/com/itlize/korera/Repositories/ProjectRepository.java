package com.itlize.korera.Repositories;


import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.User;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // find project by name
    Project getProjectByProjectNumber(String projectName);
    List<Project> getAllProjectsByUserName(String userName);
    List<Project> findByUser(User user);

    
}
