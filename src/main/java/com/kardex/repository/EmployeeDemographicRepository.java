package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeDemographics;

/**
 * @author brodriguez
 *
 */
@Repository("employeeDemographicRepository")
public interface EmployeeDemographicRepository extends JpaRepository<EmployeeDemographics, Integer> {
}