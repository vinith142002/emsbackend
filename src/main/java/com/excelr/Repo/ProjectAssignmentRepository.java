package com.excelr.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Employee;
import com.excelr.model.ProjectAssignment;

import jakarta.transaction.Transactional;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
	List<ProjectAssignment> findByProjectId(Long projectId);
    List<Employee> findAssignedEmployeesByProjectId(Long projectId);

    @Transactional
    void deleteByEmployeeIdAndProjectId(Long employeeId, Long projectId);


}
