package com.itlize.korera.Repositories;


import com.itlize.korera.Entities.Project;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project, Long> {

    //add Resource to Project


    //getProjectById(Long projectId)
//    Project get(Long projectId);

    //getProjectByUserId(Long userId)

    //update Resource in Project(Long resourceId, Project project)

    //delete Resource in Project(Long resourceId, Long projectId)

    //delete Project in User
}
