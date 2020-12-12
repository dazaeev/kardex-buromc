package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeGral;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeGralRepository")
public interface EmployeeGralRepository extends JpaRepository<EmployeeGral, Integer> {
	public EmployeeGral findById(int id);
}