package com.itlize.korera.Service;

import java.util.Set;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;

public interface ProjectResourceService {

  Set<Resource> getResourceByProject(Long projectId);

  Set<Project> getProjectByResource(Long resourceId);

  boolean addResourceToProject(Long projectId, Long resourceId);

  boolean removeResourceFromProject(Long projectId, Long resourceId); 

  
}
