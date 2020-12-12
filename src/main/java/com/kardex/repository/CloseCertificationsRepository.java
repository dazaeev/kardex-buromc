package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CloseCertifications;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("closeCertificationsRepository")
public interface CloseCertificationsRepository extends JpaRepository<CloseCertifications, Integer> {
	public CloseCertifications findById(int id);
}