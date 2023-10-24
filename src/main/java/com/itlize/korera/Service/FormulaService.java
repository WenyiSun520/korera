package com.itlize.korera.Service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;

@Service
public interface FormulaService {

  //create
  boolean addFormula(List<Formula> formula);
  
  //get by formulaId
  Formula findFormulaById(long formuaId);

  //get all by projectId 
  List<Formula> getAllFormulaByProjectName(String projectName);
  List<Formula> getAllFormulas();


  //update formula
  // void updateFormula(String fieldName, ColumnTypeEnum type, String fieldValue);
  void updateFormulaType(Formula formula, ColumnTypeEnum type);
  void updateFormulaFieldName(Formula formula, String fieldName);
  void updateFormulaFieldValue(Formula formula, String value);


  //delete formula 
  boolean deleteFormulaById(long formulaId);

  
}
