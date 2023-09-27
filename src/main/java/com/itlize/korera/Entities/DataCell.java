package com.itlize.korera.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Data Cell")
public class DataCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataCellId;


    private String cellValue;


    @ManyToOne
    @JoinColumn(name="columnId")
    private ColumnExtra columnExtra;


    @ManyToOne
    @JoinColumn(name="cellValue")
    private Formula formula;
}
