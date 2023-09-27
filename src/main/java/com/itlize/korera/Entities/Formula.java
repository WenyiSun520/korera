package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="Formula")
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaId;

    @Column(name="field Name")
    private String fieldName;

    @Column(name="type")
    private ColumnTypeEnum fieldType;

    @Column(name="value")
    private String fieldValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projectId")
    private Project project;


    @OneToMany(mappedBy="formula", cascade = CascadeType.ALL)
    private List<ResourceDetail> resourceDetailList = new ArrayList<>();

    public Formula(){

    }

    public Formula(String fieldName, ColumnTypeEnum fieldType, String fieldValue, Project project) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
        this.project = project;
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

    public List<ResourceDetail> getResourceDetailList() {
        return resourceDetailList;
    }

    public void setResourceDetailList(ResourceDetail resourceDetail) {
        this.resourceDetailList.add(resourceDetail);
    }

    public void setResourceDetailList(List<ResourceDetail> resourceDetailList) {
        this.resourceDetailList = resourceDetailList;
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
