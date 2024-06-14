package com.excelr.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Projectinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String projectName;
	private String clientName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reportingManager;
	private String technicalLead;

	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getTechnicalLead() {
		return technicalLead;
	}

	public void setTechnicalLead(String technicalLead) {
		this.technicalLead = technicalLead;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public enum ProjectStatus {
		UNKNOWN,
		ONGOING,
		COMPLETED
	}
}
