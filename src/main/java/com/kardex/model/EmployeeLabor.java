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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Empleados
 * Solo visibles, no modificables para el usuario. Llenados por administrador
 */
@Entity
@Table(name = "employee_labor")
public class EmployeeLabor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "date_admission")
	private String dateAdmission;
	@Basic
	@Column(name = "area")
	private String area;
	@Basic
	@Column(name = "policy_sgmm")
	private String policySgmm;
	@Basic
	@Column(name = "employee_number")
	private String employeeNumber;
	@Basic
	@Column(name = "business_mail")
	private String businessMail;
	@Basic
	@Column(name = "position")
	private String position;
	@Basic
	@Column(name = "active")
	private int active;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date date;
	@Column
	@UpdateTimestamp
	private Date modified;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_gral", nullable = true)
	private EmployeeGral employeeGral;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeLabor")
	private Set<HistoryJob> historyJob;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeLabor")
	private Set<EmployeeVacations> employeeVacations;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateAdmission() {
		return dateAdmission;
	}
	public void setDateAdmission(String dateAdmission) {
		this.dateAdmission = dateAdmission;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPolicySgmm() {
		return policySgmm;
	}
	public void setPolicySgmm(String policySgmm) {
		this.policySgmm = policySgmm;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getBusinessMail() {
		return businessMail;
	}
	public void setBusinessMail(String businessMail) {
		this.businessMail = businessMail;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public Set<HistoryJob> getHistoryJob() {
		return historyJob;
	}
	public void setHistoryJob(Set<HistoryJob> historyJob) {
		this.historyJob = historyJob;
	}
	public Set<EmployeeVacations> getEmployeeVacations() {
		return employeeVacations;
	}
	public void setEmployeeVacations(Set<EmployeeVacations> employeeVacations) {
		this.employeeVacations = employeeVacations;
	}
}