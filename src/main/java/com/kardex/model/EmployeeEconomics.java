package com.kardex.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Empleados
 * Acceso unicamente administrador
 */
@Entity
@Table(name = "employee_economics")
public class EmployeeEconomics {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "salary")
	private String salary;
	@Basic
	@Column(name = "date_infonavit")
	private String dateInfonavit;
	@Basic
	@Column(name = "amount_infonavit")
	private String amountInfonavit;
	@Basic
	@Column(name = "date_alimony")
	private String dateAlimony;
	@Basic
	@Column(name = "amount_alimony")
	private String amountAlimony;
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
    @JoinColumn(name = "employee_gral_id", nullable = true)
	private EmployeeGral employeeGral;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeEconomics")
	private Set<HistoryBonus> historyBonus;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeEconomics")
	private Set<HistoryLoan> historyLoan;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDateInfonavit() {
		return dateInfonavit;
	}
	public void setDateInfonavit(String dateInfonavit) {
		this.dateInfonavit = dateInfonavit;
	}
	public String getAmountInfonavit() {
		return amountInfonavit;
	}
	public void setAmountInfonavit(String amountInfonavit) {
		this.amountInfonavit = amountInfonavit;
	}
	public String getDateAlimony() {
		return dateAlimony;
	}
	public void setDateAlimony(String dateAlimony) {
		this.dateAlimony = dateAlimony;
	}
	public String getAmountAlimony() {
		return amountAlimony;
	}
	public void setAmountAlimony(String amountAlimony) {
		this.amountAlimony = amountAlimony;
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
	public EmployeeGral getEmployeeGral() {
		return employeeGral;
	}
	public void setEmployeeGral(EmployeeGral employeeGral) {
		this.employeeGral = employeeGral;
	}
	public Set<HistoryBonus> getHistoryBonus() {
		return historyBonus;
	}
	public void setHistoryBonus(Set<HistoryBonus> historyBonus) {
		this.historyBonus = historyBonus;
	}
	public Set<HistoryLoan> getHistoryLoan() {
		return historyLoan;
	}
	public void setHistoryLoan(Set<HistoryLoan> historyLoan) {
		this.historyLoan = historyLoan;
	}
}