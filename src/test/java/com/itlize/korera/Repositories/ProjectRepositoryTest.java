package com.itlize.korera.Repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.User;

@SpringBootTest
public class ProjectRepositoryTest {
  
  @Autowired
  ProjectRepository projectRepository;

  /**
   * create
   */
  @Test
  void addProject() {
    //arrange
    User user = new User("siqichen", "siqi", "chen", "password", new Date(0));
    Project project = new Project("project17", new Date(0), new Date(0), user);

    //act
    Project savedProject = projectRepository.save(project);

    //assert
    assertTrue(project.equals(savedProject));

  }

  /**
   * update
   */
  @Test
  void updateProject() {
    Project p = projectRepository.getProjectByProjectNumber("project17");
    // p.se
  }
}