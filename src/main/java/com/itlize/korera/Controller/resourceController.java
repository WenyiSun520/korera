package com.itlize.korera.Controller;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;
import com.itlize.korera.ErrorHandler.InvalidInputException;
import com.itlize.korera.Service.ResourceServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<List<Resource>> getAllResource(){
        List<Resource> list = this.resourceService.getAllResource();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/resource_name")
    public ResponseEntity<List<Resource>> getResourcesContainsName(@RequestParam(value="q") String query){
        if( query.isEmpty())throw new InvalidInputException("user input is invalid");
        List<Resource> list = this.resourceService.getResourcesByResourceNameContains( query);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/resource_id")
    public ResponseEntity<Resource> getResourcesByID(@RequestParam(value="q")long query){
        if((Long)  query == null)throw new InvalidInputException("user input is invalid");
        Resource resource= this.resourceService.getResourceByID( query);
        return ResponseEntity.ok().body(resource);
    }
    @GetMapping("/sub_resource")
    public ResponseEntity<Set<Resource>> getSubResourcesByID(@RequestParam(value="q")Long  query){
        if( query == null)throw new InvalidInputException("user input is invalid");
        Set<Resource> set = this.resourceService.getAllSubResourceByParentResource( query);
        return ResponseEntity.ok().body(set);
    }

   @PostMapping("/{username}/{projectId}/add_new_resource")
    public ResponseEntity<String> addResource(@PathVariable("username") String username,
                                              @PathVariable("projectId") Long projectId,
                                              @RequestBody Resource resource)
                          {
        //System.out.println(resource);

       if(this.resourceService.saveNewResource(resource, username, projectId)) {
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
   @DeleteMapping("/delete_resource/{projectID}/{resourceID}")
   public ResponseEntity<String> deleteResource(@PathVariable Long projectID, @PathVariable long resourceID){
        if( this.resourceService.deleteResourceByID(projectID,resourceID)){
            return ResponseEntity.ok().body("new resource is deleted successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when delete resource. id: "+resourceID);
        }


   }
}
