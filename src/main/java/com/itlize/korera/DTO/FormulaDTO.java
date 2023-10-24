package com.itlize.korera.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;
import jakarta.persistence.*;

public class FormulaDTO {
    private Long formulaId;
    private String fieldName;

    private ColumnTypeEnum fieldType;

    private String fieldValue;

    private Long projectId;

    private Long resourceId;

    public FormulaDTO(Long formulaId, String fieldName, ColumnTypeEnum fieldType, String fieldValue, Long projectId, Long resourceId) {
        this.formulaId = formulaId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
        this.projectId = projectId;
        this.resourceId = resourceId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
