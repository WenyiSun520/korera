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

    @Column(name="Project_Number")
    private String projectNumber;

    @Column(name="date_created")
    private Date dateCreated;

    @Column(name="last_modified")
    private Date lastModified;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

//    @ManyToMany
//    private Set<Resource> resources;

    @OneToOne(mappedBy = "project", cascade=CascadeType.ALL)
    private Formula formula;

    public Project() {

    }


    public Project(String projectNumber, Date dateCreated, Date lastModified, User user, Formula formula) {
        this.projectNumber = projectNumber;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.user = user;
        this.formula = formula;
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

//    public Set<Resource> getResources() {
//        return resources;
//    }
//
//    public void setResources(Set<Resource> resources) {
//        this.resources = resources;
//    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
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
                ", formula=" + formula +
                '}';
    }
}