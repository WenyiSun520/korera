package com.itlize.korera.Service;

import java.util.*;

import com.itlize.korera.DTO.ProjectInfoDTO;
import com.itlize.korera.Entities.*;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import com.itlize.korera.Repositories.FormulaRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.UserRepository;


@Service
public class ProjectServiceImpl implements ProjectService {


  private final ProjectRepository projectRepository;

  private final UserRepository userRepository;

  private final ResourceRepository resourceRepository;

  private final FormulaRepository formulaRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, ResourceRepository resourceRepository, FormulaRepository formulaRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
    this.resourceRepository = resourceRepository;
    this.formulaRepository = formulaRepository;
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
      ProjectInfoDTO proj = new ProjectInfoDTO(p.getProjectId(),p.getProjectName(),p.getDateCreated(),p.getLastModified(),p.getUser().getUsername(),p.getResources(),p.getFormulas());
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
    return projectRepository.getProjectByProjectName(projectName);
  }

  /**
   * update
   */

  @Override
  public void updateProjectNameByName(String oldProjectName, String newProjectName) {
    Project project = projectRepository.getProjectByProjectName(oldProjectName);
    long projectId = project.getProjectId();
    try {
      if (!projectRepository.existsById(projectId)) {
        project.setProjectName(newProjectName);
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
  @Override
  @Transactional
  public void removeResourcesFromProject(Long projectId, long[] resourceIdList){
    Project project = this.projectRepository.getProjectByProjectId(projectId);

    Set<Resource> projectResources = project.getResources();

    Set<Resource> set = new HashSet<>();
    for(Long id: resourceIdList){

      Resource resource = this.resourceRepository.getResourceByResourceID(id);
      Integer number = this.formulaRepository.deleteFormulasByResourceAndProject(resource,project);
      System.out.println("The number of removed formula: "+ number);
      set.add(resource);
    }

    if(projectResources.removeAll(set)){
      project.setResources(projectResources);
      this.projectRepository.save(project);
    }else{
      System.out.println("Error when removing resource");
    }





  }
}
