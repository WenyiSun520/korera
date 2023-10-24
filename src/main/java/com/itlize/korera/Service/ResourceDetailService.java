package com.itlize.korera.Service;

import com.itlize.korera.DTO.ResourceDetailDTO;
import com.itlize.korera.Entities.ResourceDetail;

import java.util.List;
import java.util.Set;

public interface ResourceDetailService {
    Set<ResourceDetail> getResourceDetailsByResourceId(long resourceId);
    List<ResourceDetailDTO> getResourceDetailsByResourceName(String resourceName);

    List<ResourceDetailDTO> getResourceDetailsByResourceDetailName(String resourceDetailName);



    ResourceDetail getResourceDetailByResourceDetailName(String resourceDetailName);


    Boolean addResourceDetail(ResourceDetail resourceDetail, String username, long resourceId);

    Boolean updateResourceDetail(ResourceDetail resourceDetail, String username, long resourceId);

    Boolean deleteResourceDetail(long resourceDetailId, long resourceId);

}
