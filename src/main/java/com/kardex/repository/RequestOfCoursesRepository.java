package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.RequestOfCourses;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("requestOfCoursesRepository")
public interface RequestOfCoursesRepository extends JpaRepository<RequestOfCourses, Integer> {
	public RequestOfCourses findById(int id);
}