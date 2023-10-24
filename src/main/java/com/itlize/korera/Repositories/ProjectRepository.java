package com.itlize.korera.Repositories;


import com.itlize.korera.Entities.Project;
import com.itlize.korera.DTO.ProjectDTO;
import com.itlize.korera.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
@EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // find project by name
    Project getProjectByProjectName(String projectName);
    Project getProjectByProjectId(Long projectId);
    // List<Project> getAllProjectsByUserName(String userName);

    @Query("SELECT new com.itlize.korera.DTO.ProjectDTO(proj.projectId, proj.projectName) " +
            "FROM Project proj " +
            "WHERE proj.user = :user")
    List<ProjectDTO>  findProjectDTOsByUser(@Param("user") User user);

    List<Project> findByUser(User user);

    boolean existsByProjectName(String projectName);

    
}
