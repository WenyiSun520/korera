package com.itlize.korera.Controller;

import java.util.List;

import com.itlize.korera.Entities.ProjectInfoDTO;
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
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Service.ProjectService;

@RestController
@RequestMapping("/projects")
public class projectController {
  
  private final ProjectService projectService;

  public projectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @PostMapping("/{username}/add_new_project")
  public ResponseEntity<String> addProject(@PathVariable("username")String username, @RequestBody Project project) {
    Project newProject = projectService.addProject(username, project);
    if (newProject != null) {
      return ResponseEntity.ok().body("new project has been saved");
    }
    return ResponseEntity.status(501).body("error when saving new project");
  }

  @GetMapping("/projects")
  public ResponseEntity<List<ProjectInfoDTO>> getAllProject() {
    List<ProjectInfoDTO> projects = projectService.getAll();
    return ResponseEntity.ok().body(projects);
  }


  @GetMapping("/search-by-username")
  public ResponseEntity<List<Project>> getProjectByUserName(@RequestParam(value="userName") String userName) {
    List<Project> projects = projectService.findAllByUserName(userName);
    if (!projects.isEmpty()) {
      // for (Project project: projects) {
      //   project.getFormulas().size(); 
      // }
      return ResponseEntity.ok().body(projects);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{projectName}")
  public ResponseEntity<?> getProjectByProjectName(@PathVariable("projectName") String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    if (project != null) {
      return ResponseEntity.ok().body(project);
    }else {
      return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
    }
  }


  @PutMapping("/{oldProjectName}")
  public ResponseEntity<String> updateProjectName(@PathVariable String oldProjectName, @RequestBody Project project) {
    try {
      String newProjectName = project.getProjectNumber();
      projectService.updateProjectNameByName(oldProjectName, newProjectName);
      return ResponseEntity.ok().body("updated project name successfully");
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{projectName}")
  public ResponseEntity<String> deleteProject(@PathVariable String projectName) {
    Project project = projectService.getProjectByProjectName(projectName);
    if (projectService.deleteProjectById(project.getProjectId())) {
      return ResponseEntity.ok().body("project has been deleted");
    }
    return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);

  }

  
}
