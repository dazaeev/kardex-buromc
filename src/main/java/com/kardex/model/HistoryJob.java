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
 * Tabla de Historial del puesto y salario
 * Acceso unicamente administrador y usuario
 */
@Entity
@Table(name = "history_job")
public class HistoryJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "job_title")
	private String jobTitle;
	@Basic
	@Column(name = "date_in")
	private String dateIn;
	@Basic
	@Column(name = "date_ou")
	private String dateOu;
	@Basic
	@Column(name = "job_area")
	private String jobArea;
	@Basic
	@Column(name = "immediate_boss")
	private String immediateBoss;
	@Basic
	@Column(name = "salary_in")
	private String salaryIn;
	@Basic
	@Column(name = "salary_ou")
	private String salaryOu;
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
    @JoinColumn(name = "employee_labor_id", nullable = true)
	private EmployeeLabor employeeLabor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDateIn() {
		return dateIn;
	}
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	public String getDateOu() {
		return dateOu;
	}
	public void setDateOu(String dateOu) {
		this.dateOu = dateOu;
	}
	public String getJobArea() {
		return jobArea;
	}
	public void setJobArea(String jobArea) {
		this.jobArea = jobArea;
	}
	public String getImmediateBoss() {
		return immediateBoss;
	}
	public void setImmediateBoss(String immediateBoss) {
		this.immediateBoss = immediateBoss;
	}
	public String getSalaryIn() {
		return salaryIn;
	}
	public void setSalaryIn(String salaryIn) {
		this.salaryIn = salaryIn;
	}
	public String getSalaryOu() {
		return salaryOu;
	}
	public void setSalaryOu(String salaryOu) {
		this.salaryOu = salaryOu;
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
	public EmployeeLabor getEmployeeLabor() {
		return employeeLabor;
	}
	public void setEmployeeLabor(EmployeeLabor employeeLabor) {
		this.employeeLabor = employeeLabor;
	}
}