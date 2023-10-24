package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
@EnableJpaRepositories
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    //Get
    Resource getResourceByResourceName(String resource_name);
    Resource getResourceByResourceID(long resourceID);
    List<Resource> getResourcesByResourceNameContainsIgnoreCase(String name);

   Boolean existsResourceByResourceName(String resource_name);

   Set<Resource> getResourcesByParentResource(Resource parentResource);


    //bug:
    //void updateResourceBySubResourceList(List<Resource> subResourceList);

}
