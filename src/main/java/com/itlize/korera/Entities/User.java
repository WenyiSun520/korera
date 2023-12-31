package com.itlize.korera.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.nio.file.FileStore;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class User implements UserDetails {;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="userid")
    private long userID;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role Role;

    @Column(name ="username")
    private String username;
    @Column(name ="fname")
    private String fname;
    @Column(name ="lname")
    private String lname;
    @Column(name ="password")
    //@JsonIgnore
    private String password;
    @Column(name ="created_date")
    private Date created_date;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference("project-user")
    //@JsonBackReference("project-user")
//    @JsonIgnore
    private List<Project> projects;

    public User() {

    }
    public User(String username, String password, Date created_date) {
        this.username = username;
        this.password = password;
        this.created_date = created_date;
    }

    public User(String username, String password, String fname, String lname) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;

    }

    public User(String username, String fname, String lname, String password, Date created_date) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.created_date = created_date;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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



