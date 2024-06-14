package com.excelr.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.CompletedProject;

@Repository
public interface CompletedProjectRepository extends JpaRepository<CompletedProject, Long>{

}
