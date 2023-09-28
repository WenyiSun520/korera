package com.itlize.korera.Service;

import com.itlize.korera.Entities.ResourceDetail;

import java.util.Set;

public interface ResourceDetailService {
    Set<ResourceDetail> getResourceDetailsByResourceId(long resourceId);
    Set<ResourceDetail> getResourceDetailsByResourceName(String resourceName);

    ResourceDetail getResourceDetailByResourceDetailName(String resourceDetailName);

    Boolean addResourceDetail(ResourceDetail resourceDetail, String username, long resourceId);

    Boolean updateResourceDetail(ResourceDetail resourceDetail, String username, long resourceId);

    Boolean deleteResourceDetail(long resourceDetailId, long resourceId);

}
