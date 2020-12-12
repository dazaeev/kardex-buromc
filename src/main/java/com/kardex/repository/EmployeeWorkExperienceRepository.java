package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeWorkExperience;

/**
 * @author brodriguez
 *
 */
@Repository("employeeWorkExperienceRepository")
public interface EmployeeWorkExperienceRepository extends JpaRepository<EmployeeWorkExperience, Integer> {
	public EmployeeWorkExperience findById(int id);
}