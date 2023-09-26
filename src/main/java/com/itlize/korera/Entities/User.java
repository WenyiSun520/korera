package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.*;

@Entity
public class User {;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;

    private String username;
    private String fname;
    private String lname;
    private String password;

    private Date created_date;

    @OneToMany(mappedBy="user")
    private List<Project> project;

    public User() {

    }
    public User(String username, String password, Date created_date) {
        this.username = username;
        this.password = password;
        this.created_date = created_date;
    }

    public User(String username, String fname, String lname, String password, Date created_date) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.created_date = created_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    public Date getCreated_date() {
        return created_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", password='" + password + '\'' +
                ", created_date=" + created_date +
                '}';
    }
}
