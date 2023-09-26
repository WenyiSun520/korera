package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="Formula")
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaID;

    @Column(name="cost_code")
    private Long costCode;

    @Column(name="editable")
    private boolean editable;

    @Column(name="item_id")
    private String itemID;

    @OneToOne(mappedBy="formula")
    private Project project;

    @OneToMany(mappedBy="formulaTable", cascade = CascadeType.ALL)
    private List<QuantitySurveyFields> quantitySurveyFieldsList;

    public Formula(){

    }

    public Formula(Long costCode, boolean editable, String itemID, Project project, List<QuantitySurveyFields> quantitySurveyFieldsList) {
        this.costCode = costCode;
        this.editable = editable;
        this.itemID = itemID;
        this.project = project;
        this.quantitySurveyFieldsList = quantitySurveyFieldsList;
    }

    public Long getFormulaID() {
        return formulaID;
    }

    public void setFormulaID(Long formulaID) {
        this.formulaID = formulaID;
    }

    public Long getCostCode() {
        return costCode;
    }

    public void setCostCode(Long costCode) {
        this.costCode = costCode;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<QuantitySurveyFields> getQuantitySurveyFieldsList() {
        return quantitySurveyFieldsList;
    }

    public void setQuantitySurveyFieldsList(List<QuantitySurveyFields> quantitySurveyFieldsList) {
        this.quantitySurveyFieldsList = quantitySurveyFieldsList;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "formulaID=" + formulaID +
                ", costCode=" + costCode +
                ", editable=" + editable +
                ", itemID='" + itemID + '\'' +
                ", project=" + project +
                ", quantitySurveyFieldsList=" + quantitySurveyFieldsList +
                '}';
    }
}
