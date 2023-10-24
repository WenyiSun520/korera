package com.itlize.korera.Service;

import com.itlize.korera.DTO.ResourceDTO;
import com.itlize.korera.DTO.UserDTO;
import com.itlize.korera.Entities.Project;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.PathVariableNotFound;
import com.itlize.korera.Repositories.ProjectRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;


    public ResourceServiceImpl(ResourceRepository resourceRepository, UserRepository userRepository,
                               ProjectRepository projectRepository) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


//    @Override
//    public List<Resource> getAllResourceByProjectID(long projectid) {
//        this.resourceRepository.
//        return null;
//    }

    @Override
    public Set<Resource> getAllResource() {
        List<Resource> allResourceList =  this.resourceRepository.findAll();
        Set<Resource> allResourceSet = new HashSet<>(allResourceList);

       return allResourceSet;
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
    public Set<Resource> getAllResourceByProjectId(Long projectID) {
        if(!this.projectRepository.existsById(projectID)){
            throw new PathVariableNotFound("projectID");
        }
        Project project = this.projectRepository.getProjectByProjectId(projectID);

        return project.getResources();
    }
    @Override
    public Set<Resource> getAllResourceByProjectName(String projectName) {
        if(!this.projectRepository.existsByProjectName(projectName)){
            throw new PathVariableNotFound("projectName");
        }
        Project project = this.projectRepository.getProjectByProjectName(projectName);

        return project.getResources();
    }

    @Override
    public Set<Resource> getAllSubResourceByParentResource(long resourceID) {
        Resource parentResource = this.resourceRepository.getResourceByResourceID(resourceID);
        if( parentResource == null) throw new PathVariableNotFound("parentResource");
        Set<Resource> set = this.resourceRepository.getResourcesByParentResource(parentResource);
        return set;
    }

    @Override
    public Boolean saveResourceToProject(List<Resource> list, String username, Long projectId) {
        User user = this.userRepository.findByUsername(username);
        Project project = this.projectRepository.getProjectByProjectId(projectId);

        if(user == null || project == null) throw new PathVariableNotFound("username or projectId");

        for(Resource re: list){
              if(!this.resourceRepository.existsResourceByResourceName(re.getResourceName())){
               throw new PathVariableNotFound("resource");
              }
              Resource resource = this.resourceRepository.getResourceByResourceName(re.getResourceName());

              resource.setLatest_modified_date(new Date());
              resource.setLatest_modified_by(user);
              resource.getProject().add(project);
              project.getResources().add(resource);
              this.resourceRepository.save(resource);
        }
          project.setLastModified(new Date());
          return true;

    }

    @Override
    public Boolean saveNewResource(Resource resource, String username) {
        User user = this.userRepository.findByUsername(username);
        System.out.println("in reosurceserviceimpl:"+resource.getResourceID());
        if(user == null) throw new PathVariableNotFound("username or projectId");
        try {
            resource.setResourceID(resource.getResourceID());
            resource.setCreated_date(new Date());
            resource.setLatest_modified_date(new Date());
            resource.setLatest_modified_by(user);
            this.resourceRepository.save(resource);
            return true;
        }catch(Exception e){
            System.out.println("Error when save new resource: "+e);

        }
        return false;
    }

    @Override
    public Boolean updateResourceName(long resourceId, String username, String newname) {
        User user = this.userRepository.findByUsername(username);
        Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
        if(user == null || resource == null) throw new PathVariableNotFound("username or resourceId");


        try{
            resource.setResourceName(newname);
            resource.setLatest_modified_by(user);
            resource.setLatest_modified_date(new Date());
            this.resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println("Error when updating resource name: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateParentResourceId(long resourceId, String username, long parentResourceId) {
        User user = this.userRepository.findByUsername(username);
        Resource resource = this.resourceRepository.getResourceByResourceID(resourceId);
        Resource parentResource = this.resourceRepository.getResourceByResourceID(parentResourceId);
        if(user == null || resource == null || parentResource == null) throw new PathVariableNotFound("username or resourceId");

        try{
            resource.setParentResource(parentResource);
            resource.setLatest_modified_by(user);
            resource.setLatest_modified_date(new Date());
            this.resourceRepository.save(resource);
        }catch(Exception e){
            System.out.println("Error when updating parent resource: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateSubResourceSet(long parentResourceId, Resource subResource, String username){
        if(!this.resourceRepository.existsById(parentResourceId)) {
            throw new PathVariableNotFound("parentResourceId");
        }
        Resource parentResource = this.resourceRepository.getReferenceById(parentResourceId);
        User user = this.userRepository.findByUsername((username));
        if(user == null) throw new PathVariableNotFound("username");
        Set<Resource> subResourceSet = parentResource.getSubResourceSet();
        try {
            subResource.setCreated_date(new Date());
            subResource.setLatest_modified_date(new Date());
            subResource.setLatest_modified_by(user);
            subResource.setParentResource(parentResource);

            subResourceSet.add(subResource);
            this.resourceRepository.save(parentResource);
        }catch(Exception e){
            System.out.println("Error when updating subresource set: "+e);
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

       // Project project = this.projectRepository.getProjectByProjectId(projectID);
     //   if(resource == null || project == null) throw new PathVariableNotFound("resourceID or projectID");
        try{
            if(resource.getResourceDetails().size() != 0){
                for(ResourceDetail resourceDetail:resource.getResourceDetails() ){
                    resourceDetail.setLatest_modified_by(null);
                }
            }
            resource.setLatest_modified_by(null);
            System.out.println(resource);
       //     project.getResources().remove(resource);
        //    this.projectRepository.save(project)
            this.resourceRepository.delete(resource);
        }catch(Exception e){
            System.out.println("Error when deleting resource by id: "+e);
            return false;
        }

        return true;
    }
}

