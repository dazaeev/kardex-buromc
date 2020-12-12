package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.Audit;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("auditRepository")
public interface AuditRepository extends JpaRepository<Audit, Long> {
}