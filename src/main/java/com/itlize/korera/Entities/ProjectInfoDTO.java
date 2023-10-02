package com.itlize.korera.Entities;

import java.util.*;

public class ProjectInfoDTO {

    private final String user;
    private Long projectId;


    private String projectNumber;


    private Date dateCreated;


    private Date lastModified;

    private Set<Resource> resources = new HashSet<>();

    private List<Formula> formulas = new ArrayList<>();

    public ProjectInfoDTO(Long projectId, String projectNumber, Date dateCreated, Date lastModified, String user, Set<Resource> resources, List<Formula> formulas) {
        this.projectId = projectId;
        this.projectNumber = projectNumber;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.user = user;
        this.resources = resources;
        this.formulas = formulas;
    }

    public String getUser() {
        return user;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }
}
