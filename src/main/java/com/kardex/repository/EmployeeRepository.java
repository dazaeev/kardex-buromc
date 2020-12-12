package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeGral;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<EmployeeGral, Integer> {
}