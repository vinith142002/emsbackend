package com.excelr.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excelr.Repo.ProjectAssignmentRepository;
import com.excelr.Repo.ProjectRepository;
import com.excelr.model.Employee;
import com.excelr.model.ProjectAssignment;
import com.excelr.model.Projectinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectAssignmentRepository projectAssignmentRepository;

	public List<Projectinfo> getAllProjects() {
		return projectRepository.findAll();
	}

	public Projectinfo saveProject(Projectinfo project) {
		return projectRepository.save(project);
	}

	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}



	public Projectinfo updateProject(Long id, Projectinfo projectDetails) {
		Optional<Projectinfo> optionalProject = projectRepository.findById(id);
		if (optionalProject.isPresent()) {
			Projectinfo existingProject = optionalProject.get();
			existingProject.setProjectName(projectDetails.getProjectName());
			existingProject.setClientName(projectDetails.getClientName());
			existingProject.setStartDate(projectDetails.getStartDate());
			existingProject.setEndDate(projectDetails.getEndDate());
			existingProject.setReportingManager(projectDetails.getReportingManager());
			existingProject.setTechnicalLead(projectDetails.getTechnicalLead());
	
			existingProject.setStatus(projectDetails.getStatus()); // Set status
			return projectRepository.save(existingProject);
		} else {
			throw new RuntimeException("Project not found with id " + id);
		}
	}
	
	
	public Projectinfo getProjectById(Long id) {
		Optional<Projectinfo> optionalProject = projectRepository.findById(id);
		return optionalProject.orElse(null);
	}

	public List<Employee> getAssignedEmployees(Long projectId) {
		List<ProjectAssignment> projectAssignments = projectAssignmentRepository.findByProjectId(projectId);
		List<Employee> assignedEmployees = new ArrayList<>();
		for (ProjectAssignment assignment : projectAssignments) {
			assignedEmployees.add(assignment.getEmployee());
		}
		return assignedEmployees;
	}

	// New method to get all completed projects
	public List<Projectinfo> getAllCompletedProjects() {
		return projectRepository.findByStatus(Projectinfo.ProjectStatus.COMPLETED);
	}

}
