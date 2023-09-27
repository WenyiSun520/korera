package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.Formula;
// import com.itlize.korera.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@EnableJpaRepositories
public interface FormulaRepository extends JpaRepository<Formula, Long> {

    //save a formula

    //getAllFormulasByProjectId(Long id)
    List<Formula> getFormulasByProjectId(Long projectId);

    //getFormulaByResourceId(Long id)
    Formula get(Long id);

    //updateFormula(Long projectId, Formula formula)
    void update(Long projectId, Formula formula);

    //deleteFormula(Long formulaId)
    void delete(Long formulaId);
}
