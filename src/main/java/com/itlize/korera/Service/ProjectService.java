package com.itlize.korera.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.User;

@Service
public interface ProjectService {

  //create
  Project addProject(Project project);

  //get
  List<Project> findAllByUserName(String userName);
  Project getProjectByProjectName(String projectName);
  List<Project> getAll();

  //update project name
  void updateProjectNameByName(String oldProjectName, String newProjectName);

  //delete
  boolean deleteProjectById(long projectId);
}
