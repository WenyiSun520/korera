package com.itlize.korera.Controller;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Service.ResourceServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/resource_name")
    public ResponseEntity<List<Resource>> getResourcesContainsName(@RequestParam(value="resource_name") String name){

        List<Resource> list = this.resourceService.getResourcesByResourceNameContains(name);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/")
    public ResponseEntity<Resource> getResourcesByID(@RequestParam(value="resource_id")long resourceID){
        Resource resource= this.resourceService.getResourceByID(resourceID);
        return ResponseEntity.ok().body(resource);
    }
    @GetMapping("/sub_resource")
    public ResponseEntity<Set<Resource>> getSubResourcesByID(@RequestParam(value="resource_id")long resourceID){
        Set<Resource> set = this.resourceService.getAllSubResourceByParentResource(resourceID);
        return ResponseEntity.ok().body(set);
    }

   @PostMapping("/{username}/add_new_resource")
    public ResponseEntity<String> addResource(@PathVariable("username") String username, @RequestBody Resource resource){
        //System.out.println(resource);
        if(this.resourceService.saveNewResource(resource, username)) {
            return ResponseEntity.ok().body("new resource is saved successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when saving new resource!");
        }
   }

//    @PutMapping ("/{username}/update_resource")
//    public ResponseEntity<String> updateResource(@PathVariable("username") String username, @RequestBody Resource resource){
//       if(this.resourceService.updateResource(resource,username)){
//           return ResponseEntity.ok().body("updated resource is saved successfully!");
//       }else{
//           return ResponseEntity.status(501).body("Server Error when update resource. id: "+ resource.getResourceID());
//       }
//
//    }
   @DeleteMapping("/delete_resource/{resourceID}")
   public ResponseEntity<String> deleteResource(@PathVariable long resourceID){
        if( this.resourceService.deleteResourceByID(resourceID)){
            return ResponseEntity.ok().body("new resource is deleted successfully!");
        }else{
            return ResponseEntity.status(501).body("Server Error when delete resource. id: "+resourceID);
        }


   }
}
