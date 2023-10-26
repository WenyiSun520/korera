package com.itlize.korera.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RESOURCE_DETAIL")
public class ResourceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resourceDetailID;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "resourceid")
    @JsonBackReference("resource-resourcedetail")
    private Resource resource;
    @Column(name="name", nullable = false)
    private String detailName;
    @Column(name="detail_description")
    private String detailDescription;

    private Date created_date;
    private Date latest_updated;
    @ManyToOne( cascade = CascadeType.REMOVE)
    @JoinColumn(name = "latest_modified_user_id")
    private User latest_modified_by;

    public ResourceDetail() {
    }

    public ResourceDetail(long resourceDetailID, Resource resource, String detailName, String detailDescription) {
        this.resourceDetailID = resourceDetailID;
        this.resource = resource;
        this.detailName = detailName;
        this.detailDescription = detailDescription;
    }

    public long getResourceDetailID() {
        return resourceDetailID;
    }

    public void setResourceDetailID(long resourceDetailID) {
        this.resourceDetailID = resourceDetailID;
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

    public String getdetailDescription() {
        return detailDescription;
    }

    public void setdetailDescription(String detail) {
        this.detailDescription = detail;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }


    public Date getLatest_updated() {
        return latest_updated;
    }

    public void setLatest_updated(Date latest_updated) {
        this.latest_updated = latest_updated;
    }

    public User getLatest_modified_by() {
        return latest_modified_by;
    }

    public void setLatest_modified_by(User latest_modified_by) {
        this.latest_modified_by = latest_modified_by;
    }

    public void setModifiedInformation(User latest_modified_by, Date latest_updated){
        this.latest_modified_by = latest_modified_by;
        this.latest_updated = latest_updated;
    }

    @Override
    public String toString() {
        return "ResourceDetail{" +
                "resourceDetailID=" + resourceDetailID +
                ", parentResourceID= " + this.resource.getResourceID() +
                ", detailName='" + detailName + '\'' +
                ", detail='" + detailDescription + '\'' +
                ", created_date=" + created_date +
                ", latest_updated=" + latest_updated +
                ", latest_modified_by=" + latest_modified_by +
                '}';
    }
}
