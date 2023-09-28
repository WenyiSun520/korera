package com.itlize.korera.Repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;

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
    Project p = projectRepository.findById((long) 1).orElse(null);
    Resource r = resourceRepository.findById((long) 1).orElse(null);
    f.setFieldName("quantity");
    f.setFieldValue("10");
    f.setFieldType(ColumnTypeEnum.NUMBER);
    f.setProject(p);
    f.setResource(r);
    // act
    Formula savedF = formulaRepository.save(f);
    // assert
    assertTrue(savedF.equals(f));
  }

  // read
  @Test
  void getFormulasByProject() {
    Formula f = new Formula();
    Project p = projectRepository.findById(1L).orElse(null);
    
  }

}
