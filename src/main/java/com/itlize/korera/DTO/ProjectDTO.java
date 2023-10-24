package com.itlize.korera.DTO;

public class ProjectDTO {
    private Long projectId;
    private String projectName;

    public ProjectDTO(Long projectId, String projectNumber) {
        this.projectId = projectId;
        this.projectName = projectNumber;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getprojectName() {
        return projectName;
    }

    public void setprojectName(String projectName) {
        this.projectName = projectName;
    }
}
