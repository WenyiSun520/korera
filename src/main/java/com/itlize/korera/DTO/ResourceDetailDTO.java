package com.itlize.korera.DTO;

import com.itlize.korera.Entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class ResourceDetailDTO {
    private long detailID;
    private String detailName;
    private String detailDescription;
    private Date created_date;
    private Date latest_updated;

    private String latest_modified_by;

    private long resourceID;

    private String resourceName;
    public ResourceDetailDTO(long detailID, String detailName, String detailDescription, Date created_date, Date latest_updated, String latest_modified_by, long resourceID, String resourceName) {
        this.detailID = detailID;
        this.detailName = detailName;
        this.detailDescription = detailDescription;
        this.created_date = created_date;
        this.latest_updated = latest_updated;
        this.latest_modified_by = latest_modified_by;
        this.resourceID = resourceID;
        this.resourceName = resourceName;
    }

    public long getDetailID() {
        return detailID;
    }

    public void setDetailID(long detailID) {
        this.detailID = detailID;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
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

    public String getLatest_modified_by() {
        return latest_modified_by;
    }

    public void setLatest_modified_by(String latest_modified_by) {
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
}
