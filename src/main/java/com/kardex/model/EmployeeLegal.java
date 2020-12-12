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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Empleados
 * Acceso unicamente administrador
 */
@Entity
@Table(name = "employee_legal")
public class EmployeeLegal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "administrative_actas_attention")
	private String administrativeActasAttention;
	@Basic
	@Column(name = "discharge_date")
	private String dischargeDate;
	@Basic
	@Column(name = "reason_low")
	private String reasonLow;
	@Basic
	@Column(name = "password_generated_rrhh")
	private String passwordGeneratedRrhh;
	@Basic
	@Column(name = "training_promissory_notes")
	private String trainingPromissoryNotes;
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
	// Contenido archivos
	@Transient
	private String fileAdministrativeActasAttention;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdministrativeActasAttention() {
		return administrativeActasAttention;
	}
	public void setAdministrativeActasAttention(String administrativeActasAttention) {
		this.administrativeActasAttention = administrativeActasAttention;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getReasonLow() {
		return reasonLow;
	}
	public void setReasonLow(String reasonLow) {
		this.reasonLow = reasonLow;
	}
	public String getPasswordGeneratedRrhh() {
		return passwordGeneratedRrhh;
	}
	public void setPasswordGeneratedRrhh(String passwordGeneratedRrhh) {
		this.passwordGeneratedRrhh = passwordGeneratedRrhh;
	}
	public String getTrainingPromissoryNotes() {
		return trainingPromissoryNotes;
	}
	public void setTrainingPromissoryNotes(String trainingPromissoryNotes) {
		this.trainingPromissoryNotes = trainingPromissoryNotes;
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
	public String getFileAdministrativeActasAttention() {
		return fileAdministrativeActasAttention;
	}
	public void setFileAdministrativeActasAttention(String fileAdministrativeActasAttention) {
		this.fileAdministrativeActasAttention = fileAdministrativeActasAttention;
	}
}