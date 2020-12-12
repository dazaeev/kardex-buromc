package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.AccessReset;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("accessResetRepository")
public interface AccessResetRepository extends JpaRepository<AccessReset, Integer> {
	
	public AccessReset findById(int id);
	public AccessReset findByEmailAndToken(String email, String token);
}