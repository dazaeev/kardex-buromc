package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CertificationTrack;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("certificationTrackRepository")
public interface CertificationTrackRepository extends JpaRepository<CertificationTrack, Integer> {
	
	public CertificationTrack findById(int id);
	public CertificationTrack findByArea(String area);
}