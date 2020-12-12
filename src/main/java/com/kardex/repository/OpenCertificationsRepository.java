package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.OpenCertifications;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("openCertificationsRepository")
public interface OpenCertificationsRepository extends JpaRepository<OpenCertifications, Integer> {
	public OpenCertifications findById(int id);
}