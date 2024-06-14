package com.excelr.Controller;

import java.util.List;

import com.excelr.Service.ProjectService;
import com.excelr.model.Projectinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private ProjectService projectService;

	@GetMapping
	public List<Projectinfo> getAllProjects() {
		logger.info("Fetching all projects");
		return projectService.getAllProjects();
	}

	@GetMapping("/completed")
	public List<Projectinfo> getAllCompletedProjects() {
		return projectService.getAllCompletedProjects();
	}

	@PostMapping
	public ResponseEntity<Projectinfo> createProject(@RequestBody Projectinfo project) {
		Projectinfo createdProject = projectService.saveProject(project);
		return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		System.out.println("Deleting project with ID: " + id);
		projectService.deleteProject(id);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Projectinfo> updateProject(@PathVariable Long id, @RequestBody Projectinfo projectDetails) {
		System.out.println("Updating project with ID: " + id);
		Projectinfo updatedProject = projectService.updateProject(id, projectDetails);
		return ResponseEntity.ok(updatedProject);
	}

}
