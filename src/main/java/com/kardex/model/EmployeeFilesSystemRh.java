package com.kardex.model;

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

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Empleados
 * Acceso unicamente administrador
 */
@Entity
@Table(name = "employee_files_system_rh")
public class EmployeeFilesSystemRh {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "administrative_attention")
	private String administrativeAttention;
	@Basic
	@Column(name = "employment_contract")
	private String employmentContract;
	@Basic
	@Column(name = "work_proposal")
	private String workProposal;
	@Basic
	@Column(name = "career_plan_signed")
	private String careerPlanSigned;
	@Basic
	@Column(name = "psychometric_test")
	private String psychometricTest;
	@Basic
	@Column(name = "holidays")
	private String holidays;
	@Basic
	@Column(name = "permits")
	private String permits;
	@Basic
	@Column(name = "socio_economic_research")
	private String socioEconomicResearch;
	@Basic
	@Column(name = "internal_technical_exams")
	private String internalTechnicalExams;
	@Basic
	@Column(name = "performance_evaluation_results")
	private String performanceEvaluationResults;
	@Basic
	@Column(name = "business_curriculum")
	private String businessCurriculum;
	@Basic
	@Column(name = "training")
	private String training;
	@Basic
	@Column(name = "settlement")
	private String settlement;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_gral_id", nullable = true)
	private EmployeeGral employeeGral;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdministrativeAttention() {
		return administrativeAttention;
	}
	public void setAdministrativeAttention(String administrativeAttention) {
		this.administrativeAttention = administrativeAttention;
	}
	public String getEmploymentContract() {
		return employmentContract;
	}
	public void setEmploymentContract(String employmentContract) {
		this.employmentContract = employmentContract;
	}
	public String getWorkProposal() {
		return workProposal;
	}
	public void setWorkProposal(String workProposal) {
		this.workProposal = workProposal;
	}
	public String getCareerPlanSigned() {
		return careerPlanSigned;
	}
	public void setCareerPlanSigned(String careerPlanSigned) {
		this.careerPlanSigned = careerPlanSigned;
	}
	public String getPsychometricTest() {
		return psychometricTest;
	}
	public void setPsychometricTest(String psychometricTest) {
		this.psychometricTest = psychometricTest;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getPermits() {
		return permits;
	}
	public void setPermits(String permits) {
		this.permits = permits;
	}
	public String getSocioEconomicResearch() {
		return socioEconomicResearch;
	}
	public void setSocioEconomicResearch(String socioEconomicResearch) {
		this.socioEconomicResearch = socioEconomicResearch;
	}
	public String getInternalTechnicalExams() {
		return internalTechnicalExams;
	}
	public void setInternalTechnicalExams(String internalTechnicalExams) {
		this.internalTechnicalExams = internalTechnicalExams;
	}
	public String getPerformanceEvaluationResults() {
		return performanceEvaluationResults;
	}
	public void setPerformanceEvaluationResults(String performanceEvaluationResults) {
		this.performanceEvaluationResults = performanceEvaluationResults;
	}
	public String getBusinessCurriculum() {
		return businessCurriculum;
	}
	public void setBusinessCurriculum(String businessCurriculum) {
		this.businessCurriculum = businessCurriculum;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getSettlement() {
		return settlement;
	}
	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	public EmployeeGral getEmployeeGral() {
		return employeeGral;
	}
	public void setEmployeeGral(EmployeeGral employeeGral) {
		this.employeeGral = employeeGral;
	}
}