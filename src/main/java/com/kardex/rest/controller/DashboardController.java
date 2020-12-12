package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.service.DashboardService;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller exclusivo para Tablero
 */
@RestController
@RequestMapping("adm/dashboard/")
public class DashboardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private DashboardService dashboardService;
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 *************************************** Controllers de Tablero ****************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	@RequestMapping(value = "dashboardCountEmployee", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCountEmployee() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCountEmployee() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCountEmployee"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			Map<String, Object> row = new HashMap<>();
			//
			Long countUser = userService.countUserAll();
			row.put("count", countUser);
			//
			responseData.add(row);
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCountEmployee() ##");
		return responseData;
    }
	
	/**
	 * Certificaciones por ÁREA
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCertificationsByArea", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCertificationsByArea() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCertificationsByArea() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCertificationsByArea"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.certificationsByArea();
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCertificationsByArea() ##");
		return responseData;
    }
	
	/**
	 * Horas de capacitación registradas por colaborador
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCertificationsHrsByUser", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCertificationsHrsByUser() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCertificationsHrsByUser() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCertificationsHrsByUser"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.certificationsHrsByUser();
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCertificationsHrsByUser() ##");
		return responseData;
    }
	
	/**
	 * Horas de capacitación registradas por Área
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCertificationsHrsByArea", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCertificationsHrsByArea() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCertificationsHrsByArea() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCertificationsHrsByArea"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.certificationsHrsByArea();
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCertificationsHrsByArea() ##");
		return responseData;
    }
	
	/**
	 * Horas de capacitación registradas por TI
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCertificationsHrsByTI", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCertificationsHrsByTI() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCertificationsHrsByTI() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCertificationsHrsByTI"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.certificationsHrsByTI();
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCertificationsHrsByTI() ##");
		return responseData;
    }
	
	/**
	 * Certificaciones por area
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCertificationsByAreaTechnology", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCertificationsByAreaTechnology() throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCertificationsByAreaTechnology() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCertificationsByAreaTechnology"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			responseData = dashboardService.certificationsByAreaTechnology("", "");
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCertificationsByAreaTechnology() ##");
		return responseData;
    }
	
	
	/**
	 * Certificaciones hechas y por hacer por empleado
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardCountCertificationsDoneAndToDo/{id}", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardCountCertificationsDoneAndToDo(@PathVariable int id) throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardCountEmployee() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardCountCertificationsDoneAndToDo"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			Map<String, Object> row = new HashMap<>();
			Map<String, Object> row2 = new HashMap<>();
			//
			Map<String, Object> countCertificationsDone = dashboardService.certificationDone(id);
			//
			row.put("howmany", countCertificationsDone.get("count_done"));
			row.put("where", "Hechas");
			//
			responseData.add(row);
			//
			Map<String, Object> countCertificationsToDo = dashboardService.certificationToDo(id);
			//
			row2.put("howmany", countCertificationsToDo.get("count_to_do"));
			row2.put("where","Por hacer");
			//
			responseData.add(row2);
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardCountCertificationsDoneAndToDo() ##");
		return responseData;
    }
	
	/**
	 * Porcentage de Certificaciones hechas y por hacer por empleado
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "dashboardPercentageDoneAndToDo/{id}", method = RequestMethod.POST)
	public List<Map<String, Object>> dashboardPercentageDoneAndToDo(@PathVariable int id) throws ServletException {
		LOGGER.info("## --> DashboardController.dashboardPercentageDoneAndToDo() ##");
		LOGGER.info("Bienvenido Dashboard: {}", userService.getPrincipal("DashboardController.dashboardPercentageDoneAndToDo"));
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			Map<String, Object> row = new HashMap<>();
			Map<String, Object> row2 = new HashMap<>();
			//
			Map<String, Object> countCertificationsPercentage = dashboardService.percentageCertifications(id);
			//
			row.put("percentage", countCertificationsPercentage.get("realizado"));
			row.put("type", "% Realizado");
			//
			responseData.add(row);
			//
			row2.put("percentage", countCertificationsPercentage.get("pendiente"));
			row2.put("type","% Pendiente");
			//
			responseData.add(row2);
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
		LOGGER.info("## <-- DashboardController.dashboardPercentageDoneAndToDo() ##");
		return responseData;
    }
	
}