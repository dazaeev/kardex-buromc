package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeFilesSystemPersonal;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeFilesSystemPersonalRepository")
public interface EmployeeFilesSystemPersonalRepository extends JpaRepository<EmployeeFilesSystemPersonal, Integer> {
	
	public EmployeeFilesSystemPersonal findById(int id);
}