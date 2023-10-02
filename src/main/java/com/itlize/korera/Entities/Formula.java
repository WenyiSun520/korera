package com.itlize.korera.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

// import java.util.*;


/** 
 * formula detail 
 * extra cell
 */
@Entity
@Table(name="Formula")
public class Formula {

    @Id
    @Column(name="formula_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaId;

    @Column(name="field_name")
    private String fieldName;

    @Column(name="type")
    private ColumnTypeEnum fieldType;

    @Column(name="value")
    private String fieldValue;

    @ManyToOne
    @JoinColumn(name="project_id")
    @JsonBackReference("project-formula")
    private Project project;

    @ManyToOne
    @JoinColumn(name="resourceid")
    @JsonBackReference("resource-formula")
    private Resource resource;

    public Formula(){

    }

    public Formula(String fieldName, ColumnTypeEnum fieldType, String fieldValue, Project project, Resource resource) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
        this.project = project;
        this.resource = resource;
    }

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ColumnTypeEnum getFieldType() {
        return fieldType;
    }

    public void setFieldType(ColumnTypeEnum fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "formulaId=" + formulaId +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                ", fieldValue='" + fieldValue + '\'' +
                ", project=" + project +
                '}';
    }
   
}
