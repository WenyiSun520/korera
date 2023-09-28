package com.itlize.korera.Service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;

@Service
public interface FormulaService {

  Formula addFormula(Formula formula);
  
  Formula findFormulaById(long formuaId);

  List<Formula> getAllFormulaByProject(Project project);

  void updateFormulaType(Formula formula, ColumnTypeEnum type);

  boolean deleteFormulaById(long formulaId);

  
}
