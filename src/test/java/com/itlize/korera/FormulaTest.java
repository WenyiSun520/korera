package com.itlize.korera;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.FormulaRepository;
import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;

@SpringBootTest
public class FormulaTest {

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
    try {
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
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // read

}
