package com.kardex.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.kardex.service.DashboardService#areaEmployeePlan(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> areaEmployeePlan(String email) {
		LOGGER.info("## --> DashboardServiceImpl.areaEmployeePlan() ##");
		String sql = "select \r\n" + 
				"	g.id as idEmployeeGral, \r\n" + 
				"	ca.id as idCatalogArea, \r\n" + 
				"	ca.description as descriptionCatalogArea,\r\n" + 
				"	ce.name as nameCertificationTrack\r\n" + 
				"		from user u,\r\n" + 
				"			employee_gral g,\r\n" + 
				"				employee_labor l,\r\n" + 
				"					catalog_area ca,\r\n" + 
				"						certification_track ce\r\n" + 
				"		where u.email = '" + email + "'\r\n" + 
				"			and g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ce.area = ca.value";
		LOGGER.info("## <-- DashboardServiceImpl.areaEmployeePlan() ##");
		return jdbcTemplate.queryForList(sql);
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.DashboardService#validateTechnologiesPerform(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> validateTechnologiesPerform(String email) {
		LOGGER.info("## --> DashboardServiceImpl.validateTechnologiesPerform() ##");
		String sql = "select \r\n" + 
				"	x.id as cer_id, \r\n" + 
				"	x.area as cer_area, \r\n" + 
				"	x.name as cer_name, \r\n" + 
				"	y.name as fase_name, \r\n" + 
				"	z.name as block_name, \r\n" + 
				"	t.technology as tec_name, \r\n" + 
				"	t.product as tec_prod,\r\n" + 
				"	g.user_id as employee_gral_user_id,	-- employee_gral.user_id\r\n" + 
				"	g.id as id_employee_gral,	-- employee_gral.id\r\n" + 
				"	@approved:=IF(\r\n" + 
				"		(select r.approved \r\n" + 
				"			from request_of_courses r \r\n" + 
				"				where r.employee_gral_id = g.id \r\n" + 
				"					and  r.name_request_program = t.product) = 1,\r\n" + 
				"		1,\r\n" + 
				"		0\r\n" + 
				"	) as approved,\r\n" + 
				"	@realizado:=IF(\r\n" + 
				"		(select r.name_request_program \r\n" + 
				"			from request_of_courses r \r\n" + 
				"				where r.employee_gral_id = g.id \r\n" + 
				"					and  r.name_request_program = t.product) = t.product,\r\n" + 
				"		CONCAT(\r\n" + 
				"			IF(@approved = 1, \r\n" + 
				"			IF((select r.name_certification \r\n" + 
				"				from request_of_courses r \r\n" + 
				"					where r.employee_gral_id = g.id \r\n" + 
				"						and  r.name_request_program = t.product) != '',\r\n" + 
				"				'SI',\r\n" + 
				"				'AGREGAR ARCHIVO'\r\n" + 
				"			)\r\n" + 
				"			, \r\n" + 
				"			'FALTA APROBACION')),\r\n" + 
				"		'MANDAR SOLICITUD'\r\n" + 
				"	) as completed,\r\n" + 
				"	if(\r\n" + 
				"		(@realizado = 'SI'),\r\n" + 
				"			(select r.duration \r\n" + 
				"				from request_of_courses r \r\n" + 
				"					where r.employee_gral_id = g.id \r\n" + 
				"						and  r.name_request_program = t.product),\r\n" + 
				"			'0'\r\n" + 
				"	) as hr_completed\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u\r\n" + 
				"		where u.email = '" + email + "'\r\n" + 
				"			and g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"		order by y.name, z.name";
		LOGGER.info("## <-- DashboardServiceImpl.validateTechnologiesPerform() ##");
		return jdbcTemplate.queryForList(sql);
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.DashboardService#statusCoursesByUsers()
	 */
	@Override
	public List<Map<String, Object>> statusCoursesByUsers() {
		LOGGER.info("## --> DashboardServiceImpl.statusCoursesByUsers() ##");
		String sql = "select \r\n" + 
				"	u.email,\r\n" + 
				"	u.name,\r\n" + 
				"	u.last_name,\r\n" + 
				"	(select r.id \r\n" + 
				"		from request_of_courses r \r\n" + 
				"			where r.employee_gral_id = g.id \r\n" + 
				"				and  r.name_request_program = t.product) as id_request_of_courses,\r\n" + 
				"	x.id as cer_id, \r\n" + 
				"	x.area as cer_area, \r\n" + 
				"	x.name as cer_name, \r\n" + 
				"	y.name as fase_name, \r\n" + 
				"	z.name as block_name, \r\n" + 
				"	t.technology as tec_name, \r\n" + 
				"	t.product as tec_prod,\r\n" + 
				"	g.user_id as employee_gral_user_id,	-- employee_gral.user_id\r\n" + 
				"	g.id as id_employee_gral,	-- employee_gral.id\r\n" + 
				"	@approved:=IF(\r\n" + 
				"		(select r.approved \r\n" + 
				"			from request_of_courses r \r\n" + 
				"				where r.employee_gral_id = g.id \r\n" + 
				"					and  r.name_request_program = t.product) = 1,\r\n" + 
				"		1,\r\n" + 
				"		0\r\n" + 
				"	) as approved,\r\n" + 
				"	@realizado:=IF(\r\n" + 
				"		(select r.name_request_program \r\n" + 
				"			from request_of_courses r \r\n" + 
				"				where r.employee_gral_id = g.id \r\n" + 
				"					and  r.name_request_program = t.product) = t.product,\r\n" + 
				"		CONCAT(\r\n" + 
				"			IF(@approved = 1, \r\n" + 
				"			IF((select r.name_certification \r\n" + 
				"				from request_of_courses r \r\n" + 
				"					where r.employee_gral_id = g.id \r\n" + 
				"						and  r.name_request_program = t.product) != '',\r\n" + 
				"				'SI',\r\n" + 
				"				'AGREGAR ARCHIVO'\r\n" + 
				"			)\r\n" + 
				"			, \r\n" + 
				"			'FALTA APROBACION')),\r\n" + 
				"		'MANDAR SOLICITUD'\r\n" + 
				"	) as completed,\r\n" + 
				"	if(\r\n" + 
				"		(@realizado = 'SI'),\r\n" + 
				"			(select r.duration \r\n" + 
				"				from request_of_courses r \r\n" + 
				"					where r.employee_gral_id = g.id \r\n" + 
				"						and  r.name_request_program = t.product),\r\n" + 
				"			'0'\r\n" + 
				"	) as hr_completed,\r\n" + 
				"	(select r.duration \r\n" + 
				"		from request_of_courses r \r\n" + 
				"			where r.employee_gral_id = g.id \r\n" + 
				"				and  r.name_request_program = t.product) as hr_send\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u\r\n" + 
				"		where g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"		order by x.area, y.name, z.name";
		LOGGER.info("## <-- DashboardServiceImpl.statusCoursesByUsers() ##");
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public List<Map<String, Object>> certificationsByArea() {
		LOGGER.info("## --> DashboardServiceImpl.certificationsByArea() ##");
		String sql = "select x.area, count(t.product) as number_area\r\n" + 
				"	from certification_track x, \r\n" + 
				"			catalog_fase y, \r\n" + 
				"				catalog_fase_block z,\r\n" + 
				"					catalog_fase_block_technology t\r\n" + 
				"	where x.id = y.certification_track_id\r\n" + 
				"		and y.id = z.catalog_fase_id\r\n" + 
				"		and z.id = t.catalog_fase_block_id\r\n" + 
				"	group by x.area";
		LOGGER.info("## <-- DashboardServiceImpl.certificationsByArea() ##");
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> catalogArea() {
		LOGGER.info("## --> DashboardServiceImpl.catalogArea() ##");
		String sql = "select x.id, name, x.value, x.description from catalog_area x";
		LOGGER.info("## <-- DashboardServiceImpl.catalogArea() ##");
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> certificationsHrsByUser() {
		LOGGER.info("## --> DashboardServiceImpl.certificationsHrsByUser() ##");
		String sql = "select \r\n" + 
				"	x.id as cer_id, \r\n" + 
				"	CONCAT(u.name, ' ', u.last_name) as name_all,\r\n" + 
				"	ROUND(sum(r.duration)) as duration_hr,\r\n" + 
				"	count(u.email) as count_technology\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u,\r\n" + 
				"											request_of_courses r\r\n" + 
				"		where g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- este ligado a usario\r\n" + 
				"			and r.employee_gral_id = g.id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = t.product\r\n" + 
				"		group by u.email";
		LOGGER.info("## <-- DashboardServiceImpl.certificationsHrsByUser() ##");
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> certificationsHrsByArea() {
		LOGGER.info("## --> DashboardServiceImpl.certificationsHrsByArea() ##");
		String sql = "select \r\n" + 
				"	x.id as cer_id, \r\n" + 
				"	ca.value as name_all,\r\n" + 
				"	ROUND(sum(r.duration)) as duration_hr,\r\n" + 
				"	count(ca.value) as count_technology\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u,\r\n" + 
				"											request_of_courses r\r\n" + 
				"		where g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- este ligado a usario\r\n" + 
				"			and r.employee_gral_id = g.id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = t.product\r\n" + 
				"		group by ca.value";
		LOGGER.info("## <-- DashboardServiceImpl.certificationsHrsByArea() ##");
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> certificationsHrsByTI() {
		LOGGER.info("## --> DashboardServiceImpl.certificationsHrsByTI() ##");
		String sql = "select \r\n" + 
				"	'TI' as name_all,\r\n" + 
				"	ROUND(sum(r.duration)) as duration_hr,\r\n" + 
				"	count(ca.value) as count_technology\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u,\r\n" + 
				"											request_of_courses r\r\n" + 
				"		where g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- este ligado a usario\r\n" + 
				"			and r.employee_gral_id = g.id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = t.product";
		LOGGER.info("## <-- DashboardServiceImpl.certificationsHrsByTI() ##");
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> certificationsByAreaTechnology(String area, String technology) {
		LOGGER.info("## --> DashboardServiceImpl.certificationsByAreaTechnology() ##");
		String sql = "select \r\n" + 
				"	x.id as cer_id, \r\n" + 
				"	CONCAT(u.name, ' ', u.last_name) as name_all,\r\n" + 
				"	ROUND(sum(r.duration)) as duration_hr,\r\n" + 
				"	count(u.email) as count_technology,\r\n" + 
				"	ca.value as area,\r\n" + 
				"	r.name_request_program\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u,\r\n" + 
				"											request_of_courses r\r\n" + 
				"		where ca.value = IFNULL(" + area + ", ca.value) -- 'SSTI Soluciones de TI'\r\n" + 
				"			and g.user_id = u.user_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- este ligado a usario\r\n" + 
				"			and r.employee_gral_id = g.id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = IFNULL(?2 , r.name_request_program) -- 'Introduction to CyberArk Privileged Account Security'\r\n" + 
				"			and r.name_request_program = t.product\r\n" + 
				"		group by u.email";
		LOGGER.info("## <-- DashboardServiceImpl.certificationsByAreaTechnology() ##");
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public Map<String, Object> certificationDone(int uId) {
		LOGGER.info("## --> DashboardServiceImpl.certificationDone() ##");
		String sql = "select \r\n" + 
				"	count(x.id) as count_done\r\n" + 
				"	-- r.name_request_program\r\n" + 
				"		from certification_track x, \r\n" + 
				"				catalog_fase y, \r\n" + 
				"					catalog_fase_block z,\r\n" + 
				"						catalog_fase_block_technology t,\r\n" + 
				"							employee_gral g,\r\n" + 
				"								employee_labor l,\r\n" + 
				"									catalog_area ca,\r\n" + 
				"										user u,\r\n" + 
				"											request_of_courses r\r\n" + 
				"		where u.user_id = " + uId + "\r\n" + 
				"			and g.user_id = u.user_id\r\n" + 
				"			and g.id = r.employee_gral_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = t.product\r\n" + 
				"		order by y.NAME;";
		LOGGER.info("## <-- DashboardServiceImpl.certificationDone() ##");
		return jdbcTemplate.queryForMap(sql);
	}
	
	@Override
	public Map<String, Object> certificationToDo(int uId) {
		LOGGER.info("## --> DashboardServiceImpl.certificationToDo() ##");
		String sql = "\r\n" + 
				"SELECT (select count(x.id) AS count_to_do\r\n" + 
				"			from certification_track x, \r\n" + 
				"					catalog_fase y, \r\n" + 
				"						catalog_fase_block z,\r\n" + 
				"							catalog_fase_block_technology t,\r\n" + 
				"								employee_gral g,\r\n" + 
				"									employee_labor l,\r\n" + 
				"										catalog_area ca,\r\n" + 
				"											user u\r\n" + 
				"			where u.user_id = "+ uId+"\r\n" + 
				"				and g.user_id = u.user_id\r\n" + 
				"				and l.employee_gral = g.id\r\n" + 
				"				and ca.value = l.area\r\n" + 
				"				and ca.id = x.catalog_area_id\r\n" + 
				"				and x.id = y.certification_track_id\r\n" + 
				"				and y.id = z.catalog_fase_id\r\n" + 
				"				and z.id = t.catalog_fase_block_id\r\n" + 
				"				order by y.NAME)-\r\n" + 
				"									(select COUNT(x.id) AS count_done\r\n" + 
				"										from certification_track x, \r\n" + 
				"										catalog_fase y, \r\n" + 
				"											catalog_fase_block z,\r\n" + 
				"												catalog_fase_block_technology t,\r\n" + 
				"													employee_gral g,\r\n" + 
				"														employee_labor l,\r\n" + 
				"															catalog_area ca,\r\n" + 
				"																user u,\r\n" + 
				"																	request_of_courses r\r\n" + 
				"		where u.user_id = "+ uId+"\r\n" + 
				"			and g.user_id = u.user_id\r\n" + 
				"			and g.id = r.employee_gral_id\r\n" + 
				"			and l.employee_gral = g.id\r\n" + 
				"			and ca.value = l.area\r\n" + 
				"			and ca.id = x.catalog_area_id\r\n" + 
				"			and x.id = y.certification_track_id\r\n" + 
				"			and y.id = z.catalog_fase_id\r\n" + 
				"			and z.id = t.catalog_fase_block_id\r\n" + 
				"			-- validar si tomo curso\r\n" + 
				"			and r.name_request_program = t.product\r\n" + 
				"		order by y.NAME) AS count_to_do;";
		LOGGER.info("## <-- DashboardServiceImpl.certificationToDo() ##");
		return jdbcTemplate.queryForMap(sql);
	}
	
	@Override
	public Map<String, Object> percentageCertifications(int uId) {
		LOGGER.info("## --> DashboardServiceImpl.percentageCertifications() ##");
		String sql = "SELECT @realizado:=round((SELECT (count(x.id)*100)  AS count_done\r\n" + 
				"			FROM certification_track x, \r\n" + 
				"			catalog_fase y, \r\n" + 
				"				catalog_fase_block z,\r\n" + 
				"					catalog_fase_block_technology t,\r\n" + 
				"						employee_gral g,\r\n" + 
				"							employee_labor l,\r\n" + 
				"								catalog_area ca,\r\n" + 
				"									user u,\r\n" + 
				"										request_of_courses r\r\n" + 
				"				where u.user_id = "+ uId+"\r\n" + 
				"					and g.user_id = u.user_id\r\n" + 
				"					and g.id = r.employee_gral_id\r\n" + 
				"					and l.employee_gral = g.id\r\n" + 
				"					and ca.value = l.area\r\n" + 
				"					and ca.id = x.catalog_area_id\r\n" + 
				"					and x.id = y.certification_track_id\r\n" + 
				"					and y.id = z.catalog_fase_id\r\n" + 
				"					and z.id = t.catalog_fase_block_id\r\n" + 
				"					and r.name_request_program = t.product\r\n" + 
				"				order by y.NAME)/\r\n" + 
				"									(SELECT COUNT(x.id) AS count_to_do \r\n" + 
				"										from certification_track x, \r\n" + 
				"											catalog_fase y, \r\n" + 
				"												catalog_fase_block z,\r\n" + 
				"													catalog_fase_block_technology t,\r\n" + 
				"														employee_gral g,\r\n" + 
				"															employee_labor l,\r\n" + 
				"																catalog_area ca,\r\n" + 
				"																	user u\r\n" + 
				"										where u.user_id = "+ uId+"\r\n" + 
				"											and g.user_id = u.user_id\r\n" + 
				"											and l.employee_gral = g.id\r\n" + 
				"											and ca.value = l.area\r\n" + 
				"											and ca.id = x.catalog_area_id\r\n" + 
				"											and x.id = y.certification_track_id\r\n" + 
				"											and y.id = z.catalog_fase_id\r\n" + 
				"											and z.id = t.catalog_fase_block_id\r\n" + 
				"											order BY y.NAME))AS realizado, round(100-@realizado) AS pendiente;";
		LOGGER.info("## <-- DashboardServiceImpl.percentageCertifications() ##");
		return jdbcTemplate.queryForMap(sql);
	}
}
