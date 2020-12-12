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
 * Tabla de Certificaciones Vigentes
 * Acceso unicamente administrador y usuario
 */
@Entity
@Table(name = "open_certifications")
public class OpenCertifications {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "description")
	private String description;
	@Basic
	@Column(name = "certification")
	private String certification;
	@Basic
	@Column(name = "name_certification")
	private String nameCertification;
	@Basic
	@Column(name = "date_expiration")
	private String dateExpiration;
	@Basic
	@Column(name = "sending_expired_mail")
	private int sendingExpiredMail;
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
    @JoinColumn(name = "employee_studies_id", nullable = true)
	private EmployeeStudies employeeStudies;
	
	// Contenido archivos -----------------------------
	@Transient private String fileCertification;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getNameCertification() {
		return nameCertification;
	}

	public void setNameCertification(String nameCertification) {
		this.nameCertification = nameCertification;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public int getSendingExpiredMail() {
		return sendingExpiredMail;
	}

	public void setSendingExpiredMail(int sendingExpiredMail) {
		this.sendingExpiredMail = sendingExpiredMail;
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

	public EmployeeStudies getEmployeeStudies() {
		return employeeStudies;
	}

	public void setEmployeeStudies(EmployeeStudies employeeStudies) {
		this.employeeStudies = employeeStudies;
	}

	public String getFileCertification() {
		return fileCertification;
	}

	public void setFileCertification(String fileCertification) {
		this.fileCertification = fileCertification;
	}
}