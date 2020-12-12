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
 * Tabla de Vacaciones del empleado
 * Acceso unicamente administrador y usuario
 */
@Entity
@Table(name = "employee_vacations")
public class EmployeeVacations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "days_taken")
	private String daysTaken;
	@Basic
	@Column(name = "days_for_year")
	private String daysForYear;
	@Basic
	@Column(name = "days_pending")
	private String daysPending;
	@Basic
	@Column(name = "reason_of_exit")
	private String reasonOfExit;
	@Basic
	@Column(name = "holiday")
	private String holiday;
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
	public String getDaysTaken() {
		return daysTaken;
	}
	public void setDaysTaken(String daysTaken) {
		this.daysTaken = daysTaken;
	}
	public String getDaysForYear() {
		return daysForYear;
	}
	public void setDaysForYear(String daysForYear) {
		this.daysForYear = daysForYear;
	}
	public String getDaysPending() {
		return daysPending;
	}
	public void setDaysPending(String daysPending) {
		this.daysPending = daysPending;
	}
	public String getReasonOfExit() {
		return reasonOfExit;
	}
	public void setReasonOfExit(String reasonOfExit) {
		this.reasonOfExit = reasonOfExit;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
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