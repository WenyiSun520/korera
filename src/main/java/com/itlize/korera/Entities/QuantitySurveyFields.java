package com.itlize.korera.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Quantity_Survey_Fields")
public class QuantitySurveyFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantitySurveyFieldsID;

    @Column(name="Quantity_Survey_Fields_Name")
    private String quantitySurveyFieldsName;

    @Column(name="Quantity_Survey_Fields_Type")
    private String quantitySurveyFieldsType;

    @Column(name="Quantity_Survey_Fields_Formula")
    private String quantitySurveyFieldsFormula;

    @ManyToOne
    @JoinColumn(name="formulaID")
    private Formula formulaTable;

    public QuantitySurveyFields(){

    }

    public QuantitySurveyFields(String quantitySurveyFieldsName, String quantitySurveyFieldsType, String quantitySurveyFieldsFormula, Formula formulaTable) {
        this.quantitySurveyFieldsName = quantitySurveyFieldsName;
        this.quantitySurveyFieldsType = quantitySurveyFieldsType;
        this.quantitySurveyFieldsFormula = quantitySurveyFieldsFormula;
        this.formulaTable = formulaTable;
    }

    public Long getQuantitySurveyFieldsID() {
        return quantitySurveyFieldsID;
    }

    public void setQuantitySurveyFieldsID(Long quantitySurveyFieldsID) {
        this.quantitySurveyFieldsID = quantitySurveyFieldsID;
    }

    public String getQuantitySurveyFieldsName() {
        return quantitySurveyFieldsName;
    }

    public void setQuantitySurveyFieldsName(String quantitySurveyFieldsName) {
        this.quantitySurveyFieldsName = quantitySurveyFieldsName;
    }

    public String getQuantitySurveyFieldsType() {
        return quantitySurveyFieldsType;
    }

    public void setQuantitySurveyFieldsType(String quantitySurveyFieldsType) {
        this.quantitySurveyFieldsType = quantitySurveyFieldsType;
    }

    public String getQuantitySurveyFieldsFormula() {
        return quantitySurveyFieldsFormula;
    }

    public void setQuantitySurveyFieldsFormula(String quantitySurveyFieldsFormula) {
        this.quantitySurveyFieldsFormula = quantitySurveyFieldsFormula;
    }

    public Formula getFormulaTable() {
        return formulaTable;
    }

    public void setFormulaTable(Formula formulaTable) {
        this.formulaTable = formulaTable;
    }

    @Override
    public String toString() {
        return "QuantitySurveyFields{" +
                "quantitySurveyFieldsID=" + quantitySurveyFieldsID +
                ", quantitySurveyFieldsName='" + quantitySurveyFieldsName + '\'' +
                ", quantitySurveyFieldsType='" + quantitySurveyFieldsType + '\'' +
                ", quantitySurveyFieldsFormula='" + quantitySurveyFieldsFormula + '\'' +
                ", formulaTable=" + formulaTable +
                '}';
    }
}