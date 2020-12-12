package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.Role;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);
	public Role findById(int id);
}
