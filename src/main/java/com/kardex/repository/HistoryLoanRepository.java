package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.HistoryLoan;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("historyLoanRepository")
public interface HistoryLoanRepository extends JpaRepository<HistoryLoan, Integer> {
	public HistoryLoan findById(int id);
}