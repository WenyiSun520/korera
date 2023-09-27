package com.itlize.korera.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="resourceid")
    private long ResourceID;
    @Column(name="resource_name")
    private String resourceName;
    @OneToMany(mappedBy = "resource",fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<ResourceDetail> resourceDetails = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_resource_id")
    private Resource parentResource;
    @OneToMany(mappedBy = "parentResource", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resource> subResourceSet = new HashSet<>();



    public Resource() {
    }

    public Resource(long resourceID, String resourceName) {
        this.ResourceID = resourceID;
        this.resourceName = resourceName;
    }

    public long getResourceID() {
        return ResourceID;
    }

    public void setResourceID(long resourceID) {
        ResourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        resourceName = resourceName;
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

    public void setSubResourceList(Set<Resource> subResourceSet ) {
        this.subResourceSet  = subResourceSet ;
    }

    public Resource getParentResource() {
        return parentResource;
    }

    public void setParentResource(Resource parentResource) {
        this.parentResource = parentResource;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "ResourceID=" + ResourceID +
                ", ResourceName='" + resourceName + '\'' +
                ", subResourceList=" +subResourceSet +
                '}';
    }
}
