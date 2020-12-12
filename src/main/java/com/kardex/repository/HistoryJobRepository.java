package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.HistoryJob;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("historyJob")
public interface HistoryJobRepository extends JpaRepository<HistoryJob, Integer> {
	public HistoryJob findById(int id);
}