package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    //Get
    Resource getResourceByResourceName(String resource_name);

   Boolean existsResourceByResourceName(String resource_name);

    //bug:
    //void updateResourceBySubResourceList(List<Resource> subResourceList);

}
