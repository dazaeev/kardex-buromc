package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.HistoryBonus;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("historyBonusRepository")
public interface HistoryBonusRepository extends JpaRepository<HistoryBonus, Integer> {
	public HistoryBonus findById(int id);
}