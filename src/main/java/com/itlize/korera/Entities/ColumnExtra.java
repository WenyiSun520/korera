package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Column")
public class ColumnExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    private String fieldName;

    private ColumnTypeEnum type;

    private String formula;


    @ManyToOne()
    @JoinColumn(name="formulaId")
    private Formula formulaObj;

    @OneToMany(mappedBy="columnExtra")
    private List<DataCell> cellValue;

    public ColumnExtra(){

    }

    public ColumnExtra(String fieldName, ColumnTypeEnum type, String formula, Formula formulaObj) {
        this.fieldName = fieldName;
        this.type = type;
        this.formula = formula;
        this.formulaObj = formulaObj;
    }

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ColumnTypeEnum getType() {
        return type;
    }

    public void setType(ColumnTypeEnum type) {
        this.type = type;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Formula getFormulaObj() {
        return formulaObj;
    }

    public void setFormulaObj(Formula formulaObj) {
        this.formulaObj = formulaObj;
    }

    @Override
    public String toString() {
        return "ColumnExtra{" +
                "columnId=" + columnId +
                ", fieldName='" + fieldName + '\'' +
                ", type=" + type +
                ", formula='" + formula + '\'' +
                ", formulaObj=" + formulaObj +
                '}';
    }
}
