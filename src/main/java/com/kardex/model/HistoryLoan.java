package com.kardex.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Tabla de Historial de prestamos
 * Acceso unicamente administrador y usuario
 */
@Entity
@Table(name = "history_loan")
public class HistoryLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "date_loan")
	private String dateLoan;
	@Basic
	@Column(name = "amount_loan")
	private String amountLoan;
	@Basic
	@Column(name = "concept_loan")
	private String conceptLoan;
	@Basic
	@Column(name = "status")
	private String status;
	@Basic
	@Column(name = "active")
	private int active;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date date;
	@Column
	@UpdateTimestamp
	private Date modified;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_economics_id", nullable = true)
	private EmployeeEconomics employeeEconomics;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateLoan() {
		return dateLoan;
	}
	public void setDateLoan(String dateLoan) {
		this.dateLoan = dateLoan;
	}
	public String getAmountLoan() {
		return amountLoan;
	}
	public void setAmountLoan(String amountLoan) {
		this.amountLoan = amountLoan;
	}
	public String getConceptLoan() {
		return conceptLoan;
	}
	public void setConceptLoan(String conceptLoan) {
		this.conceptLoan = conceptLoan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
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
	public EmployeeEconomics getEmployeeEconomics() {
		return employeeEconomics;
	}
	public void setEmployeeEconomics(EmployeeEconomics employeeEconomics) {
		this.employeeEconomics = employeeEconomics;
	}
}