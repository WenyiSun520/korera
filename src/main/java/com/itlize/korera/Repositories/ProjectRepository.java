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
    Project get(Long projectId);

    //getProjectByUserId(Long userId)
    List<Project> projects(Long userId);

    //update Resource in Project(Long resourceId, Project project)
    void update(Long resourceId, Project project);

    //delete Resource in Project(Long resourceId, Long projectId)
    void delete(Long resourceId, Project project);

    //delete Project in User
    void delete(Long userId);
}
