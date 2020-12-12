package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.EmployeeDemographics;
import com.kardex.model.EmployeeEconomics;
import com.kardex.model.EmployeeFilesSystemPersonal;
import com.kardex.model.EmployeeFilesSystemRh;
import com.kardex.model.EmployeeGral;
import com.kardex.model.EmployeeLabor;
import com.kardex.model.EmployeeLegal;
import com.kardex.model.EmployeeTraining;
import com.kardex.model.EmployeeWorkExperience;
import com.kardex.model.User;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 */
@RestController
@RequestMapping("adm/employee-all/")
public class EmployeeAllController {
	
	@Autowired
	private UserService userService;
	
	@Value("${local.root.file.system}")
	private String rootFileSystem;
	
	/**
	 * Buscar todos los empleados
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeAll", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeAll() throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				if(user.getActive() > 0) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					// Get Role
					row.put("role", user.getRoles().iterator().next().getDescription());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						row.put("birthdate", "" + employeeGral.getBirthdate());
						row.put("sex", "" + employeeGral.getSex());
						row.put("civilStatus", "" + employeeGral.getCivilStatus());
						row.put("nationality", "" + employeeGral.getNationality());
						row.put("emailPersonal", "" + employeeGral.getEmailPersonal());
						row.put("imss", "" + employeeGral.getImss());
						row.put("militaryPrimer", "" + employeeGral.getMilitaryPrimer());
						row.put("phone", "" + employeeGral.getPhone());
						row.put("cellPhone", "" + employeeGral.getCellPhone());
						row.put("emergencyPhone", "" + employeeGral.getEmergencyPhone());
						row.put("emergencyPhoneCall", "" + employeeGral.getEmergencyPhoneCall());
						row.put("rfc", "" + employeeGral.getRfc());
						row.put("curp", "" + employeeGral.getCurp());
						if(null != employeeGral.getEmployeeStudies()) {
							// TODO: daza componer esta tabla
							/*
							EmployeeStudies employeeStudies = employeeGral.getEmployeeStudies();
							row.put("universityCollege", "" + employeeStudies.getUniversityCollege());
							row.put("educationalLevel", "" + employeeStudies.getEducationalLevel());
							row.put("titleStatus", "" + employeeStudies.getTitleStatus());
							row.put("certificationsAviable", "" + employeeStudies.getCertificationsAviable());
							row.put("expiredCertificates", "" + employeeStudies.getExpiredCertificates());
							row.put("others", "" + employeeStudies.getOthers());
							*/
						} else {
							row.put("universityCollege"			 , "");
							row.put("educationalLevel"           , "");
							row.put("titleStatus"                , "");
							row.put("certificationsAviable"      , "");
							row.put("expiredCertificates"        , "");
							row.put("others"                     , "");
						}
						if(null != employeeGral.getEmployeeDemographics()) {
							EmployeeDemographics employeeDemographics = employeeGral.getEmployeeDemographics();
							row.put("streetNumber", "" + employeeDemographics.getStreetNumber());
							row.put("colony", "" + employeeDemographics.getColony());
							row.put("delegationMunicipality", "" + employeeDemographics.getDelegationMunicipality());
							row.put("postalCode", "" + employeeDemographics.getPostalCode());
							row.put("state", "" + employeeDemographics.getState());
						} else {
							row.put("streetNumber"				 , "");
							row.put("colony"                     , "");
							row.put("delegationMunicipality"     , "");
							row.put("postalCode"                 , "");
							row.put("state"                      , "");
						}
						if(null != employeeGral.getEmployeeWorkExperience()) {
							// TODO: componer llenado general
							EmployeeWorkExperience employeeWorkExperience = null;
							row.put("employmentA", ""); // + employeeWorkExperience.getEmploymentA());
							row.put("employmentB", ""); // + employeeWorkExperience.getEmploymentB());
							row.put("employmentC", ""); // + employeeWorkExperience.getEmploymentC());
						} else {
							row.put("employmentA", "");
							row.put("employmentB", "");
							row.put("employmentC", "");
						}
						// TODO: daza se modifico tabla
						/*
						if(null != employeeGral.getEmployeeLabor() && !employeeGral.getEmployeeLabor().isEmpty()) {
							Iterator<EmployeeLabor> iter = employeeGral.getEmployeeLabor().iterator();
							while(iter.hasNext()) {
								
								// EmployeeLabor employeeLabor = iter.next();
								// row.put("dateAdmission", 		(null != row.get("dateAdmission"		 ) ? row.get("dateAdmission"		 ) + " + " + employeeLabor.getDateAdmission()      : employeeLabor.getDateAdmission()      ));
								// row.put("area", 				(null != row.get("area"                  ) ? row.get("area"                  ) + " + " + employeeLabor.getArea()               : employeeLabor.getArea()               ));
								// row.put("marketStall", 			(null != row.get("marketStall"           ) ? row.get("marketStall"           ) + " + " + employeeLabor.getMarketStall()        : employeeLabor.getMarketStall()        ));
								// row.put("jobHistory", 			(null != row.get("jobHistory"            ) ? row.get("jobHistory"            ) + " + " + employeeLabor.getJobHistory()         : employeeLabor.getJobHistory()         ));
								// row.put("immediateBoss", 		(null != row.get("immediateBoss"         ) ? row.get("immediateBoss"         ) + " + " + employeeLabor.getImmediateBoss()      : employeeLabor.getImmediateBoss()      ));
								// row.put("holidaysDaysTaken", 	(null != row.get("holidaysDaysTaken"     ) ? row.get("holidaysDaysTaken"     ) + " + " + employeeLabor.getHolidaysDaysTaken()  : employeeLabor.getHolidaysDaysTaken()  ));
								// row.put("holidaysDaysPending", 	(null != row.get("holidaysDaysPending"   ) ? row.get("holidaysDaysPending"   ) + " + " + employeeLabor.getHolidaysDaysPending(): employeeLabor.getHolidaysDaysPending()));
								// row.put("sgmm", 				(null != row.get("sgmm"                  ) ? row.get("sgmm"                  ) + " + " + employeeLabor.getSgmm()               : employeeLabor.getSgmm()               ));
								// row.put("employeeNumber", 		(null != row.get("employeeNumber"        ) ? row.get("employeeNumber"        ) + " + " + employeeLabor.getEmployeeNumber()     : employeeLabor.getEmployeeNumber()     ));
								// row.put("email", 				(null != row.get("email"                 ) ? row.get("email"                 ) + " + " + employeeLabor.getEmail()              : employeeLabor.getEmail()              ));
							}
						} else {
							row.put("dateAdmission"			 , "");
							row.put("area"                   , "");
							row.put("marketStall"            , "");
							row.put("jobHistory"             , "");
							row.put("immediateBoss"          , "");
							row.put("holidaysDaysTaken"      , "");
							row.put("holidaysDaysPending"    , "");
							row.put("sgmm"                   , "");
							row.put("employeeNumber"         , "");
							row.put("email"                  , "");
						}*/
						// TODO: daza Se cambia tabla economics
						/*
						if(null != employeeGral.getEmployeeEconomics() && !employeeGral.getEmployeeEconomics().isEmpty()) {
							Iterator<EmployeeEconomics> iter = employeeGral.getEmployeeEconomics().iterator();
							while(iter.hasNext()) {
								EmployeeEconomics employeeEconomics = iter.next();
								row.put("salary", 					(null != row.get("salary"				 ) ? row.get("salary"				 ) + " + " + employeeEconomics.getSalary()					: employeeEconomics.getSalary()					));
								row.put("salaryHistory", 			(null != row.get("salaryHistory"         ) ? row.get("salaryHistory"         ) + " + " + employeeEconomics.getSalaryHistory()           : employeeEconomics.getSalaryHistory()          ));
								row.put("bondHistory", 				(null != row.get("bondHistory"           ) ? row.get("bondHistory"           ) + " + " + employeeEconomics.getBondHistory()             : employeeEconomics.getBondHistory()            ));
								row.put("loanHistory", 				(null != row.get("loanHistory"           ) ? row.get("loanHistory"           ) + " + " + employeeEconomics.getLoanHistory()             : employeeEconomics.getLoanHistory()            ));
								row.put("discountInfonavit", 		(null != row.get("discountInfonavit"     ) ? row.get("discountInfonavit"     ) + " + " + employeeEconomics.getDiscountInfonavit()       : employeeEconomics.getDiscountInfonavit()      ));
								row.put("foodAllowanceDiscount", 	(null != row.get("foodAllowanceDiscount" ) ? row.get("foodAllowanceDiscount" ) + " + " + employeeEconomics.getFoodAllowanceDiscount()   : employeeEconomics.getFoodAllowanceDiscount()  ));
							}
						} else {
							row.put("salary"				 , "");
							row.put("salaryHistory"          , "");
							row.put("bondHistory"            , "");
							row.put("loanHistory"            , "");
							row.put("discountInfonavit"      , "");
							row.put("foodAllowanceDiscount"  , "");
						}
						*/
						if(null != employeeGral.getEmployeeLegal() && !employeeGral.getEmployeeLegal().isEmpty()) {
							Iterator<EmployeeLegal> iter = employeeGral.getEmployeeLegal().iterator();
							while(iter.hasNext()) {
								EmployeeLegal employeeLegal = iter.next();
								row.put("administrativeActasAttention",	(null != row.get("administrativeActasAttention") ? row.get("administrativeActasAttention") + " + " + employeeLegal.getAdministrativeActasAttention() : employeeLegal.getAdministrativeActasAttention()));
								row.put("dischargeDate",				(null != row.get("dischargeDate"               ) ? row.get("dischargeDate"               ) + " + " + employeeLegal.getDischargeDate()                : employeeLegal.getDischargeDate()               ));
								row.put("reasonLow",					(null != row.get("reasonLow"                   ) ? row.get("reasonLow"                   ) + " + " + employeeLegal.getReasonLow()                    : employeeLegal.getReasonLow()                   ));
								row.put("passwordGeneratedRrhh",		(null != row.get("passwordGeneratedRrhh"       ) ? row.get("passwordGeneratedRrhh"       ) + " + " + employeeLegal.getPasswordGeneratedRrhh()        : employeeLegal.getPasswordGeneratedRrhh()       ));
								row.put("trainingPromissoryNotes",		(null != row.get("trainingPromissoryNotes"     ) ? row.get("trainingPromissoryNotes"     ) + " + " + employeeLegal.getTrainingPromissoryNotes()      : employeeLegal.getTrainingPromissoryNotes()     ));
							}
						} else {
							row.put("administrativeActasAttention"	 , "");
							row.put("dischargeDate"                  , "");
							row.put("reasonLow"                      , "");
							row.put("passwordGeneratedRrhh"          , "");
							row.put("trainingPromissoryNotes"        , "");
						}
						if(null != employeeGral.getEmployeeTraining() && !employeeGral.getEmployeeTraining().isEmpty()) {
							Iterator<EmployeeTraining> iter = employeeGral.getEmployeeTraining().iterator();
							while(iter.hasNext()) {
								EmployeeTraining employeeTraining = iter.next();
								row.put("softCompetitions",		(null != row.get("softCompetitions"		 ) ? row.get("softCompetitions"		 ) + " + " + employeeTraining.getSoftCompetitions()		: employeeTraining.getSoftCompetitions()		));
								row.put("technicalSkills",		(null != row.get("technicalSkills"       ) ? row.get("technicalSkills"       ) + " + " + employeeTraining.getTechnicalSkills()      : employeeTraining.getTechnicalSkills()         ));
								row.put("otherExposConferences",(null != row.get("otherExposConferences" ) ? row.get("otherExposConferences" ) + " + " + employeeTraining.getOtherExposConferences(): employeeTraining.getOtherExposConferences()   )); 
							}
						} else {
							row.put("softCompetitions"		 , "");
							row.put("technicalSkills"        , "");
							row.put("otherExposConferences"  , "");
						}
						if(null != employeeGral.getEmployeeFilesSystemPersonal() && !employeeGral.getEmployeeFilesSystemPersonal().isEmpty()) {
							Iterator<EmployeeFilesSystemPersonal> iter = employeeGral.getEmployeeFilesSystemPersonal().iterator();
							while(iter.hasNext()) {
								EmployeeFilesSystemPersonal employeeFilesSystemPersonal = iter.next();
								row.put("proofStudies", 			 (null != row.get("proofStudies"			 ) ? row.get("proofStudies"				) + " + " + employeeFilesSystemPersonal.getProofStudies()				 : employeeFilesSystemPersonal.getProofStudies()			 ));
								row.put("birthCertificate", 		 (null != row.get("birthCertificate"         ) ? row.get("birthCertificate"         ) + " + " + employeeFilesSystemPersonal.getBirthCertificate()            : employeeFilesSystemPersonal.getBirthCertificate()         ));
								row.put("titleProfessionalLicense",  (null != row.get("titleProfessionalLicense" ) ? row.get("titleProfessionalLicense" ) + " + " + employeeFilesSystemPersonal.getTitleProfessionalLicense()    : employeeFilesSystemPersonal.getTitleProfessionalLicense() ));
								row.put("fcurp", 					 (null != row.get("fcurp"                    ) ? row.get("fcurp"                    ) + " + " + employeeFilesSystemPersonal.getCurp()                        : employeeFilesSystemPersonal.getCurp()                     ));
								row.put("fimss", 					 (null != row.get("fimss"                    ) ? row.get("fimss"                    ) + " + " + employeeFilesSystemPersonal.getImss()                        : employeeFilesSystemPersonal.getImss()                     ));
								row.put("infonavit", 				 (null != row.get("infonavit"                ) ? row.get("infonavit"                ) + " + " + employeeFilesSystemPersonal.getInfonavit()                   : employeeFilesSystemPersonal.getInfonavit()                ));
								row.put("officialIdentification", 	 (null != row.get("officialIdentification"   ) ? row.get("officialIdentification"   ) + " + " + employeeFilesSystemPersonal.getOfficialIdentification()      : employeeFilesSystemPersonal.getOfficialIdentification()   ));
								row.put("passportVisa", 			 (null != row.get("passportVisa"             ) ? row.get("passportVisa"             ) + " + " + employeeFilesSystemPersonal.getPassportVisa()                : employeeFilesSystemPersonal.getPassportVisa()             ));
								row.put("noCriminalRecord", 		 (null != row.get("noCriminalRecord"         ) ? row.get("noCriminalRecord"         ) + " + " + employeeFilesSystemPersonal.getNoCriminalRecord()            : employeeFilesSystemPersonal.getNoCriminalRecord()         ));
								row.put("proofAddress", 			 (null != row.get("proofAddress"             ) ? row.get("proofAddress"             ) + " + " + employeeFilesSystemPersonal.getProofAddress()                : employeeFilesSystemPersonal.getProofAddress()             ));
								row.put("personalReferences", 		 (null != row.get("personalReferences"       ) ? row.get("personalReferences"       ) + " + " + employeeFilesSystemPersonal.getPersonalReferences()          : employeeFilesSystemPersonal.getPersonalReferences()       ));
								row.put("professionalCurriculum", 	 (null != row.get("professionalCurriculum"   ) ? row.get("professionalCurriculum"   ) + " + " + employeeFilesSystemPersonal.getProfessionalCurriculum()      : employeeFilesSystemPersonal.getProfessionalCurriculum()   ));
								row.put("photo", 					 (null != row.get("photo"                    ) ? row.get("photo"                    ) + " + " + employeeFilesSystemPersonal.getPhoto()                       : employeeFilesSystemPersonal.getPhoto()                    ));
								row.put("certifications", 			 (null != row.get("certifications"           ) ? row.get("certifications"           ) + " + " + employeeFilesSystemPersonal.getCertifications()              : employeeFilesSystemPersonal.getCertifications()           ));
							}
						} else {
							row.put("proofStudies"				 , "");
							row.put("birthCertificate"           , "");
							row.put("titleProfessionalLicense"   , "");
							row.put("fcurp"                      , "");
							row.put("fimss"                      , "");
							row.put("infonavit"                  , "");
							row.put("officialIdentification"     , "");
							row.put("passportVisa"               , "");
							row.put("noCriminalRecord"           , "");
							row.put("proofAddress"               , "");
							row.put("personalReferences"         , "");
							row.put("professionalCurriculum"     , "");
							row.put("photo"                      , "");
							row.put("certifications"             , "");
						}
						if(null != employeeGral.getEmployeeFilesSystemRh() && !employeeGral.getEmployeeFilesSystemRh().isEmpty()) {
							Iterator<EmployeeFilesSystemRh> iter = employeeGral.getEmployeeFilesSystemRh().iterator();
							while(iter.hasNext()) {
								EmployeeFilesSystemRh employeeFilesSystemRh = iter.next();
								row.put("administrativeAttention", 		(null != row.get("administrativeAttention"		) ? row.get("administrativeAttention"		) + " + " + employeeFilesSystemRh.getAdministrativeAttention()		: employeeFilesSystemRh.getAdministrativeAttention()		));
								row.put("employmentContract", 			(null != row.get("employmentContract"           ) ? row.get("employmentContract"            ) + " + " + employeeFilesSystemRh.getEmploymentContract()           : employeeFilesSystemRh.getEmploymentContract()             ));
								row.put("workProposal", 				(null != row.get("workProposal"                 ) ? row.get("workProposal"                  ) + " + " + employeeFilesSystemRh.getWorkProposal()                 : employeeFilesSystemRh.getWorkProposal()                   ));
								row.put("careerPlanSigned", 			(null != row.get("careerPlanSigned"             ) ? row.get("careerPlanSigned"              ) + " + " + employeeFilesSystemRh.getCareerPlanSigned()             : employeeFilesSystemRh.getCareerPlanSigned()               ));
								row.put("psychometricTest", 			(null != row.get("psychometricTest"             ) ? row.get("psychometricTest"              ) + " + " + employeeFilesSystemRh.getPsychometricTest()             : employeeFilesSystemRh.getPsychometricTest()               ));
								row.put("holidays", 					(null != row.get("holidays"                     ) ? row.get("holidays"                      ) + " + " + employeeFilesSystemRh.getHolidays()                     : employeeFilesSystemRh.getHolidays()                       ));
								row.put("permits", 						(null != row.get("permits"                      ) ? row.get("permits"                       ) + " + " + employeeFilesSystemRh.getPermits()                      : employeeFilesSystemRh.getPermits()                        ));
								row.put("socioEconomicResearch", 		(null != row.get("socioEconomicResearch"        ) ? row.get("socioEconomicResearch"         ) + " + " + employeeFilesSystemRh.getSocioEconomicResearch()        : employeeFilesSystemRh.getSocioEconomicResearch()          ));
								row.put("internalTechnicalExams", 		(null != row.get("internalTechnicalExams"       ) ? row.get("internalTechnicalExams"        ) + " + " + employeeFilesSystemRh.getInternalTechnicalExams()       : employeeFilesSystemRh.getInternalTechnicalExams()         ));
								row.put("performanceEvaluationResults",	(null != row.get("performanceEvaluationResults" ) ? row.get("performanceEvaluationResults"  ) + " + " + employeeFilesSystemRh.getPerformanceEvaluationResults() : employeeFilesSystemRh.getPerformanceEvaluationResults()   ));
								row.put("businessCurriculum", 			(null != row.get("businessCurriculum"           ) ? row.get("businessCurriculum"            ) + " + " + employeeFilesSystemRh.getBusinessCurriculum()           : employeeFilesSystemRh.getBusinessCurriculum()             ));
								row.put("training", 					(null != row.get("training"                     ) ? row.get("training"                      ) + " + " + employeeFilesSystemRh.getTraining()                     : employeeFilesSystemRh.getTraining()                       ));
								row.put("settlement", 					(null != row.get("settlement"                   ) ? row.get("settlement"                    ) + " + " + employeeFilesSystemRh.getSettlement()                   : employeeFilesSystemRh.getSettlement()                     ));
							}
						} else {
							row.put("administrativeAttention"			 , "");
							row.put("employmentContract"                 , "");
							row.put("workProposal"                       , "");
							row.put("careerPlanSigned"                   , "");
							row.put("psychometricTest"                   , "");
							row.put("holidays"                           , "");
							row.put("permits"                            , "");
							row.put("socioEconomicResearch"              , "");
							row.put("internalTechnicalExams"             , "");
							row.put("performanceEvaluationResults"       , "");
							row.put("businessCurriculum"                 , "");
							row.put("training"                           , "");
							row.put("settlement"                         , "");
						}
					} else {
						row.put("birthdate"				 , "");
						row.put("sex"                    , "");
						row.put("civilStatus"            , "");
						row.put("nationality"            , "");
						row.put("emailPersonal"          , "");
						row.put("imss"                   , "");
						row.put("militaryPrimer"         , "");
						row.put("phone"                  , "");
						row.put("cellPhone"              , "");
						row.put("emergencyPhone"         , "");
						row.put("emergencyPhoneCall"     , "");
						row.put("rfc"                    , "");
						row.put("curp"                   , "");

						row.put("universityCollege"			 , "");
						row.put("educationalLevel"           , "");
						row.put("titleStatus"                , "");
						row.put("certificationsAviable"      , "");
						row.put("expiredCertificates"        , "");
						row.put("others"                     , "");

						row.put("streetNumber"				 , "");
						row.put("colony"                     , "");
						row.put("delegationMunicipality"     , "");
						row.put("postalCode"                 , "");
						row.put("state"                      , "");

						row.put("employmentA", "");
						row.put("employmentB", "");
						row.put("employmentC", "");

						row.put("dateAdmission"			 , "");
						row.put("area"                   , "");
						row.put("marketStall"            , "");
						row.put("jobHistory"             , "");
						row.put("immediateBoss"          , "");
						row.put("holidaysDaysTaken"      , "");
						row.put("holidaysDaysPending"    , "");
						row.put("sgmm"                   , "");
						row.put("employeeNumber"         , "");
						row.put("email"                  , "");

						row.put("salary"				 , "");
						row.put("salaryHistory"          , "");
						row.put("bondHistory"            , "");
						row.put("loanHistory"            , "");
						row.put("discountInfonavit"      , "");
						row.put("foodAllowanceDiscount"  , "");

						row.put("administrativeActasAttention"	 , "");
						row.put("dischargeDate"                  , "");
						row.put("reasonLow"                      , "");
						row.put("passwordGeneratedRrhh"          , "");
						row.put("trainingPromissoryNotes"        , "");

						row.put("softCompetitions"		 , "");
						row.put("technicalSkills"        , "");
						row.put("otherExposConferences"  , "");

						row.put("proofStudies"				 , "");
						row.put("birthCertificate"           , "");
						row.put("titleProfessionalLicense"   , "");
						row.put("fcurp"                      , "");
						row.put("fimss"                      , "");
						row.put("infonavit"                  , "");
						row.put("officialIdentification"     , "");
						row.put("passportVisa"               , "");
						row.put("noCriminalRecord"           , "");
						row.put("proofAddress"               , "");
						row.put("personalReferences"         , "");
						row.put("professionalCurriculum"     , "");
						row.put("photo"                      , "");
						row.put("certifications"             , "");

						row.put("administrativeAttention"			 , "");
						row.put("employmentContract"                 , "");
						row.put("workProposal"                       , "");
						row.put("careerPlanSigned"                   , "");
						row.put("psychometricTest"                   , "");
						row.put("holidays"                           , "");
						row.put("permits"                            , "");
						row.put("socioEconomicResearch"              , "");
						row.put("internalTechnicalExams"             , "");
						row.put("performanceEvaluationResults"       , "");
						row.put("businessCurriculum"                 , "");
						row.put("training"                           , "");
						row.put("settlement"                         , "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
}