package com.kardex.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Auditoria
 */
@Entity
@Table(name = "audit")
public class Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	@Lob
	private String name;
	@Basic
	@Column(name = "audit_operation")
	@Lob
	private String auditOperation;
	@CreationTimestamp
	private Date date;
	@Column
	@UpdateTimestamp
	private Date modified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuditOperation() {
		return auditOperation;
	}
	public void setAuditOperation(String auditOperation) {
		this.auditOperation = auditOperation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
}