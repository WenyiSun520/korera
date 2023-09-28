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
  private Project projectId;


  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="ResourceID")
  private Resource resourceId;

}
