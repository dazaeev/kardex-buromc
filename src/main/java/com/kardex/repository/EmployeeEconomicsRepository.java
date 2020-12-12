package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeEconomics;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeEconomicsRepository")
public interface EmployeeEconomicsRepository extends JpaRepository<EmployeeEconomics, Integer> {
	
	public EmployeeEconomics findById(int id);
}