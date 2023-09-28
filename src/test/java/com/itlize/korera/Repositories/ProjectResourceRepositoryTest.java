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

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ResourceRepository resourceRepository;

  @Test
  void addProjectResource() {

    //arrange
    Project p = new Project();
    p.setProjectNumber("project132");
    p.setDateCreated(new Date(0));
    projectRepository.save(p); 
    
    Resource r = new Resource();
    r.setResourceName("Resource132");
    resourceRepository.save(r);


    ProjectResource pr = new ProjectResource(6, p, r);


    //act
    projectResourceRepository.save(pr);

  }
}
