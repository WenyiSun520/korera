package com.itlize.korera.Controller;

import java.util.List;

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
@RequestMapping("/formula")
public class formulaController {

  private final FormulaService formulaService;
  private final ProjectService projectService;

  public formulaController(FormulaService formulaService, ProjectService projectService) {
    this.formulaService = formulaService;
    this.projectService = projectService;
  }

  @GetMapping("/formulas")
  public ResponseEntity<List<Formula>> getAll() {
    List<Formula> formulas = formulaService.getAllFormulas();
    return ResponseEntity.ok().body(formulas);
  }

  @GetMapping("/search-by-project")
  public ResponseEntity<List<Formula>> getAllFormula(@RequestParam(value="projectname") String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    if (project == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<Formula> formulas = formulaService.getAllFormulaByProjectName(projectName);
    if (formulas == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok().body(formulas);
  }

  @PostMapping("/")
  public ResponseEntity<String> saveFormula(
      @RequestBody Formula formula) {
    Formula newFormula = formulaService.addFormula(formula);
    if (newFormula != null) {
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

  @PutMapping("/update-name/{formulaId}/{fieldName}")
  public ResponseEntity<String> updateFormulaFieldName(
      @PathVariable("formulaId") long formulaId, @PathVariable("fieldName") String fieldName) {
    Formula formula = formulaService.findFormulaById(formulaId);
    if (formula == null) {
      return new ResponseEntity<>("formula not found", HttpStatus.NOT_FOUND);
    }
    formulaService.updateFormulaFieldName(formula, fieldName);
    return ResponseEntity.ok().body("formula name has been updated");
  }

  @PutMapping("/update-value/{formulaId}/{fieldValue}")
  public ResponseEntity<String> updateFormulaFieldValue(
      @PathVariable("formulaId") long formulaId, @PathVariable("fieldValue") String fieldValue) {
    Formula formula = formulaService.findFormulaById(formulaId);
    if (formula == null) {
      return new ResponseEntity<>("formula not found", HttpStatus.NOT_FOUND);
    }
    formulaService.updateFormulaFieldName(formula, fieldValue);
    return ResponseEntity.ok().body("formula value has been updated");
  }

  @DeleteMapping("/{formulaId}")
  public ResponseEntity<String> deleteFormulaById(@PathVariable("formulaId") long formulaId) {
    if (formulaService.deleteFormulaById(formulaId)) {
      return ResponseEntity.ok().body("formula has been deleted");
    } else {
      return new ResponseEntity<>("Formula not found", HttpStatus.NOT_FOUND);
    }
  }

}
