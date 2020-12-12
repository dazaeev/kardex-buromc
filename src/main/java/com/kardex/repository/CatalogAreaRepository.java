package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CatalogArea;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("catalogAreaRepository")
public interface CatalogAreaRepository extends JpaRepository<CatalogArea, Integer> {
	
	public CatalogArea findById(int id);
	public CatalogArea findByValue(String value);
}