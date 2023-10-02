package com.itlize.korera.Controller;

import com.itlize.korera.Entities.Resource;
import com.itlize.korera.ErrorHandler.InvalidInputException;
import com.itlize.korera.Service.ProjectResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/api/project_resource")
public class projectResourceController {
    private final ProjectResourceService projectResourceService;
    public projectResourceController(ProjectResourceService projectResourceService) {
        this.projectResourceService = projectResourceService;
    }

    @GetMapping("/get_resource_by_projectId")
    public ResponseEntity<Set<Resource>> getResourceByProjectId(@RequestParam(value= "q") Long projectId){
        if(projectId == null) throw new InvalidInputException("projectId");
        Set<Resource> set = this.projectResourceService.getResourceByProject(projectId);
        return ResponseEntity.ok().body(set);
    }
}
