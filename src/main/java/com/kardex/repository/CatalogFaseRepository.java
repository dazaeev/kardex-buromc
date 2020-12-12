package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CatalogFase;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("catalogFaseRepository")
public interface CatalogFaseRepository extends JpaRepository<CatalogFase, Integer> {
	public CatalogFase findById(int id);
}