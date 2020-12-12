package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.Parameter;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("parameterRepository")
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
	public Parameter findById(int id);
	public Parameter findByName(String name);
	public Parameter findByValueAndActive(String value, int active);
}