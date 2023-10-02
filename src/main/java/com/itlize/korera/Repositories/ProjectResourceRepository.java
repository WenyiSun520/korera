package com.itlize.korera.Repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.ProjectResource;
import com.itlize.korera.Entities.Resource;

@Repository
@EnableJpaRepositories
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Integer> {
  List<ProjectResource> findProjectResourceByProjectId(Project project);

  // List<ProjectResource> findByResource(Resource resource);

  // Set<ProjectResource> findByProjectAndResource(Project project, Resource resource);

  // void deleteByProjectAndResource(Project project, Resource resource);
}
