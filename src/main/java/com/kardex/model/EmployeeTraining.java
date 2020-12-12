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
 * Tabla de Empleados
 * Lo debe subir el usuario, pero no debe poder borrarlo una vez ya integrado. 
 * El usuario es responsable de mantenerlo actualizado.
 */
@Entity
@Table(name = "employee_training")
public class EmployeeTraining {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "soft_competitions")
	private String softCompetitions;
	@Basic
	@Column(name = "technical_skills")
	private String technicalSkills;
	@Basic
	@Column(name = "other_expos_conferences")
	private String otherExposConferences;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSoftCompetitions() {
		return softCompetitions;
	}
	public void setSoftCompetitions(String softCompetitions) {
		this.softCompetitions = softCompetitions;
	}
	public String getTechnicalSkills() {
		return technicalSkills;
	}
	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}
	public String getOtherExposConferences() {
		return otherExposConferences;
	}
	public void setOtherExposConferences(String otherExposConferences) {
		this.otherExposConferences = otherExposConferences;
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
}