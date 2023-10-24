package com.itlize.korera.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Entity
@Table(name = "RESOURCE")
public class Resource {
    @Id
    @Column(name="resourceid")
    private long resourceID;
    @Column(name="resource_name")
    private String resourceName;
    @OneToMany(mappedBy = "resource",fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("resource-resourcedetail")
    private Set<ResourceDetail> resourceDetails = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "parent_resource_id")
    @JsonBackReference("resource-subresource")
    private Resource parentResource;
    @OneToMany(mappedBy = "parentResource", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("resource-subresource")
    private Set<Resource> subResourceSet = new HashSet<>();

    @OneToMany(mappedBy="resource",orphanRemoval = true)
    @JsonManagedReference("resource-formula")
    private Set<Formula> formulas;

    @JsonIgnore
    @ManyToMany(mappedBy = "resources",cascade = CascadeType.ALL)
    private Set<Project> project = new HashSet<>();

    @Column(name = "created_date")
    private Date created_date;
    @Column(name = "latest_modified_date")
    private Date latest_modified_date;

    @ManyToOne
    @JoinColumn(name = "modified_by_user_id")
    private User latest_modified_by;



    public Resource() {
    }

    public Resource(long resourceID, String resourceName) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
    }

    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public Resource(long resourceID) {
        this.resourceID = resourceID;
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

    public Set<ResourceDetail> getResourceDetails() {
        return resourceDetails;
    }

    public void setResourceDetails(Set<ResourceDetail> resourceDetails) {
        this.resourceDetails = resourceDetails;
    }


    public Set<Resource> getSubResourceSet() {
        return subResourceSet ;
    }

    public void setSubResourceSet(Set<Resource> subResourceSet ) {
        this.subResourceSet  = subResourceSet ;
    }

    public Resource getParentResource() {
        return parentResource;
    }

    public void setParentResource(Resource parentResource) {
        this.parentResource = parentResource;
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

    public User getLatest_modified_by() {
        return latest_modified_by;
    }

    public void setLatest_modified_by(User latest_modified_by) {
        this.latest_modified_by = latest_modified_by;
    }

    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }

    public Set<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(Set<Formula> formulas) {
        this.formulas = formulas;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "ResourceID=" + resourceID +
                ", resourceName='" + resourceName + '\'' +
                ", resourceDetails=" + resourceDetails +
                ", subResourceSet=" + subResourceSet.toString() +
                ", created_date=" + created_date +
                ", latest_modified_date=" + latest_modified_date +
                ", latest_modified_by=" + latest_modified_by +
                ", formulas=" +  formulas +
                '}';
    }

}
