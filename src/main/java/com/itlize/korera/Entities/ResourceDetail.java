package com.itlize.korera.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ResourceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resourceDetailID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resourceid")
    private Resource resource;
    @Column(nullable = false)
    private String detailName;
    private String description;
//    @ManyToOne
//    @Column(nullable = false)
//    private User user;
    private Date createdDate;
    private Date last_modified;


    public long getResourceDetailID() {
        return resourceDetailID;
    }

    public void setResourceDetailID(long resourceDetailID) {
        resourceDetailID = resourceDetailID;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(Date last_modified) {
        this.last_modified = last_modified;
    }

    @Override
    public String toString() {
        return "ResourceDetail{" +
                "ResourceDetailID=" + resourceDetailID +
                ", resource=" + resource +
                ", detailName='" + detailName + '\'' +
                ", description='" + description + '\'' +
//                ", user=" + user +
                ", createdDate=" + createdDate +
                ", last_modified=" + last_modified +
                '}';
    }
}
