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

   
}
