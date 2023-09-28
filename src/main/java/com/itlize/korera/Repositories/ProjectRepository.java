package com.itlize.korera.Repositories;


import com.itlize.korera.Entities.Project;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project, Long> {


    // find project by name
    Project getProjectByProjectNumber(String projectName);


    
}
