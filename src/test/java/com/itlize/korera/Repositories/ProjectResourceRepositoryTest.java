package com.itlize.korera.Repositories;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.ProjectResource;
import com.itlize.korera.Entities.Resource;

@SpringBootTest
public class ProjectResourceRepositoryTest {
  
  @Autowired
  ProjectResourceRepository projectResourceRepository;


  @Test
  void addProjectResource() {
    Project p = new Project();
    p.setProjectNumber("project21");
    p.setDateCreated(new Date(0));
    p.setProjectID(21L);
    
    Resource r = new Resource();
    r.setResourceID(21L);
    r.setResourceName("Resource21");


    ProjectResource pr = new ProjectResource(2, p, r);

    projectResourceRepository.save(pr);

  }
}
