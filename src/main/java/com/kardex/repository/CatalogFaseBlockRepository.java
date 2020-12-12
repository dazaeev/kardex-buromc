package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.CatalogFaseBlock;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("catalogFaseBlockRepository")
public interface CatalogFaseBlockRepository extends JpaRepository<CatalogFaseBlock, Integer> {
	public CatalogFaseBlock findById(int id);
}