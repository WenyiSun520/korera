package com.itlize.korera.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.Project;

@Service
public interface ProjectService {

  Project addProject(Project project);

  void updateProjectNameByName(String oldProjectName, String newProjectName);
  
  // List<Project> getAllProjectsByUserName(String userName);
}
