package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CatalogFaseBlockTechnology;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("catalogFaseBlockTechnologyRepository")
public interface CatalogFaseBlockTechnologyRepository extends JpaRepository<CatalogFaseBlockTechnology, Integer> {
	public CatalogFaseBlockTechnology findById(int id);
}