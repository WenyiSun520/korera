package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Project_Id")
    private Long projectId;

    @Column(name="Project_Number", unique=true)
    private String projectNumber;

    @Column(name="date_created")
    private Date dateCreated;

    @Column(name="last_modified")
    private Date lastModified;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userId")
    private User user;


    @OneToMany(mappedBy="projectId")
    private Set<ProjectResource> projectResources = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Formula> formulas;

    public Project() {

    }


    public Project(String projectNumber, Date dateCreated, Date lastModified, User user) {
        this.projectNumber = projectNumber;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.user = user;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectID(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ProjectResource> getProjectResourceSet( ) {
        return projectResources;
    }

    public void setProjectResourceSet(ProjectResource projectResource) {
        this.projectResources.add(projectResource);
    }

    public void setProjectResourceSet(Set<ProjectResource> projectResources) {
        this.projectResources = projectResources;
    }



    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectNumber='" + projectNumber + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                ", user=" + user +
//                ", resources=" + resources +
                ", formulas=" + formulas +
                '}';
    }
}