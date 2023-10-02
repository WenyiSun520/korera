package com.itlize.korera.Entities;

public class ProjectDTO {
    private Long projectId;
    private String projectNumber;

    public ProjectDTO(Long projectId, String projectNumber) {
        this.projectId = projectId;
        this.projectNumber = projectNumber;
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
}
