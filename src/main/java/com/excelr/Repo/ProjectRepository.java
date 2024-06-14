package com.excelr.Repo;

import com.excelr.model.Projectinfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projectinfo, Long> {
	List<Projectinfo> findByStatus(Projectinfo.ProjectStatus status);

}
