package com.itlize.korera.Service;

import java.util.List;

import com.itlize.korera.DTO.ProjectInfoDTO;
import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.Project;

@Service
public interface ProjectService {

  //create
  Project addProject(String username, Project project);

  //get
  List<Project> findAllByUserName(String userName);
  Project getProjectByProjectName(String projectName);
  List<ProjectInfoDTO> getAll();

  //update project name
  void updateProjectNameByName(String oldProjectName, String newProjectName);

  //delete
  boolean deleteProjectById(long projectId);
}
