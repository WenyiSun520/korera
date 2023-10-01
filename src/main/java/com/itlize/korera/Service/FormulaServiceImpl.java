package com.itlize.korera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Repositories.FormulaRepository;
import com.itlize.korera.Repositories.ProjectRepository;
@Service
public class FormulaServiceImpl implements FormulaService{
  
  @Autowired
  private final FormulaRepository formulaRepository;

  @Autowired
  private final ProjectRepository projectRepository;
  

  
  public FormulaServiceImpl(FormulaRepository formulaRepository, ProjectRepository projectRepository) {
    this.formulaRepository = formulaRepository;
    this.projectRepository = projectRepository;
  }

  @Override
  public Formula addFormula(Formula formula) {
    Formula exist = formulaRepository.findByProjectAndFieldNameAndFieldValueAndFieldTypeAndResource(
      formula.getProject(),
      formula.getFieldName(), 
      formula.getFieldValue(), 
      formula.getFieldType(),
      formula.getResource());
    if (exist != null) return exist;
    return formulaRepository.save(formula);

  }

  @Override
  public List<Formula> getAllFormulas(){
    return formulaRepository.findAll();
  }

  @Override
  public Formula findFormulaById(long formulaId) {
    return formulaRepository.findById(formulaId).orElse(null);
  }
  @Override
  public List<Formula> getAllFormulaByProjectName(String projectName) {

    Project p = projectRepository.getProjectByProjectNumber(projectName);
    return formulaRepository.findByProject(p);
  }
  
  @Override
  public void updateFormulaType(Formula formula, ColumnTypeEnum type) {
    formula.setFieldType(type);
    formulaRepository.save(formula);
  }

  @Override
  public void updateFormulaFieldName(Formula formula, String fieldName) {
    formula.setFieldName(fieldName);
    formulaRepository.save(formula);
  }

  @Override
  public void updateFormulaFieldValue(Formula formula, String value) {
    formula.setFieldName(value);
    formulaRepository.save(formula);
  }

  // public void updateFormula(String fieldName, ColumnTypeEnum type, String fieldValue) {

  // }

  @Override
  public boolean deleteFormulaById(long formulaId){

    Formula f = formulaRepository.findById(formulaId).orElse(null);

    if (f == null) {
      return false;
    }
    formulaRepository.delete(formulaRepository.findById(formulaId).orElse(null));
    
    return true;
  }

  
}
