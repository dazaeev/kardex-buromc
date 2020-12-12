package com.kardex.rest.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.CatalogArea;
import com.kardex.model.CloseCertifications;
import com.kardex.model.EmployeeDemographics;
import com.kardex.model.EmployeeEconomics;
import com.kardex.model.EmployeeFilesSystemPersonal;
import com.kardex.model.EmployeeGral;
import com.kardex.model.EmployeeLabor;
import com.kardex.model.EmployeeLegal;
import com.kardex.model.EmployeeStudies;
import com.kardex.model.EmployeeVacations;
import com.kardex.model.EmployeeWorkExperience;
import com.kardex.model.HistoryBonus;
import com.kardex.model.HistoryJob;
import com.kardex.model.HistoryLoan;
import com.kardex.model.OpenCertifications;
import com.kardex.model.RequestOfCourses;
import com.kardex.model.User;
import com.kardex.service.DashboardService;
import com.kardex.service.UserService;
import com.kardex.utils.Utils;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 */
@RestController
@RequestMapping("adm/employee-gral/")
public class EmployeeGralController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeGralController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping(value = "findUserName", method = RequestMethod.POST)
    public Map<String, String> findUserName() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findUserName");
		Map<String, String> responseData = new TreeMap<>();
		User user = userService.findUserByEmail(mapUserDetails.get("USERNAME"));
		responseData.put("USERNAME", user.getName() + " " + user.getLastName());
		return responseData;
	}
	
	@RequestMapping(value = "findEmployeeGralExist", method = RequestMethod.POST)
    public Map<String, String> findEmployeeGralExist() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeGralExist");
		Map<String, String> responseData = new TreeMap<>();
		// Si es ADMIN no valida llenado de "Datos Basicos (Información Básica)"
		if(mapUserDetails.get("ROLE").equals("ADMIN")) {
			responseData.put("process", "1");
		} else {
			User user = userService.findUserByEmail(mapUserDetails.get("USERNAME"));
			if(null != user.getEmployee()) {
				responseData.put("process", "1");
			} else {
				responseData.put("process", "0");
			}
		}
		return responseData;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ********************************** Controllers de Employee Gral ***************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Deberan ser llenados por el usuario y despues solo modificable por administrador 
	 * (LLENADO ÚNICA OCASIÓN AL INGRESO A LA EMPRESA) 
	 * Estos datos podran ser visibles para el empleado, pero solo modificables por el administrador"
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeGral", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeGral() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeGral");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				if(user.getActive() > 0) {
					// Validar si lo pueden ver ADMIN o USER
					if(Utils.validateUserAdmin(user, mapUserDetails)) {
						row.put("id", user.getId());
						row.put("uuid", user.getUuid());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						// Get Role
						row.put("role", user.getRoles().iterator().next().getDescription());
						if(null != user.getEmployee() && user.getEmployee().getActive() > 0) {
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
						} else {
							// Para validar botones
							row.put("enabled"				 , "true");
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
						}
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_gral
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeGral", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeGral(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			userService.saveUserEmployeeGral(userExists, user.getEmployee());
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "employee_gral"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeGral/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeGral(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeGral(id, active)) {
				throw new Exception("Empleado no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ********************************** Controllers de Employee Studies ************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Deberan ser llenados por el usuario y despues solo modificable por administrador 
	 * (LLENADO ÚNICA OCASIÓN AL INGRESO A LA EMPRESA, TODA CAPACITACIÓN POSTERIOR SE DEBE REGISTRAR EN EL APARTADO DE CAPACITACIÓN)
	 * Estos datos podran ser visibles para el empleado, pero solo modificables por el administrador"
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeStudies", method = RequestMethod.POST)
	public List<Map<String, Object>> findEmployeeStudies() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeStudies");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeStudies() && user.getEmployee().getEmployeeStudies().getActive() > 0) {
							EmployeeStudies employeeStudies = employeeGral.getEmployeeStudies();
							row.put("idStudies", "" + employeeStudies.getId());
							row.put("schoolLatest", "" + employeeStudies.getSchoolLatest());
							row.put("educationalLevelLatest", "" + employeeStudies.getEducationalLevelLatest());
							row.put("statusLatest", "" + employeeStudies.getStatusLatest());
							row.put("schoolPrevious", "" + employeeStudies.getSchoolPrevious());
							row.put("educationalLevelPrevious", "" + employeeStudies.getEducationalLevelPrevious());
							row.put("statusPrevious", "" + employeeStudies.getStatusPrevious());
						} else {
							// Para validar botones
							row.put("enabled"					, "true");
							row.put("idStudies"					, "0");
							row.put("schoolLatest"				, "");
							row.put("educationalLevelLatest"	, "");
							row.put("statusLatest"				, "");
							row.put("schoolPrevious"			, "");
							row.put("educationalLevelPrevious"	, "");
							row.put("statusPrevious"			, "");
						}
					} else {
						// Para validar botones
						row.put("enabled"					, "true");
						row.put("idStudies"					, "0");
						row.put("schoolLatest"				, "");
						row.put("educationalLevelLatest"	, "");
						row.put("statusLatest"				, "");
						row.put("schoolPrevious"			, "");
						row.put("educationalLevelPrevious"	, "");
						row.put("statusPrevious"			, "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_studies
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeStudies", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeStudies(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeStudies()) {
					user.getEmployee().getEmployeeStudies().setId(userExists.getEmployee().getEmployeeStudies().getId());
				} else {
					user.getEmployee().getEmployeeStudies().setEmployeeGral(userExists.getEmployee());
				}
				userService.saveUserEmployeeStudies(userExists, user.getEmployee().getEmployeeStudies());
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "employee_studies" y eliminar "open_certifications"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeStudies/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeestudies(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeStudies(id, active)) {
				throw new Exception("Información de estudios no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeStudiesOpenCertifications/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeStudiesOpenCertifications(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeStudies() 
							&& !employeeGral.getEmployeeStudies().getOpenCertifications().isEmpty()) {
						Iterator<OpenCertifications> iter = employeeGral.getEmployeeStudies().getOpenCertifications().iterator();
						while(iter.hasNext()) {
							OpenCertifications openCertifications = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + openCertifications.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(openCertifications.getActive() > 0) {
								row.put("idOpenCertifications", "" + openCertifications.getId());
								row.put("certification", "" + openCertifications.getCertification());
								row.put("description", "" + openCertifications.getDescription());
								row.put("nameCertification", "" + openCertifications.getNameCertification());
								row.put("dateExpiration", "" + openCertifications.getDateExpiration());
								// Validar fecha de expiracion
								Date dateCertificate 	= Utils.formatDate("" + openCertifications.getDateExpiration(), "yyyy-MM-dd");
								Date dateCertificateSum = Utils.subtractDays(dateCertificate, "suma", Integer.parseInt(userService.getParameterForName("date-expiration").getValue()));
								Date dateCurrent		= new Date();
								Date dateCurrentSum 	= Utils.subtractDays("suma", Integer.parseInt(userService.getParameterForName("date-expiration").getValue()));
								String log = user.getName() + " " + dateCertificate;
								if(dateCertificate.compareTo(dateCurrent) > 0) {
									LOGGER.info("Pasa vencimiento: {}", log);
									if(dateCertificateSum.compareTo(dateCurrent) >= 0) {
										LOGGER.info("A un por vencer: {}", dateCertificate);
										if(dateCertificate.compareTo(dateCurrentSum) <= 0) {
											LOGGER.info("Esta pronto a vencer: {}", dateCertificate);
											row.put("dateExpirationInValid", "true");
										}
									}
								} else {
									LOGGER.info("Ya vencio: {}", log);
									row.put("dateExpirationInValid", "true");
								}
							} else {
								// Para validar botones
								row.put("enabled"				 , "true");
								row.put("idOpenCertifications"	 , "" + openCertifications.getId());
								row.put("certification"			 , "");
								row.put("description"            , "");
								row.put("nameCertification"		 , "");
								row.put("dateExpiration"		 , "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 , "true");
						row.put("idOpenCertifications"	 , "0");
						row.put("certification"			 , "");
						row.put("description"            , "");
						row.put("nameCertification"		 , "");
						row.put("dateExpiration"		 , "");
											
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeStudiesCertificate/{type}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeStudiesCertificate(@RequestBody User user, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeStudies()) {
					user.getEmployee().getEmployeeStudies().setId(userExists.getEmployee().getEmployeeStudies().getId());
				} else {
					user.getEmployee().getEmployeeStudies().setEmployeeGral(userExists.getEmployee());
				}
				if(type.equals("VIGENTE")) {
					userService.saveUserEmployeeStudiesCertificate(userExists, user.getEmployee().getEmployeeStudies().getOpenCertifications().iterator().next(), null);
				}
				if(type.equals("VENCIDA")) {
					userService.saveUserEmployeeStudiesCertificate(userExists, null, user.getEmployee().getEmployeeStudies().getCloseCertifications().iterator().next());
				}
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "open_certifications"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeStudiesCertifications/{id}/{active}/{type}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeestudiesCertifications(@PathVariable int id, @PathVariable int active, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeStudiesCertificate(id, active, type)) {
				throw new Exception("Información de certificaciones no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Ver Documento
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewEmployeeStudiesCertificate/{type}", method = RequestMethod.POST)
	public Map<String, String> viewEmployeeStudiesCertificate(@RequestBody Map<String, String> model, @PathVariable String type) {
		Map<String, String> map = new HashMap<>();
		try {
			if(model.isEmpty()) {
				throw new Exception("Nombre del arcnivo vacio.");
			}
			User user = userService.findById(Integer.parseInt(model.get("idUser")));
			String email = user.getEmail();
			// Traer base 64 archivo
			if(type.equals("VIGENTE")) {
				map = Utils.viewDocEmployee(email, model.get("nameFile"), userService.getRootFileSystem(), "open_certifications" + File.separator + model.get("idOpenCertifications"));
			}
			if(type.equals("VENCIDA")) {
				map = Utils.viewDocEmployee(email, model.get("nameFile"), userService.getRootFileSystem(), "close_certifications" + File.separator + model.get("idOpenCertifications"));
			}
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeStudiesCloseCertifications/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeStudiesCloseCertifications(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeStudies() 
							&& !employeeGral.getEmployeeStudies().getCloseCertifications().isEmpty()) {
						Iterator<CloseCertifications> iter = employeeGral.getEmployeeStudies().getCloseCertifications().iterator();
						while(iter.hasNext()) {
							CloseCertifications closeCertifications = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + closeCertifications.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(closeCertifications.getActive() > 0) {
								row.put("idCloseCertifications", "" + closeCertifications.getId());
								row.put("certification", "" + closeCertifications.getCertification());
								row.put("description", "" + closeCertifications.getDescription());
								row.put("nameCertification", "" + closeCertifications.getNameCertification());
								row.put("dateExpiration", "" + closeCertifications.getDateExpiration());
							} else {
								// Para validar botones
								row.put("enabled"				 , "true");
								row.put("idCloseCertifications", "" + closeCertifications.getId());
								row.put("certification"			 , "");
								row.put("description"            , "");
								row.put("nameCertification"		 , "");
								row.put("dateExpiration"		 , "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 , "true");
						row.put("idCloseCertifications"	 , "0");
						row.put("certification"			 , "");
						row.put("description"            , "");
						row.put("nameCertification"		 , "");
						row.put("dateExpiration"		 , "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ****************************** Controllers de Employee Demographics ***********************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Estos datos NO podran ser visibles para el empleado UNA VEZ SEAN LLENADOS POR CUESTIÓN DE SEGURIDAD
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeDemographics", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeDemographics() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeDemographics");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeDemographics() && user.getEmployee().getActive() > 0 && user.getEmployee().getEmployeeDemographics().getActive() > 0) {
							EmployeeDemographics employeeDemographics = employeeGral.getEmployeeDemographics();
							row.put("streetNumber", "" + employeeDemographics.getStreetNumber());
							row.put("colony", "" + employeeDemographics.getColony());
							row.put("delegationMunicipality", "" + employeeDemographics.getDelegationMunicipality());
							row.put("postalCode", "" + employeeDemographics.getPostalCode());
							row.put("state", "" + employeeDemographics.getState());
						} else {
							// Para validar botones
							row.put("enabled"				 , "true");
							row.put("streetNumber"				 , "");
							row.put("colony"                     , "");
							row.put("delegationMunicipality"     , "");
							row.put("postalCode"                 , "");
							row.put("state"                      , "");
						}
					} else {
						// Para validar botones
						row.put("enabled"				 , "true");
						row.put("streetNumber"				 , "");
						row.put("colony"                     , "");
						row.put("delegationMunicipality"     , "");
						row.put("postalCode"                 , "");
						row.put("state"                      , "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * @author brodriguez
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeDemographics", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeDemographics(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeDemographics()) {
					user.getEmployee().getEmployeeDemographics().setId(userExists.getEmployee().getEmployeeDemographics().getId());
				} else {
					user.getEmployee().getEmployeeDemographics().setEmployeeGral(userExists.getEmployee());
				}
				// Modificar estado del empleado
				JSONObject jsonObject = Utils.searchObjectJson(userService.getRootFileCatalogsState().getInputStream(), 
						"id", user.getEmployee().getEmployeeDemographics().getState());
				user.getEmployee().getEmployeeDemographics().setState("" + jsonObject.get("name"));
				// Guardar empleado
				userService.saveUserEmployeeDemographic(userExists, user.getEmployee().getEmployeeDemographics());
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeDemographic/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeDemographic(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeDemographic(id, active)) {
				throw new Exception("Información de estudios no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @return map
	 */
	@RequestMapping(value = "fillState", method = RequestMethod.POST)
	public Map<Integer, String> fillSelectStateEmployeeDemographic() {
		Map<Integer, String> map = new HashMap<>();
		try {
			map = Utils.getStates(userService.getRootFileCatalogsState().getInputStream());
			if(map.isEmpty()) {
				throw new Exception("Información de estudios no modificado");
			}
		} catch (Exception e) {
			map.put(1, "Nok");
			map.put(2, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "fillCity/{id}", method = RequestMethod.POST)
	public Map<Integer, String> fillSelectCityEmployeeDemographic(@PathVariable int id) {
		Map<Integer, String> map = new HashMap<>();
		try {
			map = Utils.getCities(userService.getRootFileCatalogsMunicipality().getInputStream(), id);
			if(map.isEmpty()) {
				throw new Exception("Información de municipios no modificada");
			}
		} catch (Exception e) {
			map.put(1, "Nok");
			map.put(2, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "fillCityforState", method = RequestMethod.POST)
	public Map<String, String> fillCityforState(@RequestBody Map<String, String> model) {
		Map<String, String> map = new HashMap<>();
		try {
			if(model.isEmpty()) {
				throw new Exception("Estado vacio.");
			}
			// Buscar estados
			JSONObject jsonObjectState = Utils.searchObjectJson(userService.getRootFileCatalogsState().getInputStream(), "name", model.get("state"));
			map.put("idState", "" + jsonObjectState.get("id"));
			map.put("nameState", "" + jsonObjectState.get("name"));
			map.put("nameCitie", model.get("citie"));
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ***************************** Controllers de Employee Work Experience *********************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Ultimos 3 empleos
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeWorkexperience", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeWorkexperience() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeWorkexperience");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeWorkExperience() && user.getEmployee().getActive() > 0 && !user.getEmployee().getEmployeeWorkExperience().isEmpty()) {
							int countEmployee = 0;
							Iterator<EmployeeWorkExperience> iter = employeeGral.getEmployeeWorkExperience().iterator();
							while(iter.hasNext()) {
								countEmployee++;
								if(countEmployee <= 3) {
									EmployeeWorkExperience employeeWorkExperience = iter.next();
									if(employeeWorkExperience.getActive() > 0) {
										if(employeeWorkExperience.getType().equals("U")) {
											row.put("idU", "" + employeeWorkExperience.getId());
											row.put("companyU", "" + employeeWorkExperience.getCompany());
											row.put("positionU", "" + employeeWorkExperience.getPosition());
											row.put("salaryU", "" + employeeWorkExperience.getSalary());
											row.put("dateAdmissionU", "" + employeeWorkExperience.getDateAdmission());
											row.put("departureDateU", "" + employeeWorkExperience.getDepartureDate());
											row.put("reasonOfExitU", "" + employeeWorkExperience.getReasonOfExit());
											row.put("typeU", "" + employeeWorkExperience.getType());
										} else if(employeeWorkExperience.getType().equals("P")) {
											row.put("idP", "" + employeeWorkExperience.getId());
											row.put("companyP", "" + employeeWorkExperience.getCompany());
											row.put("positionP", "" + employeeWorkExperience.getPosition());
											row.put("salaryP", "" + employeeWorkExperience.getSalary());
											row.put("dateAdmissionP", "" + employeeWorkExperience.getDateAdmission());
											row.put("departureDateP", "" + employeeWorkExperience.getDepartureDate());
											row.put("reasonOfExitP", "" + employeeWorkExperience.getReasonOfExit());
											row.put("typeP", "" + employeeWorkExperience.getType());
										} else if(employeeWorkExperience.getType().equals("A")) {
											row.put("idA", "" + employeeWorkExperience.getId());
											row.put("companyA", "" + employeeWorkExperience.getCompany());
											row.put("positionA", "" + employeeWorkExperience.getPosition());
											row.put("salaryA", "" + employeeWorkExperience.getSalary());
											row.put("dateAdmissionA", "" + employeeWorkExperience.getDateAdmission());
											row.put("departureDateA", "" + employeeWorkExperience.getDepartureDate());
											row.put("reasonOfExitA", "" + employeeWorkExperience.getReasonOfExit());
											row.put("typeA", "" + employeeWorkExperience.getType());
										}
									} else {
										row.put(employeeWorkExperience.getType().equals("U") ? "idU" :
								                employeeWorkExperience.getType().equals("P") ? "idP" :
								                employeeWorkExperience.getType().equals("A") ? "idA" : ""
								                , employeeWorkExperience.getId());

										// Para validar botones
										row.put("enabled", "true");
										row.put("companyU", "");
										row.put("positionU", "");
										row.put("salaryU", "");
										row.put("dateAdmissionU", "");
										row.put("departureDateU", "");
										row.put("reasonOfExitU", "");
										row.put("typeU", "");
										row.put("companyP", "");
										row.put("positionP", "");
										row.put("salaryP", "");
										row.put("dateAdmissionP", "");
										row.put("departureDateP", "");
										row.put("reasonOfExitP", "");
										row.put("typeP", "");
										row.put("companyA", "");
										row.put("positionA", "");
										row.put("salaryA", "");
										row.put("dateAdmissionA", "");
										row.put("departureDateA", "");
										row.put("reasonOfExitA", "");
										row.put("typeA", "");
									}
								}
							}
						} else {
							// Para validar botones
							row.put("enabled", "true");
							row.put("idU", "");
							row.put("idP", "");
							row.put("idA", "");
							row.put("companyU", "");
							row.put("positionU", "");
							row.put("salaryU", "");
							row.put("dateAdmissionU", "");
							row.put("departureDateU", "");
							row.put("reasonOfExitU", "");
							row.put("typeU", "");
							row.put("companyP", "");
							row.put("positionP", "");
							row.put("salaryP", "");
							row.put("dateAdmissionP", "");
							row.put("departureDateP", "");
							row.put("reasonOfExitP", "");
							row.put("typeP", "");
							row.put("companyA", "");
							row.put("positionA", "");
							row.put("salaryA", "");
							row.put("dateAdmissionA", "");
							row.put("departureDateA", "");
							row.put("reasonOfExitA", "");
							row.put("typeA", "");
						}
					} else {
						// Para validar botones
						row.put("enabled", "true");
						row.put("idU", "");
						row.put("idP", "");
						row.put("idA", "");
						row.put("companyU", "");
						row.put("positionU", "");
						row.put("salaryU", "");
						row.put("dateAdmissionU", "");
						row.put("departureDateU", "");
						row.put("reasonOfExitU", "");
						row.put("typeU", "");
						row.put("companyP", "");
						row.put("positionP", "");
						row.put("salaryP", "");
						row.put("dateAdmissionP", "");
						row.put("departureDateP", "");
						row.put("reasonOfExitP", "");
						row.put("typeP", "");
						row.put("companyA", "");
						row.put("positionA", "");
						row.put("salaryA", "");
						row.put("dateAdmissionA", "");
						row.put("departureDateA", "");
						row.put("reasonOfExitA", "");
						row.put("typeA", "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeWorkExperience/{idU}/{idP}/{idA}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeWorkExperience(@RequestBody User user, 
			@PathVariable int idU, 
			@PathVariable int idP, 
			@PathVariable int idA) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			Iterator<EmployeeWorkExperience> iter = user.getEmployee().getEmployeeWorkExperience().iterator();
			while(iter.hasNext()) {
				EmployeeWorkExperience oneEmployeeWorkExperience = iter.next();
				if(user.getId() > 0) {
					// EDITAR empleado
					userService.saveUserEmployeeWorkExperience(userExists, oneEmployeeWorkExperience);
				}
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
		
	/**
	 * @author brodriguez
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeWorkExperience/{idU}/{idP}/{idA}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeWorkExperience(@PathVariable int idU, @PathVariable int idP, @PathVariable int idA, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeworkExperience(idU, active)) {
				throw new Exception("Información laboral no modificada");
			}
			if(!userService.activeEmployeeworkExperience(idA, active)) {
				throw new Exception("Información laboral no modificada");
			}
			if(!userService.activeEmployeeworkExperience(idP, active)) {
				throw new Exception("Información laboral no modificada");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ***************************** Controllers de Employee Employee Labor **********************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeLabor", method = RequestMethod.POST)
	public List<Map<String, Object>> findEmployeeLabor() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeLabor");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("uuid", user.getUuid());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeLabor() && user.getEmployee().getEmployeeLabor().getActive() > 0) {
							EmployeeLabor employeeLabor = employeeGral.getEmployeeLabor();
							row.put("idLabor", "" + employeeLabor.getId());
							row.put("dateAdmission", "" + employeeLabor.getDateAdmission());
							row.put("area", "" + employeeLabor.getArea());
							row.put("policySgmm", "" + employeeLabor.getPolicySgmm());
							row.put("employeeNumber", "" + employeeLabor.getEmployeeNumber());
							row.put("businessMail", "" + employeeLabor.getBusinessMail());
							row.put("position", "" + employeeLabor.getPosition());
							Date dateCompare = Utils.formatDate("" + employeeLabor.getDateAdmission(), "yyyy-MM-dd");
							if(dateCompare.compareTo(Utils.subtractDays("resta", Integer.parseInt(userService.getParameterForName("date-vacation").getValue()))) <= 0) {
								row.put("dateShowVacations", "true");
							}else {
								row.put("dateShowVacations"		, "");
							}
						} else {
							// Para validar botones
							row.put("enabled"					, "true");
							row.put("idLabor"					, "0");
							row.put("dateAdmission"				, "");
							row.put("area"						, "");
							row.put("policySgmm"				, "");
							row.put("employeeNumber"			, "");
							row.put("businessMail"				, "");
							row.put("position"					, "");
							row.put("dateShowVacations"			, "");
						}
					} else {
						// Para validar botones
						row.put("enabled"					, "true");
						row.put("idLabor"					, "0");
						row.put("dateAdmission"				, "");
						row.put("area"						, "");
						row.put("policySgmm"				, "");
						row.put("employeeNumber"			, "");
						row.put("businessMail"				, "");
						row.put("position"					, "");
						row.put("dateShowVacations"			, "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_labor
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeLabor", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeLabor(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeLabor()) {
					user.getEmployee().getEmployeeLabor().setId(userExists.getEmployee().getEmployeeLabor().getId());
				} else {
					user.getEmployee().getEmployeeLabor().setEmployeeGral(userExists.getEmployee());
				}
				userService.saveUserEmployeeLabor(userExists, user.getEmployee().getEmployeeLabor());
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "employee_labor" y eliminar "history_job"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeLabor/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeLabor(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeLabor(id, active)) {
				throw new Exception("Información de estudios no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeLaborHistory/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeLaborHistory(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeLabor() 
							&& !employeeGral.getEmployeeLabor().getHistoryJob().isEmpty()) {
						Iterator<HistoryJob> iter = employeeGral.getEmployeeLabor().getHistoryJob().iterator();
						while(iter.hasNext()) {
							HistoryJob historyJob = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + historyJob.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(historyJob.getActive() > 0) {
								row.put("idHistoryJob", "" + historyJob.getId());
								row.put("jobTitle", "" + historyJob.getJobTitle());
								row.put("dateIn", "" + historyJob.getDateIn());
								row.put("dateOu", "" + historyJob.getDateOu());
								row.put("jobArea", "" + historyJob.getJobArea());
								row.put("immediateBoss", "" + historyJob.getImmediateBoss());
								row.put("salaryIn", "" + historyJob.getSalaryIn());
								row.put("salaryOu", "" + historyJob.getSalaryOu());
							} else {
								// Para validar botones
								row.put("enabled"				 , "true");
								row.put("idHistoryJob", "" + historyJob.getId());
								row.put("jobTitle"			, "");
								row.put("dateIn"			, "");
								row.put("dateOu"			, "");
								row.put("jobArea"			, "");
								row.put("immediateBoss"		, "");
								row.put("salaryIn"			, "");
								row.put("salaryOu"			, "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"			, "true");
						row.put("idHistoryJob"		, "0");
						row.put("jobTitle"			, "");
						row.put("dateIn"			, "");
						row.put("dateOu"			, "");
						row.put("jobArea"			, "");
						row.put("immediateBoss"		, "");
						row.put("salaryIn"			, "");
						row.put("salaryOu"			, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * @param user
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeLaborHistory/{type}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeLaborHistory(@RequestBody User user, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeLabor()) {
					user.getEmployee().getEmployeeLabor().setId(userExists.getEmployee().getEmployeeLabor().getId());
				} else {
					user.getEmployee().getEmployeeLabor().setEmployeeGral(userExists.getEmployee());
				}
				if(type.equals("HISTORY")) {
					userService.saveUserEmployeeLaborHistory(userExists, user.getEmployee().getEmployeeLabor().getHistoryJob().iterator().next());
				}
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "history_job"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeLaborHistory/{id}/{active}/{type}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeLaborHistory(@PathVariable int id, @PathVariable int active, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeLaborHistory(id, active, type)) {
				throw new Exception("Información de Puesto y Salario no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Muestra las vacaciones del usuario 
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeVacations/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeVacations(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeLabor() 
							&& !employeeGral.getEmployeeLabor().getEmployeeVacations().isEmpty()) {
						Iterator<EmployeeVacations> iter = employeeGral.getEmployeeLabor().getEmployeeVacations().iterator();
						while(iter.hasNext()) {
							EmployeeVacations employeeVacations = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + employeeVacations.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(employeeVacations.getActive() > 0) {
								row.put("idEmployeeVacations", "" + employeeVacations.getId());
								row.put("daysTaken", "" + employeeVacations.getDaysTaken());
								row.put("daysForYear", "" + employeeVacations.getDaysForYear());
								row.put("daysPending", "" + 	employeeVacations.getDaysPending());
								row.put("reasonOfExit", "" + employeeVacations.getReasonOfExit());
								row.put("holiday", "" + employeeVacations.getHoliday());
							} else {
								// Para validar botones
								row.put("enabled"				 , "true");
								row.put("idEmployeeVacations", "" + employeeVacations.getId());
								row.put("daysTaken"			, "");
								row.put("daysForYear"		, "");
								row.put("daysPending"		, "");
								row.put("reasonOfExit"		, "");
								row.put("holiday"			, "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"			, "true");
						row.put("idEmployeeVacations"	, "0");
						row.put("daysTaken"				, "");
						row.put("daysForYear"			, "");
						row.put("daysPending"			, "");
						row.put("reasonOfExit"			, "");
						row.put("holiday"				, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Desactivar "employee_labor" y eliminar "employee_vacations"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeVacations/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeVacations(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeStudies(id, active)) {
				throw new Exception("Información de vacaciones no modificada");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * @param user
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeVacations", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeVacations(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado	
				if(null != userExists.getEmployee().getEmployeeLabor()) {
					user.getEmployee().getEmployeeLabor().setId(userExists.getEmployee().getEmployeeLabor().getId());
				} else {
					user.getEmployee().getEmployeeLabor().setEmployeeGral(userExists.getEmployee());
				}
				userService.saveUserEmployeeVacations(userExists, user.getEmployee().getEmployeeLabor().getEmployeeVacations().iterator().next());
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ************************* Controllers de Employee Employee Economics **********************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Acceso unicamente administrador
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeEconomics", method = RequestMethod.POST)
	public List<Map<String, Object>> findEmployeeEconomics() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeEconomics");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeEconomics() && user.getEmployee().getEmployeeEconomics().getActive() > 0) {
							EmployeeEconomics employeeEconomics = employeeGral.getEmployeeEconomics();
							row.put("idEconomics", "" + employeeEconomics.getId());
							row.put("salary", "" + employeeEconomics.getSalary());
							row.put("dateInfonavit", "" + employeeEconomics.getDateInfonavit());
							row.put("amountInfonavit", "" + employeeEconomics.getAmountInfonavit());
							row.put("dateAlimony", "" + employeeEconomics.getDateAlimony());
							row.put("amountAlimony", "" + employeeEconomics.getAmountAlimony());
						} else {
							// Para validar botones
							row.put("enabled"					, "true");
							row.put("idEconomics"				, "0");
							row.put("salary"					, "");
							row.put("dateInfonavit"				, "");
							row.put("amountInfonavit"			, "");
							row.put("dateAlimony"				, "");
							row.put("amountAlimony"				, "");
						}
					} else {
						// Para validar botones
						row.put("enabled"					, "true");
						row.put("idEconomics"				, "0");
						row.put("salary"					, "");
						row.put("dateInfonavit"				, "");
						row.put("amountInfonavit"			, "");
						row.put("dateAlimony"				, "");
						row.put("amountAlimony"				, "");
					}
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_economics
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeEconomics", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeEconomics(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeEconomics()) {
					user.getEmployee().getEmployeeEconomics().setId(userExists.getEmployee().getEmployeeEconomics().getId());
				} else {
					user.getEmployee().getEmployeeEconomics().setEmployeeGral(userExists.getEmployee());
				}
				userService.saveUserEmployeeEconomics(userExists, user.getEmployee().getEmployeeEconomics());
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "history_bonus" y eliminar "history_loan"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeEconomics/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeEconomics(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeEconomics(id, active)) {
				throw new Exception("Información de Datos Economicos no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeEconomicsHistoryBonus/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeEconomicsHistoryBonus(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeEconomics() 
							&& !employeeGral.getEmployeeEconomics().getHistoryBonus().isEmpty()) {
						Iterator<HistoryBonus> iter = employeeGral.getEmployeeEconomics().getHistoryBonus().iterator();
						while(iter.hasNext()) {
							HistoryBonus historyBonus = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + historyBonus.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(historyBonus.getActive() > 0) {
								row.put("idHistoryBonus", "" + historyBonus.getId());
								row.put("dateBonus", "" + historyBonus.getDateBonus());
								row.put("amountBonus", "" + historyBonus.getAmountBonus());
								row.put("conceptBonus", "" + historyBonus.getConceptBonus());
								row.put("status", "" + historyBonus.getStatus());
							} else {
								// Para validar botones
								row.put("enabled"		, "true");
								row.put("idHistoryBonus", "" + historyBonus.getId());
								row.put("dateBonus"		, "");
								row.put("amountBonus"	, "");
								row.put("conceptBonus"	, "");
								row.put("status"		, "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 , "true");
						row.put("idOpenCertifications"	 , "0");
						row.put("dateBonus"		, "");
						row.put("amountBonus"	, "");
						row.put("conceptBonus"	, "");
						row.put("status"		, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de User
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeEconomicsHistoryLoan/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeEconomicsHistoryLoan(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			User user = userService.findById(id);
			if(user.getActive() > 0) {
				//
				if(null != user.getEmployee()) {
					EmployeeGral employeeGral = user.getEmployee();
					if(null != employeeGral.getEmployeeEconomics() 
							&& !employeeGral.getEmployeeEconomics().getHistoryLoan().isEmpty()) {
						Iterator<HistoryLoan> iter = employeeGral.getEmployeeEconomics().getHistoryLoan().iterator();
						while(iter.hasNext()) {
							HistoryLoan historyLoan = iter.next();
							
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId() + "_" + historyLoan.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							if(historyLoan.getActive() > 0) {
								row.put("idHistoryLoan", "" + historyLoan.getId());
								row.put("dateLoan", "" + historyLoan.getDateLoan());
								row.put("amountLoan", "" + historyLoan.getAmountLoan());
								row.put("conceptLoan", "" + historyLoan.getConceptLoan());
								row.put("status", "" + historyLoan.getStatus());
							} else {
								// Para validar botones
								row.put("enabled"		, "true");
								row.put("idHistoryLoan"	, "" + historyLoan.getId());
								row.put("dateLoan"		, "");
								row.put("amountLoan"	, "");
								row.put("conceptLoan"	, "");
								row.put("status"		, "");
							}
							responseData.add(row);
						}
					} else {
						// 
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 , "true");
						row.put("idHistoryLoan"	 , "0");
						row.put("dateLoan"		, "");
						row.put("amountLoan"	, "");
						row.put("conceptLoan"	, "");
						row.put("status"		, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * @param user
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeEconomicsHistory/{type}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeEconomicsHistory(@RequestBody User user, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			if(user.getId() > 0) {
				// EDITAR empleado
				if(null != userExists.getEmployee().getEmployeeEconomics()) {
					user.getEmployee().getEmployeeEconomics().setId(userExists.getEmployee().getEmployeeEconomics().getId());
				} else {
					user.getEmployee().getEmployeeEconomics().setEmployeeGral(userExists.getEmployee());
				}
				if(type.equals("BONO")) {
					userService.saveUserEmployeeEconomicsHistory(userExists, user.getEmployee().getEmployeeEconomics().getHistoryBonus().iterator().next(), null);
				}
				if(type.equals("PRESTAMO")) {
					userService.saveUserEmployeeEconomicsHistory(userExists, null, user.getEmployee().getEmployeeEconomics().getHistoryLoan().iterator().next());
				}
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "history_bonus" o "history_loan" dependiendo del typo
	 * @param id
	 * @param active
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeEconomicsHistory/{id}/{active}/{type}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeEconomicsHistory(@PathVariable int id, @PathVariable int active, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeEconomicsHistory(id, active, type)) {
				throw new Exception("Información de historial no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ****************************** Controllers de Employee Legal ******************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Sanciones Administrativas y Permisos
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeLegal", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeLegal() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeLegal");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				// 
				User user = iterUser.next();
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					//
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeLegal() && !employeeGral.getEmployeeLegal().isEmpty()) {
							Iterator<EmployeeLegal> iter = employeeGral.getEmployeeLegal().iterator();
							while(iter.hasNext()) {
								EmployeeLegal employeeLegal = iter.next();
								
								Map<String, Object> row = new HashMap<>();
								row.put("id", user.getId() + "_" + employeeLegal.getId());
								row.put("name", user.getName());
								row.put("lastName", user.getLastName());
								if(employeeLegal.getActive() > 0) {
									row.put("administrativeActasAttention", "" + employeeLegal.getAdministrativeActasAttention());
									row.put("dischargeDate", "" + employeeLegal.getDischargeDate());
									row.put("reasonLow", "" + employeeLegal.getReasonLow());
									row.put("passwordGeneratedRrhh", "" + employeeLegal.getPasswordGeneratedRrhh());
									row.put("trainingPromissoryNotes", "" + employeeLegal.getTrainingPromissoryNotes());
								} else {
									// Para validar botones
									row.put("enabled"				 		, "true");
									row.put("administrativeActasAttention"	, "");
									row.put("dischargeDate"					, "");
									row.put("reasonLow"						, "");
									row.put("passwordGeneratedRrhh"			, "");
									row.put("trainingPromissoryNotes"		, "");
								}
								responseData.add(row);
							}
						} else {
							// 
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							
							// Para validar botones
							row.put("enabled"				 		, "true");
							row.put("administrativeActasAttention"	, "");
							row.put("dischargeDate"					, "");
							row.put("reasonLow"						, "");
							row.put("passwordGeneratedRrhh"			, "");
							row.put("trainingPromissoryNotes"		, "");
							
							responseData.add(row);
						}
					} else {
						//
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 		, "true");
						row.put("administrativeActasAttention"	, "");
						row.put("dischargeDate"					, "");
						row.put("reasonLow"						, "");
						row.put("passwordGeneratedRrhh"			, "");
						row.put("trainingPromissoryNotes"		, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_legal
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeLegal/{id}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeLegal(@RequestBody User user, @PathVariable int id) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			EmployeeLegal oneEmployeeLegal = user.getEmployee().getEmployeeLegal().iterator().next();
			if(user.getId() > 0) {
				// EDITAR empleado
				oneEmployeeLegal.setId(id);
			}
			userService.saveUserEmployeeLegal(userExists, oneEmployeeLegal);
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "employee_legal"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeLegal/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeLegal(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeLegal(id, active)) {
				throw new Exception("Empleado no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "viewActaEmployeeLegal/{idUser}/{idEmployeeLegal}", method = RequestMethod.POST)
	public Map<String, String> viewActaEmployeeLegal(@PathVariable int idUser, @PathVariable int idEmployeeLegal) {
		Map<String, String> map = new HashMap<>();
		try {
			User user = userService.findById(idUser);
			String email = user.getEmail();
			EmployeeLegal employeeLegal = userService.findEmployeeLegalById(idEmployeeLegal);
			String nameFile = employeeLegal.getAdministrativeActasAttention();
			// Traer base 64 archivo
			map = Utils.viewDocEmployee(email, nameFile, userService.getRootFileSystem(), "employee_legal" + File.separator + idEmployeeLegal);
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ************************ Controllers de Archivo Employee System Personal ******************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Lo debe subir el usuario, pero no debe poder borrarlo una vez ya integrado. 
	 * El usuario es responsable de mantenerlo actualizado.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findFilesSystemPersonal", method = RequestMethod.POST)
    public List<Map<String, Object>> findFilesSystemPersonal() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findFilesSystemPersonal");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				// 
				User user = iterUser.next();
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					//
					if(null != user.getEmployee()) {
						EmployeeGral employeeGral = user.getEmployee();
						if(null != employeeGral.getEmployeeFilesSystemPersonal() && !employeeGral.getEmployeeFilesSystemPersonal().isEmpty()) {
							Iterator<EmployeeFilesSystemPersonal> iter = employeeGral.getEmployeeFilesSystemPersonal().iterator();
							while(iter.hasNext()) {
								EmployeeFilesSystemPersonal employeeFilesSystemPersonal = iter.next();
								
								Map<String, Object> row = new HashMap<>();
								row.put("id", user.getId() + "_" + employeeFilesSystemPersonal.getId());
								row.put("name", user.getName());
								row.put("lastName", user.getLastName());
								if(employeeFilesSystemPersonal.getActive() > 0) {
									row.put("proofStudies", "" + employeeFilesSystemPersonal.getProofStudies());
									row.put("birthCertificate", "" + employeeFilesSystemPersonal.getBirthCertificate());
									row.put("titleProfessionalLicense", "" + employeeFilesSystemPersonal.getTitleProfessionalLicense());
									row.put("curp", "" + employeeFilesSystemPersonal.getCurp());
									row.put("rfc", "" + employeeFilesSystemPersonal.getRfc());
									row.put("imss", "" + employeeFilesSystemPersonal.getImss());
									row.put("infonavit", "" + employeeFilesSystemPersonal.getInfonavit());
									row.put("officialIdentification", "" + employeeFilesSystemPersonal.getOfficialIdentification());
									row.put("passportVisa", "" + employeeFilesSystemPersonal.getPassportVisa());
									row.put("noCriminalRecord", "" + employeeFilesSystemPersonal.getNoCriminalRecord());
									row.put("proofAddress", "" + employeeFilesSystemPersonal.getProofAddress());
									row.put("personalReferences", "" + employeeFilesSystemPersonal.getPersonalReferences());
									row.put("professionalCurriculum", "" + employeeFilesSystemPersonal.getProfessionalCurriculum());
									row.put("photo", "" + employeeFilesSystemPersonal.getPhoto());
									row.put("certifications", "" + employeeFilesSystemPersonal.getCertifications());
									row.put("recommendationLetter", "" + employeeFilesSystemPersonal.getRecommendationLetter());
								} else {
									// Para validar botones
									row.put("enabled"				 		, "true");
									row.put("proofStudies"					, ""); 
									row.put("birthCertificate"				, ""); 
									row.put("titleProfessionalLicense"		, ""); 
									row.put("curp"							, ""); 
									row.put("rfc"							, ""); 
									row.put("imss"							, ""); 
									row.put("infonavit"						, ""); 
									row.put("officialIdentification"		, ""); 
									row.put("passportVisa"					, ""); 
									row.put("noCriminalRecord"				, ""); 
									row.put("proofAddress"					, ""); 
									row.put("personalReferences"			, ""); 
									row.put("professionalCurriculum"		, ""); 
									row.put("photo"							, ""); 
									row.put("certifications"				, ""); 
									row.put("recommendationLetter"			, ""); 
								}
								responseData.add(row);
							}
						} else {
							// 
							Map<String, Object> row = new HashMap<>();
							row.put("id", user.getId());
							row.put("name", user.getName());
							row.put("lastName", user.getLastName());
							
							// Para validar botones
							row.put("enabled"				 		, "true");
							row.put("proofStudies"					, ""); 
							row.put("birthCertificate"				, ""); 
							row.put("titleProfessionalLicense"		, ""); 
							row.put("curp"							, ""); 
							row.put("rfc"							, ""); 
							row.put("imss"							, ""); 
							row.put("infonavit"						, ""); 
							row.put("officialIdentification"		, ""); 
							row.put("passportVisa"					, ""); 
							row.put("noCriminalRecord"				, ""); 
							row.put("proofAddress"					, ""); 
							row.put("personalReferences"			, ""); 
							row.put("professionalCurriculum"		, ""); 
							row.put("photo"							, ""); 
							row.put("certifications"				, "");
							row.put("recommendationLetter"			, "");
							
							responseData.add(row);
						}
					} else {
						//
						Map<String, Object> row = new HashMap<>();
						row.put("id", user.getId());
						row.put("name", user.getName());
						row.put("lastName", user.getLastName());
						
						// Para validar botones
						row.put("enabled"				 		, "true");
						row.put("proofStudies"					, ""); 
						row.put("birthCertificate"				, ""); 
						row.put("titleProfessionalLicense"		, ""); 
						row.put("curp"							, ""); 
						row.put("rfc"							, ""); 
						row.put("imss"							, ""); 
						row.put("infonavit"						, ""); 
						row.put("officialIdentification"		, ""); 
						row.put("passportVisa"					, ""); 
						row.put("noCriminalRecord"				, ""); 
						row.put("proofAddress"					, ""); 
						row.put("personalReferences"			, ""); 
						row.put("professionalCurriculum"		, ""); 
						row.put("photo"							, ""); 
						row.put("certifications"				, "");
						row.put("recommendationLetter"			, "");
						
						responseData.add(row);
					}
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/**
	 * Guardar employee_files_system_personal
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "saveUserEmployeeFilesSystemPersonal/{id}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeFilesSystemPersonal(@RequestBody User user, @PathVariable int id) {
		Map<Long, String> map = new HashMap<>();
		if (user == null || (null == user.getEmail() || user.getEmail().equals(""))){
            map.put(1L, "Nok");
			map.put(2L, "Usuario Invalido");
			return map;
        }
		User userExists = null;
		// Validar si empleado ya existe
		if(user.getId() > 0) {
			// EDITAR empleado
			User userEmployee = userService.findById(user.getId());
			if(null != userEmployee.getEmployee()) {
				user.getEmployee().setId(userEmployee.getEmployee().getId());
			}
			userExists = userService.findById(user.getId());
		} else {
			userExists = userService.findUserByEmail(user.getEmail());
		}
		if (userExists == null) {
			map.put(1L, "Nok");
			map.put(2L, "Usuario Inexistente " + user.getEmail());
			return map;
		}
		try {
			EmployeeFilesSystemPersonal oneEmployeeFilesSystemPersonal = user.getEmployee().getEmployeeFilesSystemPersonal().iterator().next();
			if(user.getId() > 0) {
				// EDITAR empleado
				oneEmployeeFilesSystemPersonal.setId(id);
			}
			userService.saveUserEmployeeFilesSystemPersonal(userExists, oneEmployeeFilesSystemPersonal);
			map.put(1L, "Ok");
			map.put(2L, "Empleado guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	/**
	 * Desactivar "employee_files_system_personal"
	 * @param id
	 * @param active
	 * @return
	 */
	@RequestMapping(value = "enabledUserEmployeeFilesSystemPersonal/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledUserEmployeeFilesSystemPersonal(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeEmployeeFilesSystemPersonal(id, active)) {
				throw new Exception("Empleado no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "viewEmployeeFilesSystemPersonal", method = RequestMethod.POST)
	public Map<String, String> viewEmployeeFilesSystemPersonal(@RequestBody Map<String, String> model) {
		Map<String, String> map = new HashMap<>();
		try {
			if(model.isEmpty()) {
				throw new Exception("Nombre del arcnivo vacio.");
			}
			User user = userService.findById(Integer.parseInt(model.get("idUser")));
			String email = user.getEmail();
			// EmployeeFilesSystemPersonal employeeFilesSystemPersonal = userService.findEmployeeFilesSystemPersonalById(idEmployeeFilesSystemPersonal);
			// String nameFile = employeeFilesSystemPersonal.getProofStudies();
			// Traer base 64 archivo
			map = Utils.viewDocEmployee(email, model.get("nameFile"), userService.getRootFileSystem(), "employee_files_system_personal" + File.separator + model.get("idEmployeeFilesSystemPersonal"));
		} catch (Exception e) {
			map.put("status", "Nok");
			map.put("error", "Error: " + e.getMessage());
		}
		return map;
	}
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ******************************** Controllers de Request Of Courses ************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */

	/**
	 * Guardar request of courses
	 * @param requestofcourses
	 * @return
	 */
	@RequestMapping(value = "saveRequestOfCourses/{idUser}", method = RequestMethod.POST)
	public Map<Long, String> saveRequestOfCourses(@PathVariable int idUser, @RequestBody RequestOfCourses requestofcourses) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null == requestofcourses) {
				throw new Exception("Solucitud vacia.");
			}
			userService.saveRequestOfCourses(requestofcourses, idUser);
			map.put(1L, "Ok");
			map.put(2L, "Solicitud guardada con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar solicitud: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "findEmployeeRequestOfCourses", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeRequestOfCourses() throws ServletException {
		Map<String, String> mapUserDetails = userService.getUserDetails("EmployeeGralController.findEmployeeRequestOfCourses");
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				// Validar si lo pueden ver ADMIN o USER
				if(user.getActive() > 0 && Utils.validateUserAdmin(user, mapUserDetails)) {
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", user.getLastName());
					List<Map<String, Object>> responseSql = dashboardService.areaEmployeePlan(user.getEmail());
					if(null != responseSql && responseSql.size() > 0) {
						Iterator<Map<String, Object>> iterSql = responseSql.iterator();
						while(iterSql.hasNext()) {
							Map<String, Object> mapIter = iterSql.next();
							row.put("descriptionCatalogArea", mapIter.get("descriptionCatalogArea"));
							row.put("descriptionCatalogAreaAux", mapIter.get("descriptionCatalogArea"));
							row.put("nameCertificationTrack", mapIter.get("nameCertificationTrack"));
							row.put("nameCertificationTrackAux", mapIter.get("nameCertificationTrack"));
						}
					} else {
						row.put("descriptionCatalogArea", "<a href=\"/adm/employee-labor\" style=\"color: red;\">FALTAN DATOS LABORALES</a>");
						row.put("descriptionCatalogAreaAux", "*");
						if(mapUserDetails.get("ROLE").equals("ADMIN")) {
							row.put("nameCertificationTrack", "<a href=\"/adm/certification-track-fases\" style=\"color: red;\">LLENAR PLAN DE CARRERA</a>");
							row.put("nameCertificationTrackAux", "*");
						} else {
							row.put("nameCertificationTrack", "<span title=\"Favor de solicitar tu plan de carrera a rh@buromc.com\" style=\"color: red;\">TU PLAN NO A SIDO DADO DE ALTA</span>");
							row.put("nameCertificationTrackAux", "*");
						}
					}
					row.put("email", user.getEmail());
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "findEmployeeTableValidateTechnologies", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeTableValidateTechnologies(@RequestBody Map<String, String> model) throws ServletException {
		LOGGER.info("## --> EmployeeGralController.findEmployeeTableValidateTechnologies() ##");
		LOGGER.info("Inicio Plan de carrera : {}", userService.getPrincipal("EmployeeGralController.findEmployeeTableValidateTechnologies"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.validateTechnologiesPerform(model.get("email"));
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- EmployeeGralController.findEmployeeTableValidateTechnologies() ##");
		return responseData;
    }
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ******************************* Controllers de Tracing Career Plans ***********************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	@RequestMapping(value = "findEmployeeTableStatusCoursesByUsers", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeTableStatusCoursesByUsers() throws ServletException {
		LOGGER.info("## --> EmployeeGralController.findEmployeeTableStatusCoursesByUsers() ##");
		LOGGER.info("Estatus de cursos por persona : {}", userService.getPrincipal("EmployeeGralController.findEmployeeTableStatusCoursesByUsers"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.statusCoursesByUsers();
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- EmployeeGralController.findEmployeeTableStatusCoursesByUsers() ##");
		return responseData;
    }
	
	@RequestMapping(value = "approveRequestCourse", method = RequestMethod.POST)
    public Map<Long, String> approveRequestCourse(@RequestBody Map<String, String> model) throws ServletException {
		LOGGER.info("## --> EmployeeGralController.approveRequestCourse() ##");
		Map<Long, String> map = new HashMap<>();
		try {
			//
			if(null != model && null != model.get("idRequestOfCourses")) {
				RequestOfCourses requestOfCourses = userService.findRequestOfCoursesById(Integer.parseInt(model.get("idRequestOfCourses")));
				if(null != requestOfCourses) {
					
				} else {
					throw new Exception("La solicitud \"" + model.get("tecProd") + "\" no se encuentra activa.");
				}
			} else {
				throw new Exception("La solicitud \"" + model.get("tecProd") + "\" no existe registrada correctamente.");
			}
			map.put(1L, "Ok");
			map.put(2L, "Solicitud aprobada.");
		} catch (Exception e){
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar solicitud: " + e.getMessage());
        }
		LOGGER.info("## <-- EmployeeGralController.approveRequestCourse() ##");
		return map;
    }
	
	@RequestMapping(value = "processingWordFiles", method = RequestMethod.POST)
    public Map<Long, String> processingWordFiles(@RequestBody Map<String, String> model) throws ServletException {
		LOGGER.info("## --> EmployeeGralController.processingWordFiles() ##");
		Map<Long, String> map = new HashMap<>();
		try {
			if(null != model.get("jsonRequestOfCourses") && !model.get("jsonRequestOfCourses").equals("")) {
				// Procesar XML
				
			} else {
				
			}
			map.put(1L, "Ok");
			map.put(2L, "Solicitud guardado con exito");
		} catch (Exception e){
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar solicitud: " + e.getMessage());
        }
		LOGGER.info("## <-- EmployeeGralController.processingWordFiles() ##");
		return map;
    }
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ************************************ Controllers de Dashboard *****************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	/**
	 * Solo visibles, no modificables para el usuario. Llenados por administrador
	 * @param id - Identificador de Area
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "findEmployeeByArea/{id}", method = RequestMethod.POST)
	public List<Map<String, Object>> findEmployeeByArea(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CatalogArea catalogArea = userService.findCatalogAreaById(id);
			List<EmployeeLabor> listEmployeeLabor = userService.findEmployeeArea(catalogArea.getName());
			Iterator<EmployeeLabor> iterEmployeeLabor = listEmployeeLabor.iterator();
			while(iterEmployeeLabor.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				EmployeeLabor employeeLabor = iterEmployeeLabor.next();
				EmployeeGral employeeGral = userService.findEmployeeGralById(employeeLabor.getEmployeeGral().getId());
				User user = userService.findById(employeeGral.getUser().getId());
						
				if(user.getActive() > 0) {
					//
					row.put("id", user.getId());
					row.put("name", StringUtils.capitalize(user.getName()));
					row.put("lastname", StringUtils.capitalize(user.getLastName()));
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }	
}