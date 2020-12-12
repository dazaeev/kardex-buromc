package com.kardex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.EmployeeNotification;
import com.kardex.model.User;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("employeeNotificationRepository")
public interface EmployeeNotificationRepository extends JpaRepository<EmployeeNotification, Integer> {
	
	public EmployeeNotification findById(int id);
	public List<EmployeeNotification> findByUser(User user);
}