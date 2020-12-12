package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CatalogWorkPlace;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("catalogWorkPlaceRepository")
public interface CatalogWorkPlaceRepository extends JpaRepository<CatalogWorkPlace, Integer> {
	public CatalogWorkPlace findById(int id);
}