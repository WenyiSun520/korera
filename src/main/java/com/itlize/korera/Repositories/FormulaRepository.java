package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface FormulaRepository extends JpaRepository<Formula, Long> {

    //save a formula


    //getAllFormulasByProjectId(Long id)


    //getFormulaByResourceId(Long id)

    //updateFormula(String id, Formula formula)


    //deleteFormula(Formula id)
}
