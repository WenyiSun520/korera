package com.itlize.korera.Service;

import java.util.List;
import java.util.Set;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Repositories.ResourceRepository;
import io.micrometer.observation.annotation.Observed;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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



  private final ResourceService resourceService;
  

  @Autowired
  public FormulaServiceImpl(FormulaRepository formulaRepository, ProjectRepository projectRepository, ResourceService resourceService) {
    this.formulaRepository = formulaRepository;
    this.projectRepository = projectRepository;
    this.resourceService = resourceService;
  }

  @Override
  public boolean addFormula(List<Formula> list) {
//    Long projectId = list.get(0).getProject().getProjectId();
//    Set<Resource> resourceList = this.resourceService.getAllResourceByProjectId(projectId);
//    for(Formula formula: list){
//        for(Resource resource: resourceList ) {
//          Formula formula1 = new Formula(formula.getFieldName(), formula.getFieldType(), formula.getFieldValue(), formula.getProject(),resource);
//           this.formulaRepository.save(formula1);
//        }
//    }
    this.formulaRepository.saveAll(list);

    return true;
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

    Project p = projectRepository.getProjectByProjectName(projectName);
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
    formula.setFieldValue(value);
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

  @Override
  @Transactional
  public boolean deleteFormulasByFieldName(String name, Long projectId){
    Project project = this.projectRepository.getProjectByProjectId(projectId);
    if(project == null) return false;

   return this.formulaRepository.deleteFormulasByFieldNameAndAndProject(name, project) != -1;
  }

  
}
