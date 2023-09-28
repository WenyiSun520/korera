package com.itlize.korera.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.ProjectResource;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.ResourceRepository;

@Service
public class ProjectResourceServiceImpl implements ProjectResourceService {

  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private ResourceRepository resourceRepository;

  @Override
  public Set<Resource> getResourceByProject(Long projectId) {
    Project project = projectRepository.findById(projectId).orElse(null);
    if (project != null) {
      Set<ProjectResource> projectResources = project.getProjectResourceSet();
      Set<Resource> resources = projectResources.stream()
          .map(ProjectResource::getResourceId)
          .collect(Collectors.toSet());

      return resources;
    }
    return Collections.emptySet();
  }

  @Override
  public Set<Project> getProjectByResource(Long resourceId) {
    Resource resource = resourceRepository.findById(resourceId).orElse(null);
    if (resource != null) {
      Set<Project> projects = resource.getProjectResources().stream()
          .map(ProjectResource::getProjectId)
          .collect(Collectors.toSet());
      return projects;
    }
    return Collections.emptySet();
  }

  @Override
  public boolean addResourceToProject(Long projectId, Long resourceId) {
    Project project = projectRepository.findById(projectId).orElse(null);
    Resource resource = resourceRepository.findById(resourceId).orElse(null);

    if (project != null && resource != null) {
      ProjectResource projectResource = new ProjectResource(project, resource);
      project.getProjectResourceSet().add(projectResource);
      projectRepository.save(project);
      return true;
    }
    return false;
  }

  public boolean removeResourceFromProject(Long projectId, Long resourceId) {
    Project project = projectRepository.findById(projectId).orElse(null);
    Resource resource = resourceRepository.findById(resourceId).orElse(null);

    if (project != null && resource != null) {
      Set<ProjectResource> projectResources = project.getProjectResourceSet();

      ProjectResource toRemove = null;
      for (ProjectResource pr : projectResources) {
        if (pr.getResourceId().equals(resource)) {
          toRemove = pr;
          break;
        }
      }

      if (toRemove != null) {
        projectResources.remove(toRemove);
        projectRepository.save(project);
        return true;
      }
      
    }
    return false;
  }

}
