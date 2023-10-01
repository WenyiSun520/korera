package com.itlize.korera.Controller;

import com.itlize.korera.Entities.ResourceDetail;
import com.itlize.korera.ErrorHandler.InvalidInputException;
import com.itlize.korera.Service.ResourceDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/resource_detail")
public class resourceDetailController {
    private final ResourceDetailServiceImpl resourceDetailService;

    @Autowired
    public resourceDetailController(ResourceDetailServiceImpl resourceDetailService) {
        this.resourceDetailService = resourceDetailService;
    }

    @GetMapping("/search_by_id")
    public ResponseEntity<Set<ResourceDetail>> getResourceDetailByResourceID(@RequestParam(value = "q") long  query) {
        if((Long)query == null)throw new InvalidInputException("user input is invalid");
        Set<ResourceDetail> s = this.resourceDetailService.getResourceDetailsByResourceId( query);
        return ResponseEntity.ok().body(s);
    }

    @GetMapping("/search_by_name")
    public ResponseEntity<String> getResourceDetailByResourceName(@RequestParam(value = "q") String query) {
        if( query.isEmpty())throw new InvalidInputException("user input is invalid");
        Set<ResourceDetail> set = this.resourceDetailService.getResourceDetailsByResourceName( query);
        return ResponseEntity.ok().body(set.toString());
    }

    @PostMapping("/{username}/{resourceId}/save_new_resource_detail")
    public ResponseEntity<String> saveResourceDetail(@PathVariable("username") String username, @PathVariable("resourceId") long resourceId, @RequestBody ResourceDetail resourceDetail) {
        if (this.resourceDetailService.addResourceDetail(resourceDetail, username, resourceId)) {
            return ResponseEntity.ok().body("new resourceDetail is saved successfully!");
        }
        return ResponseEntity.status(501).body("Server Error when saving new resourceDetail!");
    }

    @PutMapping("/{username}/{resourceId}/update_resource_detail")
    public ResponseEntity<String> updateResourceDetail(@PathVariable("username") String username, @PathVariable("resourceId") long resourceId, @RequestBody ResourceDetail resourceDetail) {
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
