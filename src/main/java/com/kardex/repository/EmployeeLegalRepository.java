package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeLegal;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeLegalRepository")
public interface EmployeeLegalRepository extends JpaRepository<EmployeeLegal, Integer> {
	
	public EmployeeLegal findById(int id);
}