package com.itlize.korera.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

public class ResourceDTO {

    private long resourceID;

    private String resourceName;
    private Set<String> resourceDetails = new HashSet<>();

    private int parentResourceId;
    private Set<String> subResourceSet = new HashSet<>();

    private Set<String> formulas = new HashSet<>();

    private Set<String> project = new HashSet<>();

    private Date created_date;
    private Date latest_modified_date;

    private User latest_modified_by;

    public ResourceDTO(long resourceID, String resourceName, Set<String> resourceDetails, int parentResourceId, Set<String> subResourceSet, Set<String> formulas, Set<String> project, Date created_date, Date latest_modified_date, User latest_modified_by) {
        this.resourceID = resourceID;
        this.resourceName = resourceName;
        this.resourceDetails = resourceDetails;
        this.parentResourceId = parentResourceId;
        this.subResourceSet = subResourceSet;
        this.formulas = formulas;
        this.project = project;
        this.created_date = created_date;
        this.latest_modified_date = latest_modified_date;
        this.latest_modified_by = latest_modified_by;
    }
}
