package com.itlize.korera.Service;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;


    public ResourceServiceImpl(ResourceRepository resourceRepository, UserRepository userRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }


//    @Override
//    public List<Resource> getAllResourceByProjectID(long projectid) {
//        this.resourceRepository.
//        return null;
//    }


    public List<Resource> getAllResource() {
        return this.resourceRepository.findAll();
    }
    @Override
    public List<Resource> getResourcesByResourceNameContains(String resourceName) {
        return this.resourceRepository.getResourcesByResourceNameContainsIgnoreCase(resourceName);
    }

    @Override
    public Resource getResourceByID(long resourceID) {
        return this.resourceRepository.getResourceByResourceID(resourceID);
    }

    @Override
    public Set<Resource> getAllSubResourceByParentResource(long resourceID) {
        Resource parentResource = this.resourceRepository.getResourceByResourceID(resourceID);
        Set<Resource> set = this.resourceRepository.getResourcesByParentResource(parentResource);
        return set;
    }

    @Override
    public Boolean saveNewResource(Resource resource, String username) {
        User user = this.userRepository.findByUsername(username);
        if(user == null) throw new PathVariableNotFound("username");
        String resourceName = resource.getResourceName();
        if(this.resourceRepository.existsResourceByResourceName(resourceName)){
            return false;
        }else{
            resource.setCreated_date(new Date());
            resource.setLatest_modified_date(new Date());
            resource.setLatest_modified_by(user);
            this.resourceRepository.save(resource);
        }
        return true;
    }

    @Override
    public Boolean updateResource(Resource resource, String username) {
        User user = this.userRepository.findByUsername(username);
        if(user == null) throw new PathVariableNotFound("username");
        long resourceID = resource.getResourceID();
        Resource oldResource = this.resourceRepository.getResourceByResourceID(resourceID);
        if(oldResource != null) {
            oldResource.setResourceName(resource.getResourceName());
            oldResource.setParentResource(resource.getParentResource());
            oldResource.setLatest_modified_date(new Date());
            oldResource.setLatest_modified_by(user);
            oldResource.setResourceDetails(resource.getResourceDetails());
            oldResource.setSubResourceSet(resource.getSubResourceSet());
            this.resourceRepository.save(oldResource);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteResourceByName(String resourceName) {
        Resource resource = this.resourceRepository.getResourceByResourceName(resourceName);
        if(resource == null) throw new PathVariableNotFound("resourceName");
        this.resourceRepository.delete(resource);
        return true;
    }

    @Override
    public Boolean deleteResourceByID(long resourceID) {
        Resource resource = this.resourceRepository.getResourceByResourceID(resourceID);
        if(resource == null) throw new PathVariableNotFound("resourceID");
        try{
            this.resourceRepository.delete(resource);
        }catch(Exception e){
            System.out.println("Error when deleting resource by id: "+e);
            return false;
        }

        return true;
    }
}

