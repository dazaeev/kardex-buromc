select * from job_place;
select * from area;
select * from `user` for update; -- $2a$10$M4POjSQN2OKWt3lr.e.1xub4zYWcFJvdTfzITwKznuDF6HK0ZEImS
select * from user_role;
select * from `role` for update;
select * from employee_gral for update;
select * from employee_notification for update;

select * from menu_role;
select * from menu for update;

rollback;

select * from send;
select * from company;
commit;

desc `user`;

commit;

desc employee_gral;

select * from employee_gral;
select * from employee_studies for update;
	desc employee_studies;
	select * from open_certifications for update;
	select * from close_certifications for update;

select x.id, x.name, x.description, x.value, x.active from parameter x;
select * from mail_template for update;

select * from employee_demographics;
select * from employee_workexperience for update;

select * from employee_labor for update;
	select * from history_job;
	select * from employee_vacations;
	
select * from employee_economics for update;
	select * from history_bonus;
	select * from history_loan;
	
select * from employee_legal for update; -- N
	select * from call_attention;
	select * from administrative_act;
	select * from promissory_notes;

select * from employee_training; -- N
select * from employee_files_system_personal for update; -- N
select * from employee_files_system_rh; -- N

-- delete from employee_gral;
-- delete from employee_studies;

select * from `user`;
select * from menu order by role_id_menu, sub_menu for update;
select * from menu_role;

commit;

-- calculo de sumas
SELECT DATE_ADD("2017-06-15", INTERVAL 10 DAY);
select x.date_expiration as date_expiration, 
	date_add(x.date_expiration, interval 92 day) as date_expiration_s,
	date as date_current,
	date_add(date, interval 92 day) as date_current_s
from open_certifications x;

-- Calogos
select * from catalog_area;
	select * from catalog_work_place where catalog_area_id = 7;

-- Plan de carrera
select * from certification_track order by catalog_area_id;
	select * from catalog_fase order by certification_track_id;
		select * from catalog_fase_block order by catalog_fase_id;
			select * from catalog_fase_block_technology order by catalog_fase_block_id;
--
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t
		where x.catalog_area_id = (select ca.id
									from user u, 
										employee_gral g,
											employee_labor l,
												catalog_area ca
									where u.email = 'ngonzalez@buromc.mx'
										and g.user_id = u.user_id
										and l.employee_gral = g.id
										and ca.value = l.area)
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
		order by y.name;

-- solicitud de cursos o certificaciones
select r.* from request_of_courses r; -- SSTI Soluciones de TI, Sophos C1		Gemalto L1
	select r.* from request_of_courses r; -- 
select x.* from employee_gral x;
select x.* from user x;

-- A que area pertenece para su plan de carrera
select 
	g.id as idEmployeeGral, 
	ca.id as idCatalogArea, 
	ca.description as descriptionCatalogArea,
	ce.name as nameCertificationTrack
		from user u,
			employee_gral g,
				employee_labor l,
					catalog_area ca,
						certification_track ce
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ce.area = ca.value;

-- Valida que tecnologias ya realizado
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod,
	r.duration as duration_hr
	-- r.name_request_program
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u,
											request_of_courses r
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and g.id = r.employee_gral_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
			-- validar si tomo curso
			and r.name_request_program = t.product
		order by y.name;

-- Valida que tecnologias tiene que realizar
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
		order by y.name, z.name;

-- Certificaciones por area
select x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name,
	y.name as f_name, 
	z.name as fb_name, 
	t.technology, 
	t.product
	from certification_track x, 
			catalog_fase y, 
				catalog_fase_block z,
					catalog_fase_block_technology t
	where x.id = y.certification_track_id
		and y.id = z.catalog_fase_id
		and z.id = t.catalog_fase_block_id
	order by cer_area, f_name;

-- Grafica "Certificaciones dadas de alta por Área"
select x.id, name, x.value, x.description from catalog_area x;
select x.area, count(t.product) as number_area
	from certification_track x, 
			catalog_fase y, 
				catalog_fase_block z,
					catalog_fase_block_technology t
	where x.id = y.certification_track_id
		and y.id = z.catalog_fase_id
		and z.id = t.catalog_fase_block_id
	group by x.area;

-- Grafica "Horas de capacitación registradas por colaborador"
select 
	x.id as cer_id, 
	CONCAT(u.name, ' ', u.last_name) as name_all,
	ROUND(sum(r.duration)) as duration_hr,
	count(u.email) as count_technology
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u,
											request_of_courses r
		where 
			-- u.email = 'brodriguez@buromc.com'
			g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
			-- este ligado a usario
			and r.employee_gral_id = g.id
			-- validar si tomo curso
			and r.name_request_program = t.product
		group by u.email;

-- Grafica "Horas de capacitación registradas por area"
select 
	x.id as cer_id, 
	ca.value as name_all,
	ROUND(sum(r.duration)) as duration_hr,
	count(ca.value) as count_technology
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u,
											request_of_courses r
		where g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
			-- este ligado a usario
			and r.employee_gral_id = g.id
			-- validar si tomo curso
			and r.name_request_program = t.product
		group by ca.value;

-- Grafica "Horas de capacitación registradas TI"
select 
	'TI' as name_all,
	ROUND(sum(r.duration)) as duration_hr,
	count(ca.value) as count_technology
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u,
											request_of_courses r
		where g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
			-- este ligado a usario
			and r.employee_gral_id = g.id
			-- validar si tomo curso
			and r.name_request_program = t.product;

-- Grafica "Horas de capacitación registradas Área (todos los colaboradores)"
select 
	x.id as cer_id, 
	CONCAT(u.name, ' ', u.last_name) as name_all,
	ROUND(sum(r.duration)) as duration_hr,
	count(u.email) as count_technology,
	ca.value as area,
	r.name_request_program
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u,
											request_of_courses r
		where ca.value = IFNULL(:area, ca.value) -- 'SSTI Soluciones de TI'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
			-- este ligado a usario
			and r.employee_gral_id = g.id	
			-- validar si tomo curso
			and r.name_request_program = IFNULL(:technology , r.name_request_program) -- 'Introduction to CyberArk Privileged Account Security'
			and r.name_request_program = t.product
		group by u.email;

select 
	ROUND(sum(x.duration)) as duration_hr,
	count(x.name_provider) as count_technology
		from request_of_courses x;

select x.*, y.*, z.* from 
	user x, employee_gral y, request_of_courses z
		where x.email = 'emedina@buromc.com'
			and x.user_id = y.user_id
			and z.employee_gral_id = y.id;

select * from catalog_area;
select * from employee_labor; -- SSTI Servicios, Curso ITIL OSA
select * from request_of_courses;

select * from history_bonus;

--
select * from user;
select us.name
	from user us, employee_gral g, catalog_area ar, employee_labor el 
where us.user_id = g.user_id
	and el.employee_gral = g.id
	and ar.name = el.area
	and ar.id= 13;

select u.name
	from user u, 
		employee_gral g,
			employee_labor l,
				catalog_area ca
	where ca.id = 13
		and g.user_id = u.user_id
		and l.employee_gral = g.id
		and ca.value = l.area;

select * from site.data_center_sensors;
select x.name, x.value, x.modified 
	from site.data_center_sensors x
where x.name = 'Temperatura' -- Humedad, Temperatura, Particula, Humo
	order by x.modified desc limit 20;

select x.href, x.title, x.image from menu x where x.html like '%dato%';
select m.id, m.html, m.menu, m.role_id_menu, m.sub_menu, m.href, m.title, m.image 
	from menu m, role r 
		where m.role_id_menu = r.role_id 
	and r.role_id = (select ur.role_id 
						from user u, user_role ur 
							where u.user_id = ur.user_id 
						and u.email = 'ngonzalez@buromc.com') 
	and (m.html like '%plan%' 
	or m.menu like '%plan%')
		order by r.role, m.menu, m.sub_menu;

select * from employee_gral;
select * from audit;

select * from access_reset;
select email, password, active from user where email='ngonzalez@buromc.com';
select u.*, r.* from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email='ngonzalez@buromc.com';
-- 
-- 
select * from employee_notification for update;
select x.email, y.value, y.id
	from user x, employee_notification y
where x.user_id = y.user_id
	order by x.user_id;
--
--
select * from employee_message;
select now();

select u.* 
	from user u, user_role ur, role r
where u.user_id = ur.user_id
	and ur.role_id = r.role_id
	and r.role = 'ADMIN'
	and u.email like '%ngonzale%';

--
SELECT u.* 
	FROM user u
WHERE u.last_name REGEXP 'herrera';

select
	concat("SELECT * FROM ",
	t_table,
	" WHERE ",
	t_field,
	" REGEXP 'Datos';") as query
from
	temp_details;

--
SET @query = 'SELECT 1;';
SELECT @query INTO OUTFILE '/opt/temp.sql';
--
set @Expression = 'select * from user INTO OUTFILE \'/opt/temp.sql\';';
PREPARE myquery FROM @Expression;
execute myquery;
DEALLOCATE prepare myquery;

SELECT * FROM employee_notification WHERE value REGEXP 'Bienvenido al portal.' into outfile '/opt/xtemp.csv'
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n';

select REPLACE(REPLACE(UNIX_TIMESTAMP(), ' ', '-'), ':', '-');
SELECT TO_BASE64(AES_ENCRYPT(UUID(), UUID()));
SELECT replace(rand(), '.', '');

select * from temp_details;
CALL test.get_table('test', 'Datos l', '/opt/filesystem/kardex/db/ngonzalez_buromc_mx');
SHOW VARIABLES LIKE "secure_file_priv";

SELECT * FROM employee_notification WHERE value REGEXP 'Bienvenido al portal.';

select group_concat(concat("\"",column_name,"\""))
	from information_schema.columns
where table_name = 'employee_notification'
	and table_schema = 'test'
order by ordinal_position;


select "id","active","date","modified","value","user_id"
union all
SELECT "id","active","date","modified","value","user_id" FROM employee_notification WHERE value REGEXP 'Bienvenido al portal.' into outfile '/opt/xtemp.csv'
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n';

select concat(
	'Procesados -- ', 
	(
		select count(*) from temp_details
	),
	' registros. (',
	(
		select group_concat(concat(t_table, ': ', t_field)) from temp_details
	),
	')'
)	as status;
select concat(t.t_table, '|' ,t.t_file) from temp_details t;


select concat("SELECT * FROM ", t_table, " WHERE ", t_field, " REGEXP '", 'Datos l', "';") as query from temp_details;



-- Valida que tecnologias tiene que realizar y ya a realizado
select * from employee_gral;
select * from request_of_courses;
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod,
	g.user_id as employee_gral_user_id,	-- employee_gral.user_id
	g.id as id_employee_gral,	-- employee_gral.id
	@realizado:=IF(
		(select r.name_request_program 
			from request_of_courses r 
				where r.employee_gral_id = g.id 
					and  r.name_request_program = t.product) = t.product,
		'SI',
		'NO'
	) as completed,
	if(
		(@realizado = 'SI'),
			(select r.duration 
				from request_of_courses r 
					where r.employee_gral_id = g.id 
						and  r.name_request_program = t.product),
			'0'
	) as hr_completed
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
		order by y.name, z.name;

-- Valida que tecnologias tiene que realizar y ya a realizado v2
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod,
	g.user_id as employee_gral_user_id,	-- employee_gral.user_id
	g.id as id_employee_gral,	-- employee_gral.id
	@approved:=IF(
		(select r.approved 
			from request_of_courses r 
				where r.employee_gral_id = g.id 
					and  r.name_request_program = t.product) = 1,
		1,
		0
	) as approved,
	@realizado:=IF(
		(select r.name_request_program 
			from request_of_courses r 
				where r.employee_gral_id = g.id 
					and  r.name_request_program = t.product) = t.product,
		CONCAT(if(@approved = 1, 'SI', 'FALTA APROBACION')),
		'MANDAR SOLICITUD'
	) as completed,
	if(
		(@realizado = 'SI'),
			(select r.duration 
				from request_of_courses r 
					where r.employee_gral_id = g.id 
						and  r.name_request_program = t.product),
			'0'
	) as hr_completed
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
		order by y.name, z.name;
		
-- Valida que tecnologias tiene que realizar y ya a realizado v3
select x.id, x.name_certification, x.approved, x.name_request_program from request_of_courses x;
select 
	x.id as cer_id, 
	x.area as cer_area, 
	x.name as cer_name, 
	y.name as fase_name, 
	z.name as block_name, 
	t.technology as tec_name, 
	t.product as tec_prod,
	g.user_id as employee_gral_user_id,	-- employee_gral.user_id
	g.id as id_employee_gral,	-- employee_gral.id
	@approved:=IF(
		(select r.approved 
			from request_of_courses r 
				where r.employee_gral_id = g.id 
					and  r.name_request_program = t.product) = 1,
		1,
		0
	) as approved,
	@realizado:=IF(
		(select r.name_request_program 
			from request_of_courses r 
				where r.employee_gral_id = g.id 
					and  r.name_request_program = t.product) = t.product,
		CONCAT(
			IF(@approved = 1, 
			IF((select r.name_certification 
				from request_of_courses r 
					where r.employee_gral_id = g.id 
						and  r.name_request_program = t.product) != '',
				'SI',
				'AGREGAR ARCHIVO'
			)
			, 
			'FALTA APROBACION')),
		'MANDAR SOLICITUD'
	) as completed,
	if(
		(@realizado = 'SI'),
			(select r.duration 
				from request_of_courses r 
					where r.employee_gral_id = g.id 
						and  r.name_request_program = t.product),
			'0'
	) as hr_completed
		from certification_track x, 
				catalog_fase y, 
					catalog_fase_block z,
						catalog_fase_block_technology t,
							employee_gral g,
								employee_labor l,
									catalog_area ca,
										user u
		where u.email = 'ngonzalez@buromc.mx'
			and g.user_id = u.user_id
			and l.employee_gral = g.id
			and ca.value = l.area
			and ca.id = x.catalog_area_id
			and x.id = y.certification_track_id
			and y.id = z.catalog_fase_id
			and z.id = t.catalog_fase_block_id
		order by y.name, z.name;