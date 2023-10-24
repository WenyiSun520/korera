package com.itlize.korera.Controller;

import com.itlize.korera.DTO.ResourceDetailDTO;
import com.itlize.korera.Entities.Resource;
import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.ErrorHandler.InvalidInputException;
import com.itlize.korera.Repositories.ResourceDetailRepository;
import com.itlize.korera.Repositories.ResourceRepository;
import com.itlize.korera.Service.ResourceDetailServiceImpl;
import com.itlize.korera.Service.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/resource_detail")
public class resourceDetailController {
    private final ResourceDetailServiceImpl resourceDetailService;
    private final ResourceServiceImpl resourceService;
    private final ResourceRepository resourceRepository;
    private final ResourceDetailRepository resourceDetailRepository;

    @Autowired
    public resourceDetailController(ResourceDetailServiceImpl resourceDetailService, ResourceServiceImpl resourceService, ResourceRepository resourceRepository, ResourceDetailRepository resourceDetailRepository) {
        this.resourceDetailService = resourceDetailService;
        this.resourceService = resourceService;
        this.resourceRepository = resourceRepository;
        this.resourceDetailRepository = resourceDetailRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllResourceDetailByResourceDetailName() {
        Map<String, List<ResourceDetailDTO>> map =  new HashMap<>();
        Set<String> distinctDetailSet = this.resourceDetailService.getDistinctDetailTypes();
        for ( String type:distinctDetailSet) {

            List<ResourceDetailDTO> list = this.resourceDetailService.getResourceDetailsByResourceDetailName(type);
            map.put(type, list);
        }

        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/search_by_id")
    public ResponseEntity<Set<ResourceDetail>> getResourceDetailByResourceID(@RequestParam(value = "q") long  query) {
        if((Long)query == null)throw new InvalidInputException("user input is invalid");
        Set<ResourceDetail> s = this.resourceDetailService.getResourceDetailsByResourceId(query);
        return ResponseEntity.ok().body(s);
    }

    @GetMapping("/search_by_name")
    public ResponseEntity<?> getResourceDetailByResourceName(@RequestParam(value = "q") String query) {
        if( query.isEmpty()) return this.getAllResourceDetailByResourceDetailName();

        Map<String, List<ResourceDetailDTO>> map =  new HashMap<>();
        Set<String> distinctDetailSet = this.resourceDetailService.getDistinctDetailTypes();
        List<ResourceDetailDTO> list = this.resourceDetailService.getResourceDetailsByResourceName(query);

        for ( ResourceDetailDTO detail:list) {

            if(!map.containsKey(detail.getDetailName())) {
                map.put(detail.getDetailName(), new ArrayList<ResourceDetailDTO>());
            }
            map.get(detail.getDetailName()).add(detail);
        }

        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/{username}/{resourceId}/save_new_resource_detail")
    public ResponseEntity<String> saveResourceDetail(@PathVariable("username") String username, @PathVariable("resourceId") long resourceId, @RequestBody ResourceDetail resourceDetail) {
        if (this.resourceDetailService.addResourceDetail(resourceDetail, username, resourceId)) {
            return ResponseEntity.ok().body("new resourceDetail is saved successfully!");
        }
        return ResponseEntity.status(501).body("Server Error when saving new resourceDetail!");
    }

    @PutMapping("/{username}/{resourceId}/update_resource_detail")
    public ResponseEntity<?> updateResourceDetail(@PathVariable("username") String username, @PathVariable("resourceId") long resourceId, @RequestBody ResourceDetail resourceDetail) {
        if (this.resourceDetailService.updateResourceDetail(resourceDetail, username, resourceId)) {

            return ResponseEntity.ok().body("resourceDetail is updated successfully!");
        }
        return ResponseEntity.status(501).body("Server Error when updating resourceDetail!");
    }

    @DeleteMapping("/{resourceId}/delete_resource/{resourceDetailId}")
    public ResponseEntity<String> deleteResourceDetail(@PathVariable("resourceId") long resourceId, @PathVariable("resourceDetailId") long resourceDetailId) {
        if (this.resourceDetailService.deleteResourceDetail(resourceDetailId, resourceId)) {
            return ResponseEntity.ok().body("resourceDetail is removed successfully!");
        }
        return ResponseEntity.status(501).body("Server Error when removing resourceDetail!");

    }
}
