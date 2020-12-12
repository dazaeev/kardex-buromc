package com.kardex.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		
			//.antMatchers(Constants.C_LOGIN).permitAll()
			.antMatchers("/").permitAll()
			
			// Reseteo
			.antMatchers("/forgets").permitAll()
			.antMatchers("/forget.html").permitAll()
			.antMatchers("/forget/access/**").permitAll()
			// Ldap
			.antMatchers("/cat/ldap/**").permitAll()
			
			// Registrar empleado
			.antMatchers("/registration-user").hasAuthority("ADMIN")
			.antMatchers("/adm/registration.html").hasAuthority("ADMIN")
	
			// Paginas y/o controlers (definido por ROL)
			// career
			.antMatchers("/career/**").hasAuthority("ADMIN")
			// request
			.antMatchers("/request/**").hasAuthority("ADMIN")
			// view
			.antMatchers("/view/**").hasAuthority("ADMIN")
			
			.antMatchers("/db/**").hasAuthority("USERS")
			
			// Validar Roles
			.antMatchers("/templates/header_bmc.html").hasAnyAuthority("ADMIN","USERS")
			
			// home ADMIN
			.antMatchers("/home").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/page/home.html").hasAnyAuthority("ADMIN")
			// home ADMIN and USERS
			.antMatchers("/page/home-local.html").hasAnyAuthority("ADMIN","USERS")
			
			// Busqueda avanzada
			.antMatchers("/adm/advanced-searchs").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/advanced-search.html").hasAnyAuthority("ADMIN","USERS")
			
			// Datos Generales
			.antMatchers("/adm/employee-all").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-all.html").hasAnyAuthority("ADMIN","USERS")
			// Datos Basicos
			.antMatchers("/adm/employee-gral").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-gral.html").hasAnyAuthority("ADMIN","USERS")
			// Datos de Estudios
			.antMatchers("/adm/employee-studies").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-studies.html").hasAnyAuthority("ADMIN","USERS")
			// Datos Demograficos
			.antMatchers("/adm/employee-demographics").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-demographics.html").hasAnyAuthority("ADMIN","USERS")
			// Experiencia Laboral
			.antMatchers("/adm/employee-workexperience").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-workexperience.html").hasAnyAuthority("ADMIN","USERS")
			// Datos Laborales
			.antMatchers("/adm/employee-labor").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-labor.html").hasAnyAuthority("ADMIN","USERS")
			// Datos Economicos
			.antMatchers("/adm/employee-economics").hasAnyAuthority("ADMIN")
			.antMatchers("/adm/employees-economics.html").hasAnyAuthority("ADMIN")
			// Datos Legales
			.antMatchers("/adm/employee-legal").hasAnyAuthority("ADMIN")
			.antMatchers("/adm/employees-legal.html").hasAnyAuthority("ADMIN")
			// Archivos Personales
			.antMatchers("/adm/employee-system-personal").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/employees-system-personal.html").hasAnyAuthority("ADMIN","USERS")
			
			// -- PLAN DE CARRERA --
			.antMatchers("/adm/certification-track").hasAnyAuthority("ADMIN","USERS")
			// ---- CATALOGOS FASE ----
			.antMatchers("/adm/certification-track-fases").hasAnyAuthority("ADMIN")
			.antMatchers("/adm/certification-track-fase.html").hasAnyAuthority("ADMIN")
			
			// -- CATALOGOS CONTROLLER --
			.antMatchers("/adm/catalogs").hasAnyAuthority("ADMIN")
			// ---- CATALOGOS AREA ----
			.antMatchers("/adm/catalogs-area").hasAnyAuthority("ADMIN")
			.antMatchers("/adm/catalog-area.html").hasAnyAuthority("ADMIN")
			// -- SEGUIMIENTO PLAN DE CARRERA ---
			.antMatchers("/adm/tracing-career-plan").hasAnyAuthority("ADMIN")
			.antMatchers("/adm/tracing-career-plans.html").hasAnyAuthority("ADMIN")
			// -- SOLICITUD DE CURSO PLAN DE CARRERA ---
			.antMatchers("/adm/request-of-course").hasAnyAuthority("ADMIN","USERS")
			.antMatchers("/adm/request-of-courses.html").hasAnyAuthority("ADMIN","USERS")
			
			// -- SWAGGER - API's ---
			.antMatchers("/swagger-ui.html").hasAnyAuthority("ADMIN")
			
			// -- BOT UI --
			.antMatchers(HttpMethod.GET, "/assets/bot-buro/**").permitAll()
			.antMatchers(HttpMethod.POST, "/home/create/helpPortal").permitAll()
			
			// Estilos permitodos para todos
			.antMatchers(HttpMethod.GET, "/css/**").permitAll()
			
			.antMatchers(HttpMethod.GET, "/assets/bootstrap-validator-master/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/css/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/fonts/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/js/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/js-controller/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/plugins/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/scss/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/select2/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/modal/**").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/file/**").permitAll()
	
			.antMatchers(HttpMethod.GET, "/assets/embed/**").permitAll()
			.antMatchers(HttpMethod.GET, "/images/**").permitAll()
			
			// Login
			.anyRequest().fullyAuthenticated().and().formLogin()
			
			// Pagina de Inicio
			//.loginPage(Constants.C_LOGIN).permitAll()
			.loginPage("/").permitAll()
			.defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password")
			//.and().logout().logoutSuccessUrl(Constants.C_LOGIN)
			.and().logout().logoutSuccessUrl("/")
			.and().csrf().disable();
	}
}