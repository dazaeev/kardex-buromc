package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeStudies;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeStudiesRepository")
public interface EmployeeStudiesRepository extends JpaRepository<EmployeeStudies, Integer> {
}