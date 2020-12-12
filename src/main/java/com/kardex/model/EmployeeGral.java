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
 * (LLENADO ÚNICA OCASIÓN AL INGRESO A LA EMPRESA) 
 */
@Entity
@Table(name = "employee_gral")
public class EmployeeGral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Basic
	@Column(name = "birthdate")
	private String birthdate;
	
	@Basic
	@Column(name = "sex")
	private String sex;
	
	@Basic
	@Column(name = "civil_status")
	private String civilStatus;
	
	@Basic
	@Column(name = "nationality")
	private String nationality;
	
	@Basic
	@Column(name = "email_personal")
	private String emailPersonal;
	
	@Basic
	@Column(name = "imss")
	private String imss;
	
	@Basic
	@Column(name = "military_primer")
	private String militaryPrimer;
	
	@Basic
	@Column(name = "phone")
	private String phone;
	
	@Basic
	@Column(name = "cell_phone")
	private String cellPhone;
	
	@Basic
	@Column(name = "emergency_phone")
	private String emergencyPhone;
	
	@Basic
	@Column(name = "emergency_phone_call")
	private String emergencyPhoneCall;
	
	@Basic
	@Column(name = "rfc")
	private String rfc;
	
	@Basic
	@Column(name = "curp")
	private String curp;
	
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
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employeeGral")
	private EmployeeStudies employeeStudies;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employeeGral")
	private EmployeeDemographics employeeDemographics;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeGral")
	private Set<EmployeeWorkExperience> employeeWorkExperience;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employeeGral")
	private EmployeeLabor employeeLabor;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employeeGral")
	private EmployeeEconomics employeeEconomics;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeGral")
	private Set<EmployeeLegal> employeeLegal;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeGral")
	private Set<EmployeeTraining> employeeTraining;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeGral")
	private Set<EmployeeFilesSystemPersonal> employeeFilesSystemPersonal;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "employeeGral")
	private Set<EmployeeFilesSystemRh> employeeFilesSystemRh;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER,
    		mappedBy = "employeeGral")
	private Set<RequestOfCourses> requestOfCourse;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getImss() {
		return imss;
	}

	public void setImss(String imss) {
		this.imss = imss;
	}

	public String getMilitaryPrimer() {
		return militaryPrimer;
	}

	public void setMilitaryPrimer(String militaryPrimer) {
		this.militaryPrimer = militaryPrimer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getEmergencyPhoneCall() {
		return emergencyPhoneCall;
	}

	public void setEmergencyPhoneCall(String emergencyPhoneCall) {
		this.emergencyPhoneCall = emergencyPhoneCall;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmployeeStudies getEmployeeStudies() {
		return employeeStudies;
	}

	public void setEmployeeStudies(EmployeeStudies employeeStudies) {
		this.employeeStudies = employeeStudies;
	}

	public EmployeeDemographics getEmployeeDemographics() {
		return employeeDemographics;
	}

	public void setEmployeeDemographics(EmployeeDemographics employeeDemographics) {
		this.employeeDemographics = employeeDemographics;
	}

	public Set<EmployeeWorkExperience> getEmployeeWorkExperience() {
		return employeeWorkExperience;
	}

	public void setEmployeeWorkExperience(Set<EmployeeWorkExperience> employeeWorkExperience) {
		this.employeeWorkExperience = employeeWorkExperience;
	}

	public EmployeeLabor getEmployeeLabor() {
		return employeeLabor;
	}

	public void setEmployeeLabor(EmployeeLabor employeeLabor) {
		this.employeeLabor = employeeLabor;
	}

	public EmployeeEconomics getEmployeeEconomics() {
		return employeeEconomics;
	}

	public void setEmployeeEconomics(EmployeeEconomics employeeEconomics) {
		this.employeeEconomics = employeeEconomics;
	}

	public Set<EmployeeLegal> getEmployeeLegal() {
		return employeeLegal;
	}

	public void setEmployeeLegal(Set<EmployeeLegal> employeeLegal) {
		this.employeeLegal = employeeLegal;
	}

	public Set<EmployeeTraining> getEmployeeTraining() {
		return employeeTraining;
	}

	public void setEmployeeTraining(Set<EmployeeTraining> employeeTraining) {
		this.employeeTraining = employeeTraining;
	}

	public Set<EmployeeFilesSystemPersonal> getEmployeeFilesSystemPersonal() {
		return employeeFilesSystemPersonal;
	}

	public void setEmployeeFilesSystemPersonal(Set<EmployeeFilesSystemPersonal> employeeFilesSystemPersonal) {
		this.employeeFilesSystemPersonal = employeeFilesSystemPersonal;
	}

	public Set<EmployeeFilesSystemRh> getEmployeeFilesSystemRh() {
		return employeeFilesSystemRh;
	}

	public void setEmployeeFilesSystemRh(Set<EmployeeFilesSystemRh> employeeFilesSystemRh) {
		this.employeeFilesSystemRh = employeeFilesSystemRh;
	}

	public Set<RequestOfCourses> getRequestOfCourse() {
		return requestOfCourse;
	}

	public void setRequestOfCourse(Set<RequestOfCourses> requestOfCourse) {
		this.requestOfCourse = requestOfCourse;
	}
}