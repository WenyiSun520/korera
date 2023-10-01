package com.itlize.korera.Service;


import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;

import java.util.List;
import java.util.Set;

public interface ResourceService {
//   List<Resource> getAllResourceByProjectID(long projectid);



   Set<Resource> getAllSubResourceByParentResource(long resourceID);
   List<Resource> getResourcesByResourceNameContains(String resourceName);


   Resource getResourceByID(long resourceID);

   Boolean saveNewResource(Resource resource, String username);
   Boolean updateResourceName(long resourceId, String username, String newname);
   Boolean updateParentResourceId(long resourceId,String username, long parentResourceId);
   Boolean updateSubResourceSet(long parentResourceId, Resource subResource, String username);

   Boolean deleteResourceByName(String resourceName);
   Boolean deleteResourceByID(long resourceID);



}
