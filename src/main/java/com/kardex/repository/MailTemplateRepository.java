package com.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardex.model.MailTemplate;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Repository("mailTemplateRepository")
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Integer> {
	public MailTemplate findById(int id);
	public MailTemplate findByNameAndActive(String name, int active);
}