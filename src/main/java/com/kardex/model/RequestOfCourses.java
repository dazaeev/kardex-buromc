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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Tabla de solicitud de cursos
 * Acceso unicamente administrador y usuario
 */
@Entity
@Table(name = "request_of_courses")
@XmlRootElement(name = "request_of_courses") // JAXB
public class RequestOfCourses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	//C
	@Basic
	@Column(name = "name_request_program")
	private String nameRequestProgram;
	@Basic
	@Column(name = "duration")
	private String duration;
	@Basic
	@Column(name = "modality")
	private String modality;
	@Basic
	@Column(name = "name_provider")
	private String nameProvider;
	@Basic
	@Column(name = "rfc_provider")
	private String rfcProvider;
	@Basic
	@Column(name = "tax_residence")
	private String taxResidence;
	@Basic
	@Column(name = "contact_info")
	private String contactInfo;
	@Basic
	@Column(name = "start_date")
	private String startDate;
	@Basic
	@Column(name = "end_date")
	private String endDate;
	@Basic
	@Column(name = "place_curse")
	private String placeCurse;
	@Basic
	@Column(name = "cost_wo_tax")
	private String costWoTax;
	@Basic
	@Column(name = "cost_w_tax")
	private String costWTax;
	@Basic
	@Column(name = "objetives_course")
	private String objetivesCourse;
	@Basic
	@Column(name = "objetives_course_related_job_place")
	private String objetivesCourseRelatedJobPlace;
	@Basic
	@Column(name = "technical_justification")
	private String technicalJustification;
	//D
	@Basic
	@Column(name = "company_1")
	private String company1;
	@Basic
	@Column(name = "justification_1")
	private String justification1;
	@Basic
	@Column(name = "company_2")
	private String company2;
	@Basic
	@Column(name = "justification_2")
	private String justification2;
	@Basic
	@Column(name = "company_3")
	private String company3;
	@Basic
	@Column(name = "justification_3")
	private String justification3;
	@Basic
	@Column(name = "approved")
	private int approved;
	@Basic
	@Column(name = "name_certification")
	private String nameCertification;
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
	
	// Contenido plantilla -----------------------------
	@Transient private String dateRequest;
	@Transient private String fullNameRequest;
    @Transient private String rfcRequest;
    @Transient private String rfcAddress;
    @Transient private String rfcPhone;
    @Transient private String rfcMobile;
    @Transient private String rfcMail;
    // Contenido archivos -----------------------------
 	@Transient private String fileCertification;
	
	public int getId() {
		return id;
	}
	@XmlElement(name = "id") // JAXB
	public void setId(int id) {
		this.id = id;
	}
	public String getNameRequestProgram() {
		return nameRequestProgram;
	}
	@XmlElement(name = "nameRequestProgram") // JAXB
	public void setNameRequestProgram(String nameRequestProgram) {
		this.nameRequestProgram = nameRequestProgram;
	}
	public String getDuration() {
		return duration;
	}
	@XmlElement(name = "duration") // JAXB
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getModality() {
		return modality;
	}
	@XmlElement(name = "modality") // JAXB
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getNameProvider() {
		return nameProvider;
	}
	@XmlElement(name = "nameProvider") // JAXB
	public void setNameProvider(String nameProvider) {
		this.nameProvider = nameProvider;
	}
	public String getRfcProvider() {
		return rfcProvider;
	}
	@XmlElement(name = "rfcProvider") // JAXB
	public void setRfcProvider(String rfcProvider) {
		this.rfcProvider = rfcProvider;
	}
	public String getTaxResidence() {
		return taxResidence;
	}
	@XmlElement(name = "taxResidence") // JAXB
	public void setTaxResidence(String taxResidence) {
		this.taxResidence = taxResidence;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	@XmlElement(name = "contactInfo") // JAXB
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getStartDate() {
		return startDate;
	}
	@XmlElement(name = "startDate") // JAXB
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	@XmlElement(name = "endDate") // JAXB
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPlaceCurse() {
		return placeCurse;
	}
	@XmlElement(name = "placeCurse") // JAXB
	public void setPlaceCurse(String placeCurse) {
		this.placeCurse = placeCurse;
	}
	public String getCostWoTax() {
		return costWoTax;
	}
	@XmlElement(name = "costWoTax") // JAXB
	public void setCostWoTax(String costWoTax) {
		this.costWoTax = costWoTax;
	}
	public String getCostWTax() {
		return costWTax;
	}
	@XmlElement(name = "costWTax") // JAXB
	public void setCostWTax(String costWTax) {
		this.costWTax = costWTax;
	}
	public String getObjetivesCourse() {
		return objetivesCourse;
	}
	@XmlElement(name = "objetivesCourse") // JAXB
	public void setObjetivesCourse(String objetivesCourse) {
		this.objetivesCourse = objetivesCourse;
	}
	public String getObjetivesCourseRelatedJobPlace() {
		return objetivesCourseRelatedJobPlace;
	}
	@XmlElement(name = "objetivesCourseRelatedJobPlace") // JAXB
	public void setObjetivesCourseRelatedJobPlace(String objetivesCourseRelatedJobPlace) {
		this.objetivesCourseRelatedJobPlace = objetivesCourseRelatedJobPlace;
	}
	public String getTechnicalJustification() {
		return technicalJustification;
	}
	@XmlElement(name = "technicalJustification") // JAXB
	public void setTechnicalJustification(String technicalJustification) {
		this.technicalJustification = technicalJustification;
	}
	public String getCompany1() {
		return company1;
	}
	@XmlElement(name = "company1") // JAXB
	public void setCompany1(String company1) {
		this.company1 = company1;
	}
	public String getJustification1() {
		return justification1;
	}
	@XmlElement(name = "justification1") // JAXB
	public void setJustification1(String justification1) {
		this.justification1 = justification1;
	}
	public String getCompany2() {
		return company2;
	}
	@XmlElement(name = "company2") // JAXB
	public void setCompany2(String company2) {
		this.company2 = company2;
	}
	public String getJustification2() {
		return justification2;
	}
	@XmlElement(name = "justification2") // JAXB
	public void setJustification2(String justification2) {
		this.justification2 = justification2;
	}
	public String getCompany3() {
		return company3;
	}
	@XmlElement(name = "company3") // JAXB
	public void setCompany3(String company3) {
		this.company3 = company3;
	}
	public String getJustification3() {
		return justification3;
	}
	@XmlElement(name = "justification3") // JAXB
	public void setJustification3(String justification3) {
		this.justification3 = justification3;
	}
	public String getNameCertification() {
		return nameCertification;
	}
	public void setNameCertification(String nameCertification) {
		this.nameCertification = nameCertification;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getActive() {
		return active;
	}
	@XmlElement(name = "active") // JAXB
	public void setActive(int active) {
		this.active = active;
	}
	public Date getDate() {
		return date;
	}
	@XmlElement(name = "date") // JAXB
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
	// Contenido plantilla -----------------------------
	public String getDateRequest() {
		return dateRequest;
	}
	@XmlElement(name = "dateRequest") // JAXB
	public void setDateRequest(String dateRequest) {
		this.dateRequest = dateRequest;
	}
	public String getFullNameRequest() {
		return fullNameRequest;
	}
	@XmlElement(name = "fullNameRequest") // JAXB
	public void setFullNameRequest(String fullNameRequest) {
		this.fullNameRequest = fullNameRequest;
	}
	public String getRfcRequest() {
		return rfcRequest;
	}
	@XmlElement(name = "rfcRequest") // JAXB
	public void setRfcRequest(String rfcRequest) {
		this.rfcRequest = rfcRequest;
	}
	public String getRfcAddress() {
		return rfcAddress;
	}
	@XmlElement(name = "rfcAddress") // JAXB
	public void setRfcAddress(String rfcAddress) {
		this.rfcAddress = rfcAddress;
	}
	public String getRfcPhone() {
		return rfcPhone;
	}
	@XmlElement(name = "rfcPhone") // JAXB
	public void setRfcPhone(String rfcPhone) {
		this.rfcPhone = rfcPhone;
	}
	public String getRfcMobile() {
		return rfcMobile;
	}
	@XmlElement(name = "rfcMobile") // JAXB
	public void setRfcMobile(String rfcMobile) {
		this.rfcMobile = rfcMobile;
	}
	public String getRfcMail() {
		return rfcMail;
	}
	@XmlElement(name = "rfcMail") // JAXB
	public void setRfcMail(String rfcMail) {
		this.rfcMail = rfcMail;
	}
	// Contenido archivos -----------------------------
	public String getFileCertification() {
		return fileCertification;
	}
	public void setFileCertification(String fileCertification) {
		this.fileCertification = fileCertification;
	}
}