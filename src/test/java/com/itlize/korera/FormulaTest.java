package com.itlize.korera;

import java.util.Date;
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
  private UserRepository userRepository;


  @Test
  void addFormula() {
    Formula f = new Formula();
    Project p = projectRepository.findById((long) 1).orElse(null);
    f.setFieldName("quantity");
    f.setFieldValue("10");
    f.setFieldType(ColumnTypeEnum.NUMBER);
    f.setProject(p);
  }

}
