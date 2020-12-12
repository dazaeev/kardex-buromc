package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeVacations;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeVacations")
public interface EmployeeVacationsRepository extends JpaRepository<EmployeeVacations, Integer> {
	public EmployeeVacations findById(int id);
}