package com.itlize.korera.Controller;

import java.util.*;

import com.itlize.korera.DTO.FormulaDTO;
import com.itlize.korera.Entities.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Repositories.FormulaRepository;
import com.itlize.korera.Service.FormulaService;
import com.itlize.korera.Service.ProjectService;

@RestController
@RequestMapping("api/formula")
public class formulaController {

  private final FormulaService formulaService;
  private final ProjectService projectService;

  public formulaController(FormulaService formulaService, ProjectService projectService) {
    this.formulaService = formulaService;
    this.projectService = projectService;
  }

  @GetMapping("/enum-values")
  public ColumnTypeEnum[] getEnumValues() {
    return ColumnTypeEnum.values();
  }

  @GetMapping("/formulas")
  public ResponseEntity<List<Formula>> getAll() {
    List<Formula> formulas = formulaService.getAllFormulas();
    return ResponseEntity.ok().body(formulas);
  }

  @GetMapping("/search-by-project")
  public ResponseEntity<?> getAllFormulaByProjectName(@RequestParam(value="projectname") String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    if (project == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Map<String, List<FormulaDTO>> formulaMap = new HashMap<>();
    List<Formula> formulas = project.getFormulas();
    Set<Resource> resources = project.getResources();

    for (Formula formula : formulas) {
      String type = formula.getFieldName();
      FormulaDTO formulaDTO = new FormulaDTO(formula.getFormulaId(),formula.getFieldName(),formula.getFieldType(),
              formula.getFieldValue(),formula.getProject().getProjectId(),formula.getResource().getResourceID());
      if (!formulaMap.containsKey(type)) {
        formulaMap.put(type, new ArrayList<>());
      }
      formulaMap.get(type).add(formulaDTO);
    }
    return ResponseEntity.ok().body(formulaMap);
  }

  @GetMapping("get-type/search-by-project")
  public ResponseEntity<List<String>> getFormulaTypeByProjectName(@RequestParam(value="projectname") String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    List<Formula> formulas = project.getFormulas();
    return ResponseEntity.ok().body( formulas.stream().map(Formula::getFieldName).distinct().toList());
  }

  @PostMapping("/save")
  public ResponseEntity<String> saveFormula(
      @RequestBody List<Formula> formula) {
    if (formulaService.addFormula(formula)) {
      return ResponseEntity.ok().body("new formula has been added");
    } else {
      return ResponseEntity.status(501).body("error when saving new formula");
    }
  }

  @PutMapping("/update-type/{formulaId}/{type}")
  public ResponseEntity<String> updateFormulaFieldType(
      @PathVariable("formulaId") long formulaId, @PathVariable("type") ColumnTypeEnum type) {
    Formula formula = formulaService.findFormulaById(formulaId);
    if (formula == null) {
      return new ResponseEntity<>("formula not found", HttpStatus.NOT_FOUND);
    }
    formulaService.updateFormulaType(formula, type);
    return ResponseEntity.ok().body("formula type has been updated");
  }

 //  @PutMapping("/update-name/{formulaId}/{fieldName}")
//  public ResponseEntity<String> updateFormulaFieldName(
//      @PathVariable("formulaId") long formulaId, @RequestParam("formula") Formula formula) {
//
//    if (formula == null) {
//      return new ResponseEntity<>("formula not found", HttpStatus.NOT_FOUND);
//    }
//    formulaService.updateFormulaFieldName(formula);
//    return ResponseEntity.ok().body("formula name has been updated");
//  }

  @PutMapping("/update-value/{formulaId}/{fieldValue}")
  public ResponseEntity<String> updateFormulaFieldValue(
      @PathVariable("formulaId") long formulaId, @PathVariable("fieldValue") String fieldValue) {
    Formula formula = formulaService.findFormulaById(formulaId);
    if (formula == null) {
      return new ResponseEntity<>("formula not found", HttpStatus.NOT_FOUND);
    }
    formulaService.updateFormulaFieldValue(formula,fieldValue);
    return ResponseEntity.ok().body("formula value has been updated");
  }

  @DeleteMapping("/{formulaId}")
  public ResponseEntity<String> deleteFormulaById(@PathVariable("formulaId") long formulaId) {
    if (formulaService.deleteFormulaById(formulaId)) {
      return ResponseEntity.ok().body("formula has been deleted");
    } 
    return new ResponseEntity<>("Formula not found", HttpStatus.NOT_FOUND);
    
  }

}
