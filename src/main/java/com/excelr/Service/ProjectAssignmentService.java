package com.excelr.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.Repo.ProjectAssignmentRepository;
import com.excelr.model.Employee;
import com.excelr.model.ProjectAssignment;

@Service
public class ProjectAssignmentService {
	@Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;
    public ProjectAssignment assignProject(ProjectAssignment projectAssignment) {
        return projectAssignmentRepository.save(projectAssignment);
    }
    public List<Employee> getAssignedEmployeesByProjectId(Long projectId) {
        List<ProjectAssignment> assignments = projectAssignmentRepository.findByProjectId(projectId);
        return assignments.stream()
                .map(ProjectAssignment::getEmployee)
                .collect(Collectors.toList());
    }

    public void deleteProjectAssignment(Long employeeId, Long projectId) {
        projectAssignmentRepository.deleteByEmployeeIdAndProjectId(employeeId, projectId);
    }


}
