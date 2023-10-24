package com.itlize.korera.DTO;

import com.itlize.korera.DTO.ProjectDTO;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private long userID;
    private String username;

    private String fname;
    private String lname;

    private Date created_date;

    public UserDTO(){}

    public UserDTO(long userID, String username, String fname, String lname, Date created_date) {
        this.userID = userID;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.created_date = created_date;
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}

