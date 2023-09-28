package com.itlize.korera.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Repositories.FormulaRepository;
import com.itlize.korera.Repositories.ProjectRepository;

public class FormulaServiceImpl implements FormulaService{
  

  private final FormulaRepository formulaRepository;
  

  @Autowired
  public FormulaServiceImpl(FormulaRepository formulaRepository) {
    this.formulaRepository = formulaRepository;
  }

  @Override
  public Formula addFormula(Formula formula) {
    formula.getFieldName();
    formula.getFieldType();
    formula.getFieldValue();
    formula.getProject();
    formula.getResource();
    return formulaRepository.save(formula);

  }

  @Override
  public Formula findFormulaById(long formulaId) {
    return formulaRepository.findById(formulaId).orElse(null);
  }

  public List<Formula> getAllFormulaByProject(Project project) {
    return formulaRepository.findByProject(project);
  }

  public void updateFormulaType(Formula formula, ColumnTypeEnum type) {
    formula.setFieldType(type);
    formulaRepository.save(formula);
  }

  public boolean deleteFormulaById(long formulaId){

    Formula f = formulaRepository.findById(formulaId).orElse(null);

    if (f != null) {
      formulaRepository.delete(f);
    }
    return !formulaRepository.exists(Example.of(f));
  }
}
