package com.itlize.korera.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.itlize.korera.Entities.User;
import jakarta.persistence.*;

import java.util.*;

public class ResourceDTO {

    private long resourceID;

    private String resourceName;
    private Date created_date;
    private Date latest_modified_date;

    private String latest_modified_by;

    public ResourceDTO(long resourceID, String resourceName,Date created_date, Date latest_modified_date, String latest_modified_by) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.created_date = created_date;
        this.latest_modified_date = latest_modified_date;
        this.latest_modified_by = latest_modified_by;
    }

    public long getResourceID() {
        return resourceID;
    }

    public void setResourceID(long resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLatest_modified_date() {
        return latest_modified_date;
    }

    public void setLatest_modified_date(Date latest_modified_date) {
        this.latest_modified_date = latest_modified_date;
    }

    public String getLatest_modified_by() {
        return latest_modified_by;
    }

    public void setLatest_modified_by(String latest_modified_by) {
        this.latest_modified_by = latest_modified_by;
    }
}
