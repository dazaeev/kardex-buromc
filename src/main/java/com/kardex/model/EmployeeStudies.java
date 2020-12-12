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
 * Deberan ser llenados por el usuario y despues solo modificable por administrador 
 * (LLENADO ÚNICA OCASIÓN AL INGRESO A LA EMPRESA, TODA CAPACITACIÓN POSTERIOR SE DEBE REGISTRAR EN EL APARTADO DE CAPACITACIÓN)
 * Estos datos podran ser visibles para el empleado, 
 * pero solo modificables por el administrador
 */
@Entity
@Table(name = "employee_studies")
public class EmployeeStudies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "school_latest")
	private String schoolLatest;
	@Basic
	@Column(name = "educational_level_latest")
	private String educationalLevelLatest;
	@Basic
	@Column(name = "status_latest")
	private String statusLatest;
	@Basic
	@Column(name = "school_previous")
	private String schoolPrevious;
	@Basic
	@Column(name = "educational_level_previous")
	private String educationalLevelPrevious;
	@Basic
	@Column(name = "status_previous")
	private String statusPrevious;
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
	// Se cambia a "FetchType.EAGER" para obtener los datos de open_certifications
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER,
    		mappedBy = "employeeStudies")
	private Set<OpenCertifications> openCertifications;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeStudies")
	private Set<CloseCertifications> closeCertifications;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolLatest() {
		return schoolLatest;
	}
	public void setSchoolLatest(String schoolLatest) {
		this.schoolLatest = schoolLatest;
	}
	public String getEducationalLevelLatest() {
		return educationalLevelLatest;
	}
	public void setEducationalLevelLatest(String educationalLevelLatest) {
		this.educationalLevelLatest = educationalLevelLatest;
	}
	public String getStatusLatest() {
		return statusLatest;
	}
	public void setStatusLatest(String statusLatest) {
		this.statusLatest = statusLatest;
	}
	public String getSchoolPrevious() {
		return schoolPrevious;
	}
	public void setSchoolPrevious(String schoolPrevious) {
		this.schoolPrevious = schoolPrevious;
	}
	public String getEducationalLevelPrevious() {
		return educationalLevelPrevious;
	}
	public void setEducationalLevelPrevious(String educationalLevelPrevious) {
		this.educationalLevelPrevious = educationalLevelPrevious;
	}
	public String getStatusPrevious() {
		return statusPrevious;
	}
	public void setStatusPrevious(String statusPrevious) {
		this.statusPrevious = statusPrevious;
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
	public Set<OpenCertifications> getOpenCertifications() {
		return openCertifications;
	}
	public void setOpenCertifications(Set<OpenCertifications> openCertifications) {
		this.openCertifications = openCertifications;
	}
	public Set<CloseCertifications> getCloseCertifications() {
		return closeCertifications;
	}
	public void setCloseCertifications(Set<CloseCertifications> closeCertifications) {
		this.closeCertifications = closeCertifications;
	}
}