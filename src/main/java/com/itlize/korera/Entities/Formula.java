package com.itlize.korera.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="Formula")
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formulaId;

    @Column(name="cost_code")
    private Long costCode;

    @Column(name="editable")
    private boolean editable;

    @Column(name="item_id")
    private String itemId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projectId")
    private Project project;


    @OneToMany(mappedBy="formula")
    private List<DataCell> cellValue;



    public Formula(){

    }

    public Formula(Long costCode, boolean editable, String itemId, Project project) {
        this.costCode = costCode;
        this.editable = editable;
        this.itemId = itemId;
        this.project = project;
    }

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemID(String itemId) {
        this.itemId = itemId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }



    @Override
    public String toString() {
        return "Formula{" +
                "formulaId=" + formulaId +
                ", costCode=" + costCode +
                ", editable=" + editable +
                ", itemId='" + itemId + '\'' +
                ", project=" + project +
                '}';
    }
}
