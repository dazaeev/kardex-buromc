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
 * Lo debe subir el usuario, pero no debe poder borrarlo una vez ya integrado. 
 * El usuario es responsable de mantenerlo actualizado.
 */
@Entity
@Table(name = "employee_files_system_personal")
public class EmployeeFilesSystemPersonal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "proof_studies")
	private String proofStudies;
	@Basic
	@Column(name = "birth_certificate")
	private String birthCertificate;
	@Basic
	@Column(name = "title_professional_license")
	private String titleProfessionalLicense;
	@Basic
	@Column(name = "curp")
	private String curp;
	@Basic
	@Column(name = "rfc")
	private String rfc;
	@Basic
	@Column(name = "imss")
	private String imss;
	@Basic
	@Column(name = "infonavit")
	private String infonavit;
	@Basic
	@Column(name = "official_identification")
	private String officialIdentification;
	@Basic
	@Column(name = "passport_visa")
	private String passportVisa;
	@Basic
	@Column(name = "no_criminal_record")
	private String noCriminalRecord;
	@Basic
	@Column(name = "proof_address")
	private String proofAddress;
	@Basic
	@Column(name = "personal_references")
	private String personalReferences;
	@Basic
	@Column(name = "professional_curriculum")
	private String professionalCurriculum;
	@Basic
	@Column(name = "photo")
	private String photo;
	@Basic
	@Column(name = "certifications")
	private String certifications;
	@Basic
	@Column(name = "recommendationLetter")
	private String recommendationLetter;
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
	
	// Contenido archivos -----------------------------
	@Transient private String fileProofStudies;
    @Transient private String fileBirthCertificate;
    @Transient private String fileTitleProfessionalLicense;
    @Transient private String fileCurp;
    @Transient private String fileRfc;
    @Transient private String fileImss;
    @Transient private String fileInfonavit;
    @Transient private String fileOfficialIdentification;
    @Transient private String filePassportVisa;
    @Transient private String fileNoCriminalRecord;
    @Transient private String fileProofAddress;
    @Transient private String filePersonalReferences;
    @Transient private String fileProfessionalCurriculum;
    @Transient private String filePhoto;
    @Transient private String fileCertifications;
    @Transient private String fileRecommendationLetter;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProofStudies() {
		return proofStudies;
	}
	public void setProofStudies(String proofStudies) {
		this.proofStudies = proofStudies;
	}
	public String getBirthCertificate() {
		return birthCertificate;
	}
	public void setBirthCertificate(String birthCertificate) {
		this.birthCertificate = birthCertificate;
	}
	public String getTitleProfessionalLicense() {
		return titleProfessionalLicense;
	}
	public void setTitleProfessionalLicense(String titleProfessionalLicense) {
		this.titleProfessionalLicense = titleProfessionalLicense;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getImss() {
		return imss;
	}
	public void setImss(String imss) {
		this.imss = imss;
	}
	public String getInfonavit() {
		return infonavit;
	}
	public void setInfonavit(String infonavit) {
		this.infonavit = infonavit;
	}
	public String getOfficialIdentification() {
		return officialIdentification;
	}
	public void setOfficialIdentification(String officialIdentification) {
		this.officialIdentification = officialIdentification;
	}
	public String getPassportVisa() {
		return passportVisa;
	}
	public void setPassportVisa(String passportVisa) {
		this.passportVisa = passportVisa;
	}
	public String getNoCriminalRecord() {
		return noCriminalRecord;
	}
	public void setNoCriminalRecord(String noCriminalRecord) {
		this.noCriminalRecord = noCriminalRecord;
	}
	public String getProofAddress() {
		return proofAddress;
	}
	public void setProofAddress(String proofAddress) {
		this.proofAddress = proofAddress;
	}
	public String getPersonalReferences() {
		return personalReferences;
	}
	public void setPersonalReferences(String personalReferences) {
		this.personalReferences = personalReferences;
	}
	public String getProfessionalCurriculum() {
		return professionalCurriculum;
	}
	public void setProfessionalCurriculum(String professionalCurriculum) {
		this.professionalCurriculum = professionalCurriculum;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}
	public String getRecommendationLetter() {
		return recommendationLetter;
	}
	public void setRecommendationLetter(String recommendationLetter) {
		this.recommendationLetter = recommendationLetter;
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
	public String getFileProofStudies() {
		return fileProofStudies;
	}
	public void setFileProofStudies(String fileProofStudies) {
		this.fileProofStudies = fileProofStudies;
	}
	public String getFileBirthCertificate() {
		return fileBirthCertificate;
	}
	public void setFileBirthCertificate(String fileBirthCertificate) {
		this.fileBirthCertificate = fileBirthCertificate;
	}
	public String getFileTitleProfessionalLicense() {
		return fileTitleProfessionalLicense;
	}
	public void setFileTitleProfessionalLicense(String fileTitleProfessionalLicense) {
		this.fileTitleProfessionalLicense = fileTitleProfessionalLicense;
	}
	public String getFileCurp() {
		return fileCurp;
	}
	public void setFileCurp(String fileCurp) {
		this.fileCurp = fileCurp;
	}
	public String getFileRfc() {
		return fileRfc;
	}
	public void setFileRfc(String fileRfc) {
		this.fileRfc = fileRfc;
	}
	public String getFileImss() {
		return fileImss;
	}
	public void setFileImss(String fileImss) {
		this.fileImss = fileImss;
	}
	public String getFileInfonavit() {
		return fileInfonavit;
	}
	public void setFileInfonavit(String fileInfonavit) {
		this.fileInfonavit = fileInfonavit;
	}
	public String getFileOfficialIdentification() {
		return fileOfficialIdentification;
	}
	public void setFileOfficialIdentification(String fileOfficialIdentification) {
		this.fileOfficialIdentification = fileOfficialIdentification;
	}
	public String getFilePassportVisa() {
		return filePassportVisa;
	}
	public void setFilePassportVisa(String filePassportVisa) {
		this.filePassportVisa = filePassportVisa;
	}
	public String getFileNoCriminalRecord() {
		return fileNoCriminalRecord;
	}
	public void setFileNoCriminalRecord(String fileNoCriminalRecord) {
		this.fileNoCriminalRecord = fileNoCriminalRecord;
	}
	public String getFileProofAddress() {
		return fileProofAddress;
	}
	public void setFileProofAddress(String fileProofAddress) {
		this.fileProofAddress = fileProofAddress;
	}
	public String getFilePersonalReferences() {
		return filePersonalReferences;
	}
	public void setFilePersonalReferences(String filePersonalReferences) {
		this.filePersonalReferences = filePersonalReferences;
	}
	public String getFileProfessionalCurriculum() {
		return fileProfessionalCurriculum;
	}
	public void setFileProfessionalCurriculum(String fileProfessionalCurriculum) {
		this.fileProfessionalCurriculum = fileProfessionalCurriculum;
	}
	public String getFilePhoto() {
		return filePhoto;
	}
	public void setFilePhoto(String filePhoto) {
		this.filePhoto = filePhoto;
	}
	public String getFileCertifications() {
		return fileCertifications;
	}
	public void setFileCertifications(String fileCertifications) {
		this.fileCertifications = fileCertifications;
	}
	public String getFileRecommendationLetter() {
		return fileRecommendationLetter;
	}
	public void setFileRecommendationLetter(String fileRecommendationLetter) {
		this.fileRecommendationLetter = fileRecommendationLetter;
	}
}