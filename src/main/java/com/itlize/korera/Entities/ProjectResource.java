package com.itlize.korera.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="ProjectResource")
public class ProjectResource {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int projectResourceId;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="projectId")
  private Project projectId;  //projectId == project


  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="ResourceID")
  private Resource resourceId; //resourceId == resource

  public ProjectResource() {
    
  }

  
  public ProjectResource(int projectResourceId, Project projectId, Resource resourceId) {
    this.projectResourceId = projectResourceId;
    this.projectId = projectId;
    this.resourceId = resourceId;
  }
  public ProjectResource(Project projectId, Resource resourceId) {
    this.projectId = projectId;
    this.resourceId = resourceId;
  }


  public int getProjectResourceId() {
    return projectResourceId;
  }


  public void setProjectResourceId(int projectResourceId) {
    this.projectResourceId = projectResourceId;
  }


  public Project getProjectId() {
    return projectId;
  }


  public void setProjectId(Project projectId) {
    this.projectId = projectId;
  }


  public Resource getResourceId() {
    return resourceId;
  }


  public void setResourceId(Resource resourceId) {
    this.resourceId = resourceId;
  }

  

}
