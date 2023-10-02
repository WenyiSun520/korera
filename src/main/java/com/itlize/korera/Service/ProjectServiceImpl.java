package com.itlize.korera.Service;

import java.util.Date;
import java.util.List;

import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.UserRepository;


@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private final ProjectRepository projectRepository;
  @Autowired
  private final UserRepository userRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
  }

  /**
   * add
   */

  @Override
  public Project addProject(String username, Project project) {
    User user = this.userRepository.findByUsername(username);
    if(user == null) throw new PathVariableNotFound("username");
    project.setUser(user);
    project.setDateCreated(new Date());
    project.setLastModified(new Date());
    return projectRepository.save(project);
  }
  

  /**
   * get
   */

  @Override
  public List<Project> getAll() {
    return projectRepository.findAll();
  }
  @Override
  public List<Project> findAllByUserName(String userName) {
    User user = userRepository.findByUsername(userName);
    return projectRepository.findByUser(user);
  }

  @Override
  public Project getProjectByProjectName(String projectName) {
    return projectRepository.getProjectByProjectNumber(projectName);
  }

  /**
   * update
   */

  @Override
  public void updateProjectNameByName(String oldProjectName, String newProjectName) {
    Project project = projectRepository.getProjectByProjectNumber(oldProjectName);
    long projectId = project.getProjectId();
    try {
      if (!projectRepository.existsById(projectId)) {
        project.setProjectNumber(newProjectName);
      }
    } catch (Exception e) {
      System.out.println("You cannot set to this project name, it's already existed");
      System.out.println(e);
    }
  }


  /**
   * delete
   */
  @Override
  public boolean deleteProjectById(long projectId) {
    Project p = projectRepository.findById(projectId).orElse(null);
    
      if (p != null) {
        projectRepository.delete(p);
        return !projectRepository.existsById(projectId);
      }
      return false;

  }
}
