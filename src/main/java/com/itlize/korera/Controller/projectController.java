package com.itlize.korera.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Service.ProjectService;

@RestController
@RequestMapping("/project")
public class projectController {
  
  private final ProjectService projectService;

  public projectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping("/{username}/projects/addNewProject")
  public ResponseEntity<String> addProject(@PathVariable("username") String userName, @RequestBody Project project) {
    Project newProject = projectService.addProject(project, userName);
    if (newProject != null) {
      return ResponseEntity.ok().body("new project has been saved");
    }
    return ResponseEntity.status(501).body("error when saving new project");
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<List<Project>> getProjectByUserName(@PathVariable("username") String userName) {
    List<Project> projects = projectService.findAllByUserName(userName);
    if (!projects.isEmpty()) {
      return ResponseEntity.ok().body(projects);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{projectName}")
  public ResponseEntity<Project> getProjectByProjectName(@PathVariable("projectName") String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    if (project != null) {
      return ResponseEntity.ok().body(project);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


  @PutMapping("/{projectName}/update")
  public ResponseEntity<String> updateProjectName(@PathVariable String oldProjectName, @RequestParam String newProjectName, @RequestBody Project Project) {
    try {
      projectService.updateProjectNameByName(oldProjectName, newProjectName);
    }
  }

  
}
