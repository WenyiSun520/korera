package com.itlize.korera.Controller;

import com.itlize.korera.DTO.ResourceDTO;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.InvalidInputException;
import com.itlize.korera.Service.ResourceServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/resource")
public class resourceController {
    private final ResourceServiceImpl resourceService;

    @Autowired
    public resourceController(ResourceServiceImpl resourceService){
        this.resourceService = resourceService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllResource(){
        Set<Resource> all = this.resourceService.getAllResource();
        List<ResourceDTO> list = new ArrayList<>();
        for(Resource resource:all){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getResourceID(),resource.getResourceName(),
                    resource.getCreated_date(),resource.getLatest_modified_date(),resource.getLatest_modified_by().getUsername());
            list.add(resourceDTO);

        };
        if(all.isEmpty()) return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/resource_name")
    public ResponseEntity<?> getResourcesContainsName(@RequestParam(value="q") String query){
        if( query.isEmpty()) return this.getAllResource();
        List<Resource> list = this.resourceService.getResourcesByResourceNameContains(query);
        if(list.isEmpty()) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        List<ResourceDTO> result = new ArrayList<>();
        for(Resource resource:list){
            ResourceDTO resourceDTO = new ResourceDTO(resource.getResourceID(),resource.getResourceName(),
                    resource.getCreated_date(),resource.getLatest_modified_date(),resource.getLatest_modified_by().getUsername());
            result.add(resourceDTO);

        };
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/resource_id")
    public ResponseEntity<?> getResourcesByID(@RequestParam(value="q")long query){
        if((Long)  query == null)throw new InvalidInputException("user input is invalid");
        Resource resource= this.resourceService.getResourceByID( query);
        if(resource == null) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/project_id")
    public ResponseEntity<?> getResourcesByProjectID(@RequestParam(value="q")Long query){
        if(query == null)throw new InvalidInputException("user input is invalid");
        Set<Resource> resource= this.resourceService.getAllResourceByProjectId(query);

        if(resource == null) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/project_name")
    public ResponseEntity<?> getResourcesByProjectName(@RequestParam(value="q")String query){
        if(query == null)throw new InvalidInputException("user input is invalid");
        Set<Resource> resource= this.resourceService.getAllResourceByProjectName(query);
        if(resource == null) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        List<ResourceDTO> result = new ArrayList<>();
        for(Resource res :resource) {
            ResourceDTO dto = new ResourceDTO(res.getResourceID(), res.getResourceName(), res.getCreated_date(), res.getLatest_modified_date(), res.getLatest_modified_by().getUsername());
            result.add(dto);
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("available/project_name")
    public ResponseEntity<?> getUnAddedResourcesByProjectName(@RequestParam(value="q")String query){
        if(query == null)throw new InvalidInputException("user input is invalid");
        Set<Resource> resource= this.resourceService.getAllResourceByProjectName(query);
        if(resource == null) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        Set<Resource> allResources = this.resourceService.getAllResource();
        allResources.removeAll(resource);
        List<ResourceDTO> result = new ArrayList<>();
        for(Resource res :allResources) {
            ResourceDTO dto = new ResourceDTO(res.getResourceID(), res.getResourceName(), res.getCreated_date(), res.getLatest_modified_date(), res.getLatest_modified_by().getUsername());
            result.add(dto);
        }

        return ResponseEntity.ok().body(result);

    }
    @GetMapping("/sub_resource")
    public ResponseEntity<?> getSubResourcesByID(@RequestParam(value="q")Long  query){
        if( query == null)throw new InvalidInputException("user input is invalid");
        Set<Resource> set = this.resourceService.getAllSubResourceByParentResource(query);
        if(set.isEmpty()) return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(set);
    }

   @PostMapping("/{username}/{projectId}/add_resource")
    public ResponseEntity<String> addResourceToProject(@PathVariable("username") String username,
                                              @PathVariable("projectId") Long projectId,
                                                       @RequestBody List<Resource> list)
                          {

       if(this.resourceService.saveResourceToProject(list, username, projectId)) {
            return ResponseEntity.ok().body("new resource is saved successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when saving new resource!");
        }
   }

    @PostMapping("/{username}/add_new_resource")
    public ResponseEntity<String> addNewResource(@PathVariable("username") String username,
                                                          @RequestBody Resource resource)
    {
        if(this.resourceService.saveNewResource(resource, username)) {
            return ResponseEntity.ok().body("new resource is saved successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when saving new resource!");
        }
    }

    @PutMapping ("/{username}/{resourceId}/update_resource_name")
    public ResponseEntity<String> updateResourceName(@PathVariable("username") String username,@PathVariable("resourceId") long resourceId, @RequestBody Resource resource){
       String newname = resource.getResourceName();
       if(this.resourceService.updateResourceName(resourceId,username, newname)){
           return ResponseEntity.ok().body("updated resource name is saved successfully!");
       }else{
           return ResponseEntity.status(501).body("Server Error when update resource. id: "+ resourceId);
       }


    }

    @PutMapping ("/{username}/{resourceId}/update_parent_resource")
    public ResponseEntity<String> updateParentResource(@PathVariable("username") String username,@PathVariable("resourceId") long resourceId, @RequestBody Resource resource){
        long parentResourceId = resource.getResourceID();
        if(this.resourceService.updateParentResourceId(resourceId,username, parentResourceId)){
            return ResponseEntity.ok().body("updated resource is saved successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when update resource. id: "+ resourceId);
        }

    }

    @PostMapping ("/{username}/{parentResourceId}/update_subresource_set")
    public ResponseEntity<String> updateSubResourceSet(@PathVariable("username") String username,
                                                 @PathVariable("parentResourceId") long parentResourceId, @RequestBody Resource resource){
       if(this.resourceService.updateSubResourceSet(parentResourceId, resource, username)){
           return ResponseEntity.ok().body("updated resource is saved successfully!");
       }else{
           return ResponseEntity.status(501).body("Server Error when update resource. id: "+ resource.getResourceID());
       }

    }
   @DeleteMapping("/delete_resource/{resourceID}")
   public ResponseEntity<String> deleteResource( @PathVariable long resourceID){
        if( this.resourceService.deleteResourceByID(resourceID)){
            return ResponseEntity.ok().body("new resource is deleted successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when delete resource. id: "+resourceID);
        }


   }
}
