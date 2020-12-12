package com.kardex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeLabor;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 */
@Repository("employeeLaborRepository")
public interface EmployeeLaborRepository extends JpaRepository<EmployeeLabor, Integer> {
	
	public EmployeeLabor findById(int id);
	public List<EmployeeLabor> findByArea(String id);
}