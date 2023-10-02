package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long projectId;

    @Column(name="project_number", unique=true)
    private String projectNumber;

    @Column(name="date_created")
    private Date dateCreated;

    @Column(name="last_modified")
    private Date lastModified;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name="userid")
    @JsonBackReference("project-user")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="project_resource",
            joinColumns = @JoinColumn(name="resourceID"),
            inverseJoinColumns = @JoinColumn(name="projectId")
    )
   @JsonIgnore
    private Set<Resource> resources = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
   @JsonManagedReference("project-formula")
    //@JsonIgnore
    private List<Formula> formulas;

    public Project() {

    }


    public Project(String projectNumber, Date dateCreated, Date lastModified, User user) {
        this.projectNumber = projectNumber;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.user = user;
    }
    public Project(String projectNumber) {
        this.projectNumber = projectNumber;
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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    // public void setProjectResourceSet(Set<ProjectResource> projectResources) {
    //     this.projectResources = projectResources;
    // }

    


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


    public List<Formula> getFormulas() {
        return formulas;
    }


    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }
}



