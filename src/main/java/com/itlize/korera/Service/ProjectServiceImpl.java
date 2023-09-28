package com.itlize.korera.Service;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.UserRepository;

public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private final ProjectRepository projectRepository;
  @Autowired
  private final UserRepository userRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Project addProject(Project project) {
    return projectRepository.save(project);
  }

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

  // @Override
  // public List<Project> getAllProjectsByUserName(String userName) {
  //   User user = (User) this.userRepository.findByUsername(userName);
  //   List<Project> projects = new ArrayList<>();
  // }
}
