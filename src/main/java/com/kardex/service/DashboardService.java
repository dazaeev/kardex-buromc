package com.kardex.service;

import java.util.List;
import java.util.Map;

public interface DashboardService {
	
	/**
	 * A que area pertenece para su plan de carrera
	 * @param email
	 * @return
	 */
	List<Map<String, Object>> areaEmployeePlan(String email);
	/**
	 * Valida que tecnologias tiene que realizar y ya a realizado v3
	 * @param email
	 * @return
	 */
	List<Map<String, Object>> validateTechnologiesPerform(String email);
	/**
	 * Estatus de cursos por persona <br>
	 * Ejm:	FALTA APROBACION <br>
	 * 		approved = 0 FALTA APROBACION	(esperar a que se apruebe) <br>
	 * 		approved = 1 AGREGAR ARCHIVO	(pasa al siguiente estatus)
	 * @return
	 */
	List<Map<String, Object>> statusCoursesByUsers();
	/**
	 * @return
	 */
	List<Map<String, Object>> certificationsByArea();
	/**
	 * @return
	 */
	List<Map<String, Object>> catalogArea();
	/**
	 * @return
	 */
	List<Map<String, Object>> certificationsHrsByUser();
	/**
	 * @return
	 */
	List<Map<String, Object>> certificationsHrsByArea();
	/**
	 * @return
	 */
	List<Map<String, Object>> certificationsHrsByTI();
	/**
	 * Certificaciones por area
	 * @param area
	 * @param technology
	 * @return
	 */
	List<Map<String, Object>> certificationsByAreaTechnology(String area, String technology);
	/**
	 * @return
	 */
	Map<String, Object> certificationDone(int uId);
	/**
	 * @return
	 */
	Map<String, Object> certificationToDo(int uId);
	/**
	 * @return
	 */
	Map<String, Object> percentageCertifications(int uId);
}
