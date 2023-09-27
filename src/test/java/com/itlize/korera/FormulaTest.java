package com.itlize.korera;

import java.util.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.UserRepository;

@SpringBootTest
public class FormulaTest {
  
  @Autowired
  private Formula formula;


  @Autowired
  private Project project;

  @Autowired
  private UserRepository userRepository;



  public void getFormulasByProjectId() {
    User user = this.userRepository.findByUsername("siqi@gamil.com");
    
    //creating a new project
    Project project = new Project();
    project.setUser(user);
    project.setProjectNumber("project1");
    project.setDateCreated(new Date());
    project.setLastModified(new Date());


    //creating formula
    try {
      Set<Formula> formulas = 
    }


    
  }

}
