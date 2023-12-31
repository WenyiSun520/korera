package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.ColumnTypeEnum;
import com.itlize.korera.Entities.Formula;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;

// import com.itlize.korera.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;


@Repository
@EnableJpaRepositories
public interface FormulaRepository extends JpaRepository<Formula, Long> {
    List<Formula> findByProject(Project project);
    Formula findByProjectAndFieldNameAndFieldValueAndFieldTypeAndResource(Project project, String fieldName, String value, ColumnTypeEnum type, Resource resource);

    boolean existsFormulasByFormulaId(Long formulaId);
    Integer deleteFormulasByFieldNameAndAndProject(String name, Project project);
    Integer deleteFormulasByResourceAndProject(Resource resource, Project project);
 //  List<Formula> findFormulasByFieldNameAndAndProject(String name, Project project);
 //  List<Formula> findFormulasByProjectAndResource(Project project, Resource resource);

}
