package com.itlize.korera.Repositories;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDetailRepository extends JpaRepository<ResourceDetail, Long> {


    List<ResourceDetail> findResourceDetailsByResource(Resource resource);
    List<ResourceDetail> findResourceDetailsByDetailName(String detailname);
    ResourceDetail findResourceDetailByResourceDetailID(long resourceDetailId);
    ResourceDetail findResourceDetailByDetailName(String detailName);

}
