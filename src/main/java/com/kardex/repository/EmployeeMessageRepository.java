package com.kardex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeMessage;
import com.kardex.model.User;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeMessageRepository")
public interface EmployeeMessageRepository extends JpaRepository<EmployeeMessage, Integer> {
	
	public EmployeeMessage findById(int id);
	public EmployeeMessage findByUserAndName(User user, String name);
	public List<EmployeeMessage> findByUser(User user);
}