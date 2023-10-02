package com.itlize.korera.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private long userID;
    private String username;

    private Date created_date;

    private List<ProjectDTO> projects;

    public UserDTO(){}
    public UserDTO(long userID, String username,Date created_date, List<ProjectDTO> projects) {
        this.userID = userID;
        this.username = username;
        this.created_date = created_date;
        this.projects = projects;

    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}

