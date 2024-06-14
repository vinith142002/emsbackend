package com.excelr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.Service.ProjectAssignmentService;
import com.excelr.model.Employee;
import com.excelr.model.ProjectAssignment;

@RestController
@RequestMapping("/api/projectassignments")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectAssignmentController {
	 @Autowired
	    private ProjectAssignmentService projectAssignmentService;

	    @GetMapping("/{projectId}")
	    public ResponseEntity<List<Employee>> getAssignedEmployees(@PathVariable Long projectId) {
	        List<Employee> assignedEmployees = projectAssignmentService.getAssignedEmployeesByProjectId(projectId);
	        return ResponseEntity.ok(assignedEmployees);
	    }

	    @PostMapping
	    public ResponseEntity<ProjectAssignment> assignProject(@RequestBody ProjectAssignment projectAssignment) {
	        ProjectAssignment createdAssignment = projectAssignmentService.assignProject(projectAssignment);
	        return ResponseEntity.status(201).body(createdAssignment);
	    }



	    @DeleteMapping
	    public ResponseEntity<Void> deleteProjectAssignment(@RequestParam Long employeeId, @RequestParam Long projectId) {
	        projectAssignmentService.deleteProjectAssignment(employeeId, projectId);
	        return ResponseEntity.noContent().build();
	    }
//	    public ResponseEntity<Void> deleteProjectAssignment(@PathVariable Long id) {
//	        try {
//	            projectAssignmentService.deleteProjectAssignment(id);
//	            return ResponseEntity.noContent().build();
//	        } catch (ResourceNotFoundException e) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	        }
//	    }



}
