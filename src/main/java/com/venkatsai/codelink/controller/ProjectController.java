package com.venkatsai.codelink.controller;

import com.venkatsai.codelink.model.Project;
import com.venkatsai.codelink.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/me")
    public ResponseEntity<?> createProject(@RequestHeader("user_id") Long user_id, @RequestBody Project project) {
        Project project1 = projectService.createProject(user_id,project);
        if(project1!=null){
            return ResponseEntity.ok(project1);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getProject(@RequestHeader("user_id") Long user_id) {
        List<Project> projectList = projectService.getProjects(user_id);
        if(projectList==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(projectList);
    }
}
