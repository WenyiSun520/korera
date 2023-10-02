package com.itlize.korera.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.itlize.korera.Entities.*;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import com.itlize.korera.Repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.UserRepository;


@Service
public class ProjectServiceImpl implements ProjectService {


  private final ProjectRepository projectRepository;

  private final UserRepository userRepository;

  private final ResourceRepository resourceRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, ResourceRepository resourceRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
    this.resourceRepository = resourceRepository;
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
  public List<ProjectInfoDTO> getAll() {

    List<Project> projectList = projectRepository.findAll();
    List<ProjectInfoDTO> resultList = new ArrayList<>();
    for(Project p: projectList){
      ProjectInfoDTO proj = new ProjectInfoDTO(p.getProjectId(),p.getProjectNumber(),p.getDateCreated(),p.getLastModified(),p.getUser().getUsername(),p.getResources(),p.getFormulas());
     resultList.add(proj);
    }

    return resultList;
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
        Set<Resource> resourceSet = p.getResources();
        for(Resource re:resourceSet){
          re.getProject().remove(p);
          this.resourceRepository.save(re);
        }
        projectRepository.delete(p);
        return !projectRepository.existsById(projectId);
      }
      return false;

  }
}
