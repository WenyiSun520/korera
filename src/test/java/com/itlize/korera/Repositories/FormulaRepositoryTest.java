package com.itlize.korera.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;

@SpringBootTest
@Transactional
public class FormulaRepositoryTest {
  @Autowired
  private FormulaRepository formulaRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private ResourceRepository resourceRepository;

  // create
  @Test
  void addFormula() {
    // arrange
    Formula f = new Formula();
   // Project p = new Project();
    //arrange
    User user = new User("siqichen", "siqi", "chen", "password", new Date(0));
    Project project = new Project("project092", new Date(0), new Date(0), user);
    projectRepository.save(project);

    //act

    Resource r = new Resource();
    resourceRepository.save(r);
    f.setFieldName("total");
    f.setFieldValue("200");
    f.setFieldType(ColumnTypeEnum.NUMBER);
    f.setProject(project);
    f.setResource(r);

    Project savedProject = projectRepository.save(project);


    // act
    Formula savedF = formulaRepository.save(f);

    // assert
    assertTrue(savedF.equals(f));
  }

  // read
  @Test
  void getFormulasById() {
    // arrange
    User user = new User("Bob", "12345", new Date(0));
    Project p = new Project("project65", new Date(0), new Date(0), user);
    projectRepository.save(p);
    Resource r = resourceRepository.findById((long) 1).orElse(null);
    Formula f = new Formula();
    f.setFieldValue("blue");
    f.setFieldType(ColumnTypeEnum.TEXT);
    f.setFormulaId(2L);
    f.setFieldName("1234567");
    f.setProject(p);
    f.setResource(r);
    formulaRepository.save(f);


    // act
    Formula newerF = formulaRepository.findById(2L).orElse(null);

    //assert
    assertEquals("1234567", newerF.getFieldName());
  }

  // @Test
  // void getAllFormulaByProject() {
  //   Project p = projectRepository.getProjectByProjectNumber("project3777");
  //   List<Formula> formulas = formulaRepository.getAllFormulaByProject(p);
  // }

}
