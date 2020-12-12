package com.kardex.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.kardex.model.CatalogArea;
import com.kardex.model.CatalogFase;
import com.kardex.model.CatalogFaseBlock;
import com.kardex.model.CatalogFaseBlockTechnology;
import com.kardex.model.CatalogWorkPlace;
import com.kardex.model.CertificationTrack;
import com.kardex.model.CloseCertifications;
import com.kardex.model.EmployeeDemographics;
import com.kardex.model.EmployeeEconomics;
import com.kardex.model.EmployeeFilesSystemPersonal;
import com.kardex.model.EmployeeGral;
import com.kardex.model.EmployeeLabor;
import com.kardex.model.EmployeeLegal;
import com.kardex.model.EmployeeNotification;
import com.kardex.model.EmployeeStudies;
import com.kardex.model.EmployeeVacations;
import com.kardex.model.EmployeeWorkExperience;
import com.kardex.model.HistoryBonus;
import com.kardex.model.HistoryJob;
import com.kardex.model.HistoryLoan;
import com.kardex.model.OpenCertifications;
import com.kardex.model.Parameter;
import com.kardex.model.RequestOfCourses;
import com.kardex.model.Role;
import com.kardex.model.User;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 */
public interface UserService {
	//
	/**
	 * Obtiene parametros
	 * @param id - Si es mayor a 0 busca por id
	 * @return
	 */
	public Parameter getParameterForId(int id);
	//
	/**
	 * @param name - Si tiene valor busca por nombre
	 * @return
	 */
	public Parameter getParameterForName(String name);
	//
	/**
	 * @param action
	 * @return
	 */
	public Object getPrincipal(String action);
	/**
	 * @return
	 */
	public String getEmail();
	/**
	 * @param action
	 * @return
	 */
	public Map<String, String> getUserDetails(String action);
	/**
	 * @param obj
	 * @return
	 */
	public String getAuthority(Object obj);
	//
	/**
	 * @return
	 */
	public String getRootFileSystem();
	/**
	 * @return
	 */
	public String getBdRootFileSystem();
	//
	/**
	 * @return
	 */
	public Resource getRootFileCatalogsState();
	//
	/**
	 * @return
	 */
	public Resource getRootFileCatalogsMunicipality();
	//
	/**
	 * @param id
	 * @return
	 */
	public User findById(int id);
	//
	/**
	 * @param user
	 */
	public void saveUser(User user);
	/**
	 * @param user
	 */
	public void saveUserForLdap(User user);
	/**
	 * @param user
	 */
	public void saveUserForActive(User user);
	/**
	 * @param user
	 */
	public void sendEmailActive(User user);
	/**
	 * @return
	 */
	public List<Role> findRoleAll();
	/**
	 * @return
	 */
	public List<EmployeeGral> findEmployeeAll();
	/**
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeUser(int id, int active) throws Exception;
	//
	/**
	 * @return
	 */
	public List<User> findUserAll();
	/**
	 * @return
	 */
	public Long countUserAll();
	/**
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email);
	// Employee Gral
	/**
	 * Guardar Empleado General
	 * @param user
	 * @param employeeGral
	 * @throws Exception
	 */
	public void saveUserEmployeeGral(User user, EmployeeGral employeeGral) throws Exception;
	/**
	 * Eliminado logico en Empleado General
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeGral(int id, int active) throws Exception;
	// Employee Studies
	/**
	 * Guardar Empleado Estudio
	 * @param user
	 * @param employeeGral
	 * @throws Exception
	 */
	public void saveUserEmployeeStudies(User user, EmployeeStudies employeeStudies) throws Exception;
	/**
	 * Certificate
	 * @param user
	 * @param openCertifications
	 * @param closeCertifications
	 * @throws Exception
	 */
	public void saveUserEmployeeStudiesCertificate(User user, OpenCertifications openCertifications, CloseCertifications closeCertifications) throws Exception;
	/**
	 * Eliminado logico Empleado Estudio
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeStudies(int id, int active) throws Exception;
	/**
	 * Eliminado fisico para certificados, se elimina de bd
	 * Archivos de File System no se eliminan
	 * @param id
	 * @param active
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeStudiesCertificate(int id, int active, String type) throws Exception;
	// Employee demographic
	/**
	 * @param user
	 * @param employeeDemographics
	 * @throws Exception
	 */
	public void saveUserEmployeeDemographic(User user, EmployeeDemographics employeeDemographics) throws Exception;
	/**
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeDemographic(int id, int active) throws Exception;
	// Employee WorkExperience
	/**
	 * @param user
	 * @param employeeWorkExperience
	 * @throws Exception
	 */
	public void saveUserEmployeeWorkExperience(User user, EmployeeWorkExperience employeeWorkExperience) throws Exception;
	/**
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeworkExperience(int id, int active) throws Exception;
	// Employee Labor
	/**
	 * Guardar Empleado Laborales
	 * @param user
	 * @param employeeLabor
	 * @throws Exception
	 */
	public void saveUserEmployeeLabor(User user, EmployeeLabor employeeLabor) throws Exception;
	 /**
	 * @param user
	 * @param historyJob
	 * @throws Exception
	 */
	public void saveUserEmployeeLaborHistory(User user, HistoryJob historyJob) throws Exception;
	 /**
	 * @param id
	 * @param active
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeLaborHistory(int id, int active, String type) throws Exception;
	 /**
	 * @param user
	 * @param vacations
	 * @throws Exception
	 */
	public void saveUserEmployeeVacations(User user, EmployeeVacations employeeVacations) throws Exception;
	 /**
	 * Eliminado logico Empleado Laborales
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeLabor(int id, int active) throws Exception;
	// Employee Economics
	/**
	 * Guardar Empleado Economico
	 * @param user
	 * @param employeeEconomics
	 * @throws Exception
	 */
	public void saveUserEmployeeEconomics(User user, EmployeeEconomics employeeEconomics) throws Exception;
	/**
	 * Eliminado logico Empleado Economico
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeEconomics(int id, int active) throws Exception;
	/**
	 * @param user
	 * @param historyBonus
	 * @param historyLoan
	 * @throws Exception
	 */
	public void saveUserEmployeeEconomicsHistory(User user, HistoryBonus historyBonus, HistoryLoan historyLoan) throws Exception;
	/**
	 * @param id
	 * @param active
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeEconomicsHistory(int id, int active, String type) throws Exception;
	// Employee Legal
	/**
	 * Guardar Empleado Legal
	 * @param user
	 * @param employeeLegal
	 * @throws Exception
	 */
	public void saveUserEmployeeLegal(User user, EmployeeLegal employeeLegal) throws Exception;
	/**
	 * Eliminado logico Empleado Legal
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeLegal(int id, int active) throws Exception;
	
	/**
	 * Buscar por id a EmployeeLegal
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EmployeeLegal findEmployeeLegalById(int id) throws Exception;
	// Employee Files System Personal
	/**
	 * Guardar Empleado Archivos Personales
	 * @param user
	 * @param employeeFilesSystemPersonal
	 * @throws Exception
	 */
	public void saveUserEmployeeFilesSystemPersonal(User user, EmployeeFilesSystemPersonal employeeFilesSystemPersonal) throws Exception;
	/**
	 *  Eliminado logico Empleado Archivos Personales
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeEmployeeFilesSystemPersonal(int id, int active) throws Exception;
	/**
	 * Buscar por id a EmployeeFilesSystemPersonal
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EmployeeFilesSystemPersonal findEmployeeFilesSystemPersonalById(int id) throws Exception;
	// ################ catalog_area #################
	/**
	 * @return
	 */
	public List<CatalogArea> findCatalogAreaAll();
	/**
	 * @param id
	 * @return
	 */
	public CatalogArea findCatalogAreaById(int id);
	/**
	 * @param id
	 * @return
	 */
	public RequestOfCourses findRequestOfCoursesById(int id);
	/**
	 * @param value
	 * @return
	 */
	public CatalogArea findCatalogAreaByValue(String value);
	/**
	 * @param catalogArea
	 * @throws Exception
	 */
	public void saveCatalogArea(CatalogArea catalogArea) throws Exception;
	/**
	 * @param requestOfCourses
	 * @param idUser
	 * @throws Exception
	 */
	public void saveRequestOfCourses(RequestOfCourses requestOfCourses, int idUser) throws Exception;
	/**
	 * @param id
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public boolean activeCatalogArea(int id, int active) throws Exception;
	// ################ catalog_work_place #################
	/**
	 * @param id
	 * @return
	 */
	public CatalogWorkPlace findCatalogWorkPlaceById(int id);
	/**
	 * @param catalogWorkPlace
	 * @throws Exception
	 */
	public void saveCatalogWorkPlace(CatalogWorkPlace catalogWorkPlace) throws Exception;
	/**
	 * @param id
	 * @param active
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean activeCatalogWorkPlace(int id, int active, String type) throws Exception;
	
	/**
	 * @param name
	 * @return list
	 * @throws Exception
	 */
	public List<EmployeeLabor> findEmployeeArea(String id) throws Exception;
	
	/**
	 * @param email
	 * @param data
	 * @return
	 */
	public Map<Long, String> sendMailHelpPortal(String email, String data);
	public Map<Long, String> sendMailHelpPortalLogin(String email, String data);
	
	// ################ certification_track #################
	public List<CertificationTrack> findCertificationTrackAll();
	public CertificationTrack findCertificationTrackById(int id);
	public CertificationTrack findCertificationTrackByValue(String value);
	public void saveCertificationTrack(CertificationTrack certificationTrack) throws Exception;
	public boolean activeCertificationTrack(int id, int active) throws Exception;
	// ################ catalog_fase #################
	public CatalogFase findCatalogFaseById(int id);
	public void saveCatalogFase(CatalogFase catalogFase) throws Exception;
	// ################ catalog_fase_block #################
	public CatalogFaseBlock findCatalogFaseBlockById(int id);
	public void saveCatalogFaseBlock(CatalogFaseBlock catalogFaseBlock) throws Exception;
	// ################ catalog_fase_block_technology #################
	public CatalogFaseBlockTechnology findCatalogFaseBlockTechnologyById(int id);
	public void saveCatalogFaseBlockTechnology(CatalogFaseBlockTechnology catalogFaseBlockTechnology) throws Exception;
	// ################ catalog fase block technology #################
	public boolean activeCatalogFaseBlockTechnology(int id, int active, String type) throws Exception;
	//################### Capacitaciones por colaborador ########################
	/**
	 * @return
	 */
	public EmployeeGral findEmployeeGralById(int id);
	// ################ employee_notification #################
	public List<EmployeeNotification> findEmployeeNotificationAll();
	public List<EmployeeNotification> findEmployeeNotificationUserAll(User user);
	public void deleteEmployeeNotification(Integer id);
	public void notificationTo(String[] args);
	public void saveEmployeeNotification(EmployeeNotification employeeNotification);
}