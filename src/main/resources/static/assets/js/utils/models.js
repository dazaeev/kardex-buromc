/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Modelo de Datos
 */

function User() {
    this.id = 0;
    this.uuid = 0;
    this.account = '';
    this.email = '';
    this.password = '';
    this.name = '';
    this.lastName = '';
    this.active = 1;
    this.roles = [];
    this.employeeNotification = [];
    this.employee = null;
}

function Employee() {
	this.id = 0;
	this.cveEmployee = '';
	this.birthdate = '';
	this.sex = '';
	this.civilStatus = '';
	this.nationality = '';
	this.imss = '';
	this.state = '';
	this.delegationMunicipality = '';
	this.colony = '';
	this.postalCode = '';
	this.streetNumber = '';
	this.militaryPrimer = '';
	this.phone = '';
	this.cellPhone = '';
	this.emergencyPhone = '';
	this.rfc = '';
	this.curp = '';
	this.institute = '';
	this.educationalLevel = '';
	this.statusTitle = '';
	this.nameProofStudies = '';
	this.nameBirthCertificate = '';
	this.nameTitleProfecionalCedula = '';
	this.nameCurp = '';
	this.nameImss = '';
	this.nameInfonavit = '';
	this.nameIdentification = '';
	this.namePassportVisa = '';
	this.nameNoncriminalBackground = '';
	this.nameProofAddress = '';
	this.namePersonalReferences = '';
	this.nameProfessionalCv = '';
	this.namePsychometricTests = '';
	this.namePhoto = '';
	this.jobPlace = null;
	this.boss = '';
	this.salary = '';
	this.user = null;
	// Content File
	this.fileProofStudies = '';
	this.fileBirthCertificate = '';
	this.fileTitleProfecionalCedula = '';
	this.fileCurp = '';
	this.fileImss = '';
	this.fileInfonavit = '';
	this.fileIdentification = '';
	this.filePassportVisa = '';
	this.fileNoncriminalBackground = '';
	this.fileProofAddress = '';
	this.filePersonalReferences = '';
	this.fileProfessionalCv = '';
	this.filePsychometricTests = '';
	this.filePhoto = '';
}

function JobPlace() {
	this.id = 0;
	this.name = '';
	this.description = '';
	this.employees = [];
	this.area = null;
}

function Area() {
	this.id = 0;
	this.name = '';
	this.description = '';
	this.jobPlaces = [];
}

function Role() {
    this.id = 0;
    this.role = '';
    this.description = '';
}

function Menu() {
	this.id = 0;
	this.roleId = 0;
	this.menu = '';
	this.subMenu = '';
	this.html = '';
	this.roles = [];
}

function employeeNotification(){
	this.id = 0;
	this.value = '';
	this.active = '';
	this.user = null;
}

function employeeGral(){
	this.id = 0;
	this.birthdate = '';
	this.sex = '';
	this.civilStatus = '';
	this.nationality = '';
	this.emailPersonal = '';
	this.imss = '';
	this.militaryPrimer = '';
	this.phone = '';
	this.cellPhone = '';
	this.emergencyPhone = '';
	this.emergencyPhoneCall = '';
	this.rfc = '';
	this.curp = '';
	this.active = '';
	this.user = null;
	this.employeeStudies = null;
	this.employeeDemographics = null;
	this.employeeWorkExperience = [];
	this.employeeLabor = null;
	this.employeeEconomics = null;
	this.employeeLegal = [];
	this.employeeTraining = [];
	this.employeeFilesSystemPersonal = [];
	this.employeeFilesSystemRh = [];
}

function employeeStudies(){
	this.id = 0;
	this.schoolLatest = '';
	this.educationalLevelLatest = '';
	this.statusLatest = '';
	this.schoolPrevious = '';
	this.educationalLevelPrevious = '';
	this.statusPrevious = '';
	this.active = 0;
	this.employeeGral = null;
	this.openCertifications = [];
	this.closeCertifications = [];
}

function employeeDemographics(){
	this.id							= 0;
	this.streetNumber            	= '';  
	this.colony                  	= '';  
	this.delegationMunicipality  	= '';  
	this.postalCode              	= '';  
	this.state                   	= '';
	this.active             		= '';
	this.employeeGral 				= null;
}

function employeeWorkExperience(){
	this.id							= 0;
	this.company	            	= '';  
	this.position               	= '';
	this.salary						= '';
	this.dateAdmission				= '';
	this.departureDate				= '';
	this.reasonOfExit				= '';
	this.type						= '';
	this.active             		= '';
	this.employeeGral 				= null;
}

function employeeLabor(){
	this.id					= 0;
	this.dateAdmission      = '';
	this.area               = '';
	this.policySgmm			= '';
	this.employeeNumber		= '';
	this.businessMail		= '';
	this.position			= '';
	this.active				= '';
	this.employeeGral		= null;
	this.historyJob			= [];
	this.employeeVacations	= [];
}

function employeeEconomics(){
	this.id						= 0;
	this.salary					= '';
	this.dateInfonavit			= '';
	this.amountInfonavit		= '';
	this.dateAlimony			= '';
	this.amountAlimony			= '';
	this.active             	= '';
	this.employeeGral 			= null;
	this.historyBonus			= [];
	this.historyLoan			= [];
}

function employeeLegal() {
	this.id									= 0;
	this.administrativeActasAttention   	= '';
	this.dischargeDate                  	= '';
	this.reasonLow                      	= '';
	this.passwordGeneratedRrhh          	= '';
	this.trainingPromissoryNotes        	= '';
	this.active								= '';
	this.employeeGral						= null;
	// Content File
	this.fileAdministrativeActasAttention	= '';
}

function employeeFilesSystemPersonal() {
	this.id									= 0;
	this.proofStudies						= '';
	this.birthCertificate                   = '';
	this.titleProfessionalLicense           = '';
	this.curp                               = '';
	this.rfc                                = '';
	this.imss                               = '';
	this.infonavit                          = '';
	this.officialIdentification             = '';
	this.passportVisa                       = '';
	this.noCriminalRecord                   = '';
	this.proofAddress                       = '';
	this.personalReferences                 = '';
	this.professionalCurriculum             = '';
	this.photo                              = '';
	this.certifications                     = '';
	this.recommendationLetter				= '';
	this.active								= '';
	this.employeeGral						= null;
	// Content File
	this.fileProofStudies                   = '';
	this.fileBirthCertificate               = '';
	this.fileTitleProfessionalLicense       = '';
	this.fileCurp                           = '';
	this.fileRfc                            = '';
	this.fileImss                           = '';
	this.fileInfonavit                      = '';
	this.fileOfficialIdentification         = '';
	this.filePassportVisa                   = '';
	this.fileNoCriminalRecord               = '';
	this.fileProofAddress                   = '';
	this.filePersonalReferences             = '';
	this.fileProfessionalCurriculum         = '';
	this.filePhoto                          = '';
	this.fileCertifications                 = '';
	this.fileRecommendationLetter           = '';
}

function openCertifications() {
	this.id					= 0;
	this.description		= '';
	this.certification      = '';
	this.nameCertification	= '';
	this.dateExpiration		= '';
	this.sendingExpiredMail	= 1;
	this.active             = '';
	this.employeeStudies 	= null;
	// Content File
	this.fileCertification	= '';
}

function closeCertifications() {
	this.id					= 0;
	this.description		= '';
	this.certification      = '';
	this.nameCertification	= '';
	this.dateExpiration		= '';
	this.active             = '';
	this.employeeStudies 	= null;
	// Content File
	this.fileCertification	= '';
}

function historyJob() {
	this.id					= 0;
	this.jobTitle			= '';
	this.dateIn      		= '';
	this.dateOu				= '';
	this.jobArea			= '';
	this.immediateBoss		= '';
	this.salaryIn			= '';
	this.salaryOu			= '';
	this.active             = '';
	this.employeeLabor		= null;
	
	this.active             = '';
	this.employeeStudies 	= null;
}

function employeeVacations() {
	this.id					= 0;
	this.daysTaken			= '';
	this.daysForYear      	= '';
	this.daysPending		= '';
	this.reasonOfExit		= '';
	this.holiday			= '';
	this.active             = '';
	this.employeeLabor		= null;
}
function historyBonus() {
	this.id					= 0;
	this.dateBonus			= '';
	this.amountBonus		= '';
	this.conceptBonus		= '';
	this.status				= '';
	this.active             = '';
	this.employeeEconomics 	= null;
}

function historyLoan() {
	this.id					= 0;
	this.dateLoan			= '';
	this.amountLoan			= '';
	this.conceptLoan		= '';
	this.status				= '';
	this.active             = '';
	this.employeeEconomics 	= null;
}

// Solicitud de CURSOS
function requestOfCourses(){
	this.id								= 0;
	this.nameRequestProgram 			='';
	this.duration						='';
	this.modality           			='';
	this.nameProvider       			='';
	this.rfcProvider        			='';
	this.taxResidence       			='';
	this.contactInfo        			='';
	this.startDate          			='';
	this.endDate            			='';
	this.placeCurse         			='';
	this.costWoTax          			='';
	this.costWTax           			='';
	this.objetivesCourse				='';
	this.objetivesCourseRelatedJobPlace	='';
	this.technicalJustification			='';
	this.company1						='';
	this.justification1     			='';
	this.company2           			='';
	this.justification2     			='';
	this.company3           			='';
	this.justification3     			='';
	this.approved						= 0;
	this.nameCertification				= '';
	this.active             			='';
	this.employeeGral					= null;
	// Content File
	this.fileCertification				= '';
}

// CATALOGS
function catalogArea(){
	this.id					= 0;
	this.name      			= '';
	this.value      		= '';
	this.description      	= '';
	this.active      		= '';
	this.catalogWorkPlace	= [];
	this.certificationTrack = null;
}

function catalogWorkPlace() {
	this.id					= 0;
	this.name      			= '';
	this.value      		= '';
	this.description      	= '';
	this.active      		= '';
	this.catalogArea	 	= null;
}

// CertificationTrack
function certificationTrack(){
	this.id					= 0;
	this.name      			= '';
	this.area      			= '';
	this.active      		= '';
	this.catalogFase		= [];
	this.catalogArea		= null;
}

function catalogFase() {
	this.id					= 0;
	this.name      			= '';
	this.description      	= '';
	this.active      		= '';
	this.certificationTrack	= null;
	this.catalogFaseBlock	= [];
}

function catalogFaseBlock() {
	this.id							= 0;
	this.name      					= '';
	this.description      			= '';
	this.active      				= '';
	this.catalogFase				= null;
	this.catalogFaseBlockTechnology	= [];
}

function catalogFaseBlockTechnology() {
	this.id							= 0;
	this.technology					= '';
	this.product	      			= '';
	this.active      				= '';
	this.catalogFaseBlock			= null;
}