package com.itlize.korera.Repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itlize.korera.Entities.ProjectResource;

@Repository
@EnableJpaRepositories
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Integer> {
  
}
