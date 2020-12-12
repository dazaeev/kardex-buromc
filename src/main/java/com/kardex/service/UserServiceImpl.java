package com.kardex.service;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.kardex.repository.CatalogAreaRepository;
import com.kardex.repository.CatalogFaseBlockRepository;
import com.kardex.repository.CatalogFaseBlockTechnologyRepository;
import com.kardex.repository.CatalogFaseRepository;
import com.kardex.repository.CatalogWorkPlaceRepository;
import com.kardex.repository.CertificationTrackRepository;
import com.kardex.repository.CloseCertificationsRepository;
import com.kardex.repository.EmployeeDemographicRepository;
import com.kardex.repository.EmployeeEconomicsRepository;
import com.kardex.repository.EmployeeFilesSystemPersonalRepository;
import com.kardex.repository.EmployeeGralRepository;
import com.kardex.repository.EmployeeLaborRepository;
import com.kardex.repository.EmployeeLegalRepository;
import com.kardex.repository.EmployeeNotificationRepository;
import com.kardex.repository.EmployeeRepository;
import com.kardex.repository.EmployeeStudiesRepository;
import com.kardex.repository.EmployeeVacationsRepository;
import com.kardex.repository.EmployeeWorkExperienceRepository;
import com.kardex.repository.HistoryBonusRepository;
import com.kardex.repository.HistoryJobRepository;
import com.kardex.repository.HistoryLoanRepository;
import com.kardex.repository.MailTemplateRepository;
import com.kardex.repository.OpenCertificationsRepository;
import com.kardex.repository.ParameterRepository;
import com.kardex.repository.RequestOfCoursesRepository;
import com.kardex.repository.RoleRepository;
import com.kardex.repository.UserRepository;
import com.kardex.utils.Constants;
import com.kardex.utils.Utils;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private ParameterRepository parameterRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeGralRepository employeeGralRepository;
	@Autowired
	private EmployeeStudiesRepository employeeStudiesRepository;
	@Autowired
	private EmployeeDemographicRepository employeeDemographicRepository;
	@Autowired
	private EmployeeWorkExperienceRepository employeeWorkExperienceRepository;
	@Autowired
	private EmployeeLaborRepository employeeLaborRepository;
	@Autowired
	private EmployeeEconomicsRepository employeeEconomicsRepository;
	@Autowired
	private EmployeeLegalRepository employeeLegalRepository;
	@Autowired
	private EmployeeFilesSystemPersonalRepository employeeFilesSystemPersonalRepository;
	@Autowired
	private OpenCertificationsRepository openCertificationsRepository;
	@Autowired
	private CloseCertificationsRepository closeCertificationsRepository;
	@Autowired
	private HistoryJobRepository historyJobRepository;
	@Autowired
	private EmployeeVacationsRepository employeeVacationsRepository;
	@Autowired
	private HistoryBonusRepository historyBonusRepository;
	@Autowired
	private HistoryLoanRepository historyLoanRepository;
	@Autowired
	private MailTemplateRepository mailTemplateRepository;
	@Autowired
	private CatalogAreaRepository catalogAreaRepository;
	@Autowired
	private CatalogWorkPlaceRepository catalogWorkPlaceRepository;
	@Autowired
	private CertificationTrackRepository certificationTrackRepository;
	@Autowired
	private CatalogFaseRepository catalogFaseRepository;
	@Autowired
	private CatalogFaseBlockRepository catalogFaseBlockRepository;
	@Autowired
	private CatalogFaseBlockTechnologyRepository catalogFaseBlockTechnologyRepository;
	@Autowired
	private RequestOfCoursesRepository requestofcoursesrepository;
	@Autowired
	private EmployeeNotificationRepository employeeNotificationRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${local.root.file.system}")
	private String rootFileSystem;
	
	@Value("${local.root.file.system.mysql}")
	private String rootBdFileSystem;
	
	@Value("${local.root.file.catalogs.state}")
	private Resource rootFileCatalogsState;
	
	@Value("${local.root.file.catalogs.municipality}")
	private Resource rootFileCatalogsMunicipality;
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getParameterForId(int)
	 */
	@Override
	public Parameter getParameterForId(int id) {
		return parameterRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getParameterForName(java.lang.String)
	 */
	@Override
	public Parameter getParameterForName(String name) {
		return parameterRepository.findByName(name);
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getPrincipal(java.lang.String)
	 */
	@Override
	public Object getPrincipal(String action) {
		LOGGER.info("-> Accion: {}", action);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			LOGGER.info("--> Usuario: {}", username);
		} else {
			String username = principal.toString();
			LOGGER.info("--> Usuario: {}", username);
		}
		return principal;
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getEmail()
	 */
	@Override
	public String getEmail() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
			LOGGER.info("--> getEmail: {}", username);
		} else {
			username = principal.toString();
			LOGGER.info("--> getEmail: {}", username);
		}
		return username;
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getUserDetails(java.lang.String)
	 */
	@Override
	public Map<String, String> getUserDetails(String action) {
		LOGGER.info("-> Accion: {}", action);
		Map<String, String> map = new TreeMap<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			map.put("USERNAME", username);
			LOGGER.info("--> Usuario: {}", username);
		} else {
			String username = principal.toString();
			map.put("USERNAME", username);
			LOGGER.info("--> Usuario: {}", username);
		}
		if (principal instanceof UserDetails) {
			Collection<? extends GrantedAuthority> authorities = ((UserDetails)principal).getAuthorities();
			for (GrantedAuthority auth : authorities) {
				map.put("ROLE", auth.getAuthority());
				LOGGER.info("---> Rol: {}", auth.getAuthority());
	        }
		}
		return map;
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#getAuthority(java.lang.Object)
	 */
	@Override
	public String getAuthority(Object obj) {
		String authority = "";
		if (obj instanceof UserDetails) {
			Collection<? extends GrantedAuthority> authorities = ((UserDetails)obj).getAuthorities();
			for (GrantedAuthority auth : authorities) {
				authority = auth.getAuthority();
				LOGGER.info("---> Rol: {}", authority);
	        }
		}
		return authority;
	}
	
	@Override
	public String getRootFileSystem() {
		return rootFileSystem;
	}
	
	@Override
	public String getBdRootFileSystem() {
		return rootBdFileSystem;
	}
	
	@Override
	public Resource getRootFileCatalogsState() {
		return rootFileCatalogsState;
	}
	
	@Override
	public Resource getRootFileCatalogsMunicipality() {
		return rootFileCatalogsMunicipality;
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findById(int)
	 */
	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUser(com.kardex.model.User)
	 */
	@Override
	public void saveUser(User user) {
		Role userRole = roleRepository.findById(user.getRoles().iterator().next().getId());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		// Mandar correo de cambio de estatus
		String msg = mailTemplateRepository.findByNameAndActive("EMAIL-WELCOME", 1).getBody()
				.replace("{username}", user.getName().trim() + " " + user.getLastName().trim())
				.replace("{account}", user.getAccount().trim());
		String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
				Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
				getParameterForName("EMAIL-USERNAME").getValue(), 
				getParameterForName("EMAIL-KEY").getValue(), 
				getParameterForName("EMAIL-SETFROM").getValue(), 
				"BeITalent (Bienvenida)", 
				"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
				msg,
				user.getEmail().replaceAll(";", "") + ";" + getParameterForName("EMAIL-TO").getValue(),
				// Mandar Archivo (Manual)
				false,
				"", 
				"", 
				"");
		if(!"".equals(respEmail)) {
			LOGGER.info("Envio de correo incorrecto: {}", respEmail);
		}
		// Envia notificacion
		String emailTo = getParameterForName("EMAIL-TO").getValue();
		String []args = {
			emailTo + Constants.C_VERTICAL_BAR + "Se creo usuario: " + user.getEmail(),
			user.getEmail() + Constants.C_VERTICAL_BAR + "Bienvenido al portal."
		};
		notificationTo(args);
		//
	}
	
	@Override
	public void saveUserForLdap(User user) {
		String keyRh = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(keyRh));
		userRepository.save(user);
	}
	
	@Override
	public void saveUserForActive(User user) {
		user.setPassword("");
		userRepository.save(user);
	}
	
	@Override
	public void sendEmailActive(User user) {
		String msg = mailTemplateRepository.findByNameAndActive("EMAIL-ACTIVE", 1).getBody()
				.replace("{username}", user.getName().trim() + " " + user.getLastName().trim());
		String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
				Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
				getParameterForName("EMAIL-USERNAME").getValue(), 
				getParameterForName("EMAIL-KEY").getValue(), 
				getParameterForName("EMAIL-SETFROM").getValue(), 
				"BeITalent (Activad@)", 
				"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
				msg,
				user.getEmail().replaceAll(";", "") + ";" + getParameterForName("EMAIL-TO").getValue(),
				// Mandar Archivo (Manual)
				false,
				"", 
				"", 
				"");
		if(!"".equals(respEmail)) {
			LOGGER.info("Envio de correo incorrecto: {}", respEmail);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findRoleAll()
	 */
	@Override
	public List<Role> findRoleAll() {
		return roleRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeAll()
	 */
	@Override
	public List<EmployeeGral> findEmployeeAll() {
		return employeeRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeUser(int, int)
	 */
	@Override
	public boolean activeUser(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeUser() ##");
		User user = userRepository.findById(id);
		if(null == user) {
			throw new Exception("Usuario inexistente");
		}
		user.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeUser() ##");
		return (null != userRepository.save(user));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findUserAll()
	 */
	@Override
	public List<User> findUserAll() {
		return userRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#countUserAll()
	 */
	@Override
	public Long countUserAll() {
		return userRepository.count();
	}	

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findUserByEmail(java.lang.String)
	 */
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeGral(com.kardex.model.User, com.kardex.model.EmployeeGral)
	 */
	@Override
	public void saveUserEmployeeGral(User user, EmployeeGral employeeGral) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeGral() ##");
		EmployeeGral employee = employeeGral;
		user.setEmployee(null);
		employeeGral.setUser(user);
		employeeRepository.save(employee);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeGral() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeGral(int, int)
	 */
	@Override
	public boolean activeEmployeeGral(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeGral() ##");
		User user = userRepository.findById(id);
		EmployeeGral employeeGral;
		if(null == user) {
			throw new Exception("Empleado inexistente");
		}
		employeeGral = user.getEmployee();
		employeeGral.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeGral() ##");
		return (null != employeeGralRepository.save(employeeGral));
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeStudies(com.kardex.model.User, com.kardex.model.EmployeeStudies)
	 */
	@Override
	public void saveUserEmployeeStudies(User user, EmployeeStudies employeeStudies) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeStudies() ##");
		employeeStudies.setEmployeeGral(user.getEmployee());
		employeeStudiesRepository.save(employeeStudies);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeStudies() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeStudiesCertificate(com.kardex.model.User, com.kardex.model.OpenCertifications, com.kardex.model.CloseCertifications)
	 */
	@Override
	public void saveUserEmployeeStudiesCertificate(User user, OpenCertifications openCertifications, CloseCertifications closeCertifications) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeStudiesCertificate() ##");
		// Guardar archivo fisico
		// Validar que tipo de operacion
		if(null != openCertifications) {
			openCertifications.setEmployeeStudies(user.getEmployee().getEmployeeStudies());
			openCertificationsRepository.save(openCertifications);
			String fatherFolder = "open_certifications";
			if(null != openCertifications.getFileCertification() && !openCertifications.getFileCertification().isEmpty()) {
				String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
						openCertifications.getNameCertification(), 
						openCertifications.getFileCertification(), 
						rootFileSystem, 
						fatherFolder + File.separator + openCertifications.getId());
				if(!result.equals("Ok")) {
					// Eliminamos nombre de archivo en BD
					openCertifications.setFileCertification("");
					openCertificationsRepository.save(openCertifications);
					throw new Exception(result);
				}
			}
		}
		if(null != closeCertifications) {
			closeCertifications.setEmployeeStudies(user.getEmployee().getEmployeeStudies());
			closeCertificationsRepository.save(closeCertifications);
			String fatherFolder = "close_certifications";
			if(null != closeCertifications.getFileCertification() && !closeCertifications.getFileCertification().isEmpty()) {
				String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
						closeCertifications.getNameCertification(), 
						closeCertifications.getFileCertification(), 
						rootFileSystem, 
						fatherFolder + File.separator + closeCertifications.getId());
				if(!result.equals("Ok")) {
					// Eliminamos nombre de archivo en BD
					closeCertifications.setFileCertification("");
					closeCertificationsRepository.save(closeCertifications);
					throw new Exception(result);
				}
			}
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeStudiesCertificate() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeStudies(int, int)
	 */
	@Override
	public boolean activeEmployeeStudies(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeStudies() ##");
		User user = userRepository.findById(id);
		EmployeeGral employeeGral;
		if(null == user) {
			throw new Exception("Empleado inexistente");
		}
		employeeGral = user.getEmployee();
		if(null == employeeGral) {
			throw new Exception("Empleado inexistente");
		}
		EmployeeStudies employeeStudies = employeeGral.getEmployeeStudies();
		// Activar / Desactivar Información de estudios
		employeeStudies.setActive(active);
		// Borrar certificaciones
		if(!employeeStudies.getOpenCertifications().isEmpty()) {
			openCertificationsRepository.deleteInBatch(employeeStudies.getOpenCertifications());
		}
		if(!employeeStudies.getCloseCertifications().isEmpty()) {
			closeCertificationsRepository.deleteInBatch(employeeStudies.getCloseCertifications());
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeStudies() ##");
		return (null != employeeStudiesRepository.save(employeeStudies));
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeStudiesCertificate(int, int, java.lang.String)
	 */
	@Override
	public boolean activeEmployeeStudiesCertificate(int id, int active, String type) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeStudiesCertificate() ##");
		if(type.equals("VIGENTE")) {
			OpenCertifications openCertifications = openCertificationsRepository.findById(id);
			if(null == openCertifications) {
				throw new Exception("Certificación inexistente");
			}
			// Borrar certificaciones
			openCertificationsRepository.delete(openCertifications);
		}
		if(type.equals("VENCIDA")) {
			CloseCertifications closeCertifications = closeCertificationsRepository.findById(id);
			if(null == closeCertifications) {
				throw new Exception("Certificación inexistente");
			}
			// Borrar certificaciones
			closeCertificationsRepository.delete(closeCertifications);
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeStudiesCertificate() ##");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeDemographic(com.kardex.model.User, com.kardex.model.EmployeeDemographics)
	 */
	@Override
	public void saveUserEmployeeDemographic(User user, EmployeeDemographics employeeDemographics) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeDemographic() ##");
		employeeDemographics.setEmployeeGral(user.getEmployee());
		employeeDemographicRepository.save(employeeDemographics);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeDemographic() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeDemographic(int, int)
	 */
	@Override
	public boolean activeEmployeeDemographic(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeDemographic() ##");
		User user = userRepository.findById(id);
		EmployeeGral employeeGral;
		if(null == user) {
			throw new Exception("Empleado inexistente");
		}
		employeeGral = user.getEmployee();
		if(null == employeeGral) {
			throw new Exception("Empleado inexistente");
		}
		EmployeeDemographics employeeDemographics = employeeGral.getEmployeeDemographics();
		employeeDemographics.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeDemographic() ##");
		return (null != employeeDemographicRepository.save(employeeDemographics));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeWorkExperience(com.kardex.model.User, com.kardex.model.EmployeeWorkExperience)
	 */
	@Override
	public void saveUserEmployeeWorkExperience(User user, EmployeeWorkExperience employeeWorkExperience)throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeWorkExperience() ##");
		employeeWorkExperience.setEmployeeGral(user.getEmployee());
		employeeWorkExperienceRepository.save(employeeWorkExperience);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeWorkExperience() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeworkExperience(int, int)
	 */
	@Override
	public boolean activeEmployeeworkExperience(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeworkExperience() ##");
		EmployeeWorkExperience employeeworkExperience = employeeWorkExperienceRepository.findById(id);
		if(null == employeeworkExperience) {
			throw new Exception("Empleado inexistente");
		}
		employeeworkExperience.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeworkExperience() ##");
		return (null != employeeWorkExperienceRepository.save(employeeworkExperience));
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeLabor(com.kardex.model.User, com.kardex.model.EmployeeLabor)
	 */
	@Override
	public void saveUserEmployeeLabor(User user, EmployeeLabor employeeLabor) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeLabor() ##");
		employeeLabor.setEmployeeGral(user.getEmployee());
		employeeLaborRepository.save(employeeLabor);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeLabor() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeLaborHistory(com.kardex.model.User, com.kardex.model.HistoryJob)
	 */
	@Override
	public void saveUserEmployeeLaborHistory(User user, HistoryJob historyJob) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeLaborHistory() ##");
		// Validar que tipo de operacion
		if(null != historyJob) {
			historyJob.setEmployeeLabor(user.getEmployee().getEmployeeLabor());
			historyJobRepository.save(historyJob);
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeLaborHistory() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeLaborHistory(int, int, java.lang.String)
	 */
	@Override
	public boolean activeEmployeeLaborHistory(int id, int active, String type) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeLaborHistory() ##");
		if(type.equals("HISTORY")) {
			HistoryJob historyJob = historyJobRepository.findById(id);
			if(null == historyJob) {
				throw new Exception("Certificación inexistente");
			}
			// Borrar certificaciones
			historyJobRepository.delete(historyJob);
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeLaborHistory() ##");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeLabor(int, int)
	 */
	@Override
	public boolean activeEmployeeLabor(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeLabor() ##");
		User user = userRepository.findById(id);
		EmployeeGral employeeGral;
		if(null == user) {
			throw new Exception("Empleado inexistente");
		}
		employeeGral = user.getEmployee();
		if(null == employeeGral) {
			throw new Exception("Empleado inexistente");
		}
		EmployeeLabor employeeLabor = employeeGral.getEmployeeLabor();
		// Activar / Desactivar Información de estudios
		employeeLabor.setActive(active);
		// Borrar certificaciones
		if(!employeeLabor.getHistoryJob().isEmpty()) {
			historyJobRepository.deleteInBatch(employeeLabor.getHistoryJob());
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeLabor() ##");
		return (null != employeeLaborRepository.save(employeeLabor));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeVacations(com.kardex.model.User, com.kardex.model.EmployeeVacations)
	 */
	@Override
	public void saveUserEmployeeVacations(User user, EmployeeVacations employeeVacations) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeVacations() ##");
		// Validar que tipo de operacion
		if(null != employeeVacations) {
			employeeVacations.setEmployeeLabor(user.getEmployee().getEmployeeLabor());
			employeeVacationsRepository.save(employeeVacations);
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeVacations() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeEconomics(com.kardex.model.User, com.kardex.model.EmployeeEconomics)
	 */
	@Override
	public void saveUserEmployeeEconomics(User user, EmployeeEconomics employeeEconomics) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeEconomics() ##");
		employeeEconomics.setEmployeeGral(user.getEmployee());
		employeeEconomicsRepository.save(employeeEconomics);
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeEconomics() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeEconomics(int, int)
	 */
	@Override
	public boolean activeEmployeeEconomics(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeEconomics() ##");
		User user = userRepository.findById(id);
		EmployeeGral employeeGral;
		if(null == user) {
			throw new Exception("Empleado inexistente");
		}
		employeeGral = user.getEmployee();
		if(null == employeeGral) {
			throw new Exception("Empleado inexistente");
		}
		EmployeeEconomics employeeEconomics = employeeGral.getEmployeeEconomics();
		// Activar / Desactivar Información de estudios
		employeeEconomics.setActive(active);
		// Borrar certificaciones
		if(!employeeEconomics.getHistoryBonus().isEmpty()) {
			historyBonusRepository.deleteInBatch(employeeEconomics.getHistoryBonus());
		}
		if(!employeeEconomics.getHistoryLoan().isEmpty()) {
			historyLoanRepository.deleteInBatch(employeeEconomics.getHistoryLoan());
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeEconomics() ##");
		return (null != employeeEconomicsRepository.save(employeeEconomics));
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeEconomicsHistory(com.kardex.model.User, com.kardex.model.HistoryBonus, com.kardex.model.HistoryLoan)
	 */
	@Override
	public void saveUserEmployeeEconomicsHistory(User user, HistoryBonus historyBonus, HistoryLoan historyLoan) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeEconomicsHistory() ##");
		// Validar que tipo de operacion
		if(null != historyBonus) {
			historyBonus.setEmployeeEconomics(user.getEmployee().getEmployeeEconomics());
			historyBonusRepository.save(historyBonus);
			// Mandar correo de cambio de estatus
			String msg = mailTemplateRepository.findByNameAndActive("EMAIL-STATUS-HISTORY-BONUS-LOAN", 1).getBody()
					.replace("{username}", user.getName())
					.replace("{type}", "bono")
					.replace("{concept}", historyBonus.getConceptBonus())
					.replace("{date}", historyBonus.getDateBonus())
					.replace("{amount}", Utils.formatNumber(historyBonus.getAmountBonus(), "es", "MX"));
			String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
					Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
					getParameterForName("EMAIL-USERNAME").getValue(), 
					getParameterForName("EMAIL-KEY").getValue(), 
					getParameterForName("EMAIL-SETFROM").getValue(), 
					"Bono " + historyBonus.getStatus(), 
					"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
					msg,
					user.getEmail().replaceAll(";", "") + ";" + getParameterForName("EMAIL-TO").getValue(),
					false,
					"", "", "");
			if(!"".equals(respEmail)) {
				LOGGER.info("Envio de correo incorrecto: {}", respEmail);
			}
			// Envia notificacion
			String emailTo = getParameterForName("EMAIL-TO").getValue();
			String []args = {
				emailTo + Constants.C_VERTICAL_BAR + "Solicitud de bono \"" + historyBonus.getConceptBonus() + "\"</br> por " + user.getEmail(),
				user.getEmail() + Constants.C_VERTICAL_BAR + "Bono otorgado </br>\"" + historyBonus.getConceptBonus() + "\""
			};
			notificationTo(args);
			//
		}
		if(null != historyLoan) {
			historyLoan.setEmployeeEconomics(user.getEmployee().getEmployeeEconomics());
			historyLoanRepository.save(historyLoan);
			// Mandar correo de cambio de estatus
			String msg = mailTemplateRepository.findByNameAndActive("EMAIL-STATUS-HISTORY-BONUS-LOAN", 1).getBody()
					.replace("{username}", user.getName())
					.replace("{type}", "prestamo")
					.replace("{concept}", historyLoan.getConceptLoan())
					.replace("{date}", historyLoan.getDateLoan())
					.replace("{amount}", Utils.formatNumber(historyLoan.getAmountLoan(), "es", "MX"));
			String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
					Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
					getParameterForName("EMAIL-USERNAME").getValue(), 
					getParameterForName("EMAIL-KEY").getValue(), 
					getParameterForName("EMAIL-SETFROM").getValue(), 
					"Prestamo " + historyLoan.getStatus(), 
					"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
					msg,
					user.getEmail().replaceAll(";", "") + ";" + getParameterForName("EMAIL-TO").getValue(),
					false,
					"", "", "");
			if(!"".equals(respEmail)) {
				LOGGER.info("Envio de correo incorrecto: {}", respEmail);
			}
			// Envia notificacion
			String emailTo = getParameterForName("EMAIL-TO").getValue();
			String []args = {
				emailTo + Constants.C_VERTICAL_BAR + "Solicitud de prestamo \"" + historyLoan.getConceptLoan() + "\"</br> por " + user.getEmail(),
				user.getEmail() + Constants.C_VERTICAL_BAR + "Prestamo otorgado </br>\"" + historyLoan.getConceptLoan() + "\""
			};
			notificationTo(args);
			//
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeEconomicsHistory() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeEconomicsHistory(int, int, java.lang.String)
	 */
	@Override
	public boolean activeEmployeeEconomicsHistory(int id, int active, String type) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeEconomicsHistory() ##");
		if(type.equals("BONO")) {
			HistoryBonus historyBonus = historyBonusRepository.findById(id);
			if(null == historyBonus) {
				throw new Exception("Historial inexistente");
			}
			// Borrar
			historyBonusRepository.delete(historyBonus);
		}
		if(type.equals("PRESTAMO")) {
			HistoryLoan historyLoan = historyLoanRepository.findById(id);
			if(null == historyLoan) {
				throw new Exception("Historial inexistente");
			}
			// Borrar
			historyLoanRepository.delete(historyLoan);
		}
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeEconomicsHistory() ##");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeLegal(com.kardex.model.User, com.kardex.model.EmployeeEconomics)
	 */
	@Override
	public void saveUserEmployeeLegal(User user, EmployeeLegal employeeLegal) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeLegal() ##");
		employeeLegal.setEmployeeGral(user.getEmployee());
		employeeLegalRepository.save(employeeLegal);
		// Guardar archivo fisico
		if(null != employeeLegal.getFileAdministrativeActasAttention() && !employeeLegal.getFileAdministrativeActasAttention().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeLegal.getAdministrativeActasAttention(), 
					employeeLegal.getFileAdministrativeActasAttention(), 
					rootFileSystem, 
					"employee_legal" + File.separator + employeeLegal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeLegal.setAdministrativeActasAttention("");
				employeeLegalRepository.save(employeeLegal);
				throw new Exception(result);
			}
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeLegal() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeLegal(int, int)
	 */
	@Override
	public boolean activeEmployeeLegal(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeLegal() ##");
		EmployeeLegal employeeLegal = employeeLegalRepository.findById(id);
		if(null == employeeLegal) {
			throw new Exception("Empleado inexistente");
		}
		employeeLegal.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeLegal() ##");
		return (null != employeeLegalRepository.save(employeeLegal));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeLegalById(int)
	 */
	@Override
	public EmployeeLegal findEmployeeLegalById(int id) throws Exception {
		return employeeLegalRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveUserEmployeeFilesSystemPersonal(com.kardex.model.User, com.kardex.model.EmployeeFilesSystemPersonal)
	 */
	@Override
	public void saveUserEmployeeFilesSystemPersonal(User user, EmployeeFilesSystemPersonal employeeFilesSystemPersonal) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveUserEmployeeFilesSystemPersonal() ##");
		employeeFilesSystemPersonal.setEmployeeGral(user.getEmployee());
		employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
		// Guardar archivo fisico
		String fatherFolder = "employee_files_system_personal";
		if(null != employeeFilesSystemPersonal.getFileProofStudies() && !employeeFilesSystemPersonal.getFileProofStudies().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getProofStudies(), 
					employeeFilesSystemPersonal.getFileProofStudies(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileProofStudies("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileBirthCertificate() && !employeeFilesSystemPersonal.getFileBirthCertificate().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getBirthCertificate(), 
					employeeFilesSystemPersonal.getFileBirthCertificate(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileBirthCertificate("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileTitleProfessionalLicense() && !employeeFilesSystemPersonal.getFileTitleProfessionalLicense().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getTitleProfessionalLicense(), 
					employeeFilesSystemPersonal.getFileTitleProfessionalLicense(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileTitleProfessionalLicense("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileCurp() && !employeeFilesSystemPersonal.getFileCurp().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getCurp(), 
					employeeFilesSystemPersonal.getFileCurp(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileCurp("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileRfc() && !employeeFilesSystemPersonal.getFileRfc().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getRfc(), 
					employeeFilesSystemPersonal.getFileRfc(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileRfc("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileImss() && !employeeFilesSystemPersonal.getFileImss().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getImss(), 
					employeeFilesSystemPersonal.getFileImss(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileImss("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileInfonavit() && !employeeFilesSystemPersonal.getFileInfonavit().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getInfonavit(), 
					employeeFilesSystemPersonal.getFileInfonavit(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileInfonavit("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileOfficialIdentification() && !employeeFilesSystemPersonal.getFileOfficialIdentification().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getOfficialIdentification(), 
					employeeFilesSystemPersonal.getFileOfficialIdentification(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileOfficialIdentification("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFilePassportVisa() && !employeeFilesSystemPersonal.getFilePassportVisa().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getPassportVisa(), 
					employeeFilesSystemPersonal.getFilePassportVisa(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFilePassportVisa("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileNoCriminalRecord() && !employeeFilesSystemPersonal.getFileNoCriminalRecord().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getNoCriminalRecord(), 
					employeeFilesSystemPersonal.getFileNoCriminalRecord(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileNoCriminalRecord("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		//
		if(null != employeeFilesSystemPersonal.getFileProofAddress() && !employeeFilesSystemPersonal.getFileProofAddress().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getProofAddress(), 
					employeeFilesSystemPersonal.getFileProofAddress(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileProofAddress("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		if(null != employeeFilesSystemPersonal.getFilePersonalReferences() && !employeeFilesSystemPersonal.getFilePersonalReferences().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getPersonalReferences(), 
					employeeFilesSystemPersonal.getFilePersonalReferences(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFilePersonalReferences("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		if(null != employeeFilesSystemPersonal.getFileProfessionalCurriculum() && !employeeFilesSystemPersonal.getFileProfessionalCurriculum().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getProfessionalCurriculum(), 
					employeeFilesSystemPersonal.getFileProfessionalCurriculum(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileProfessionalCurriculum("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		if(null != employeeFilesSystemPersonal.getFilePhoto() && !employeeFilesSystemPersonal.getFilePhoto().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getPhoto(), 
					employeeFilesSystemPersonal.getFilePhoto(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFilePhoto("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		if(null != employeeFilesSystemPersonal.getFileCertifications() && !employeeFilesSystemPersonal.getFileCertifications().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getCertifications(), 
					employeeFilesSystemPersonal.getFileCertifications(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileCertifications("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		if(null != employeeFilesSystemPersonal.getFileRecommendationLetter() && !employeeFilesSystemPersonal.getFileRecommendationLetter().isEmpty()) {
			String result = Utils.copyFileSystem(Utils.getMailName(user.getEmail()), 
					employeeFilesSystemPersonal.getRecommendationLetter(), 
					employeeFilesSystemPersonal.getFileRecommendationLetter(), 
					rootFileSystem, 
					fatherFolder + File.separator + employeeFilesSystemPersonal.getId());
			if(!result.equals("Ok")) {
				// Eliminamos nombre de archivo en BD
				employeeFilesSystemPersonal.setFileRecommendationLetter("");
				employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal);
				throw new Exception(result);
			}
		}
		LOGGER.info("## <-- UserServiceImpl.saveUserEmployeeFilesSystemPersonal() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeEmployeeFilesSystemPersonal(int, int)
	 */
	@Override
	public boolean activeEmployeeFilesSystemPersonal(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeEmployeeFilesSystemPersonal() ##");
		EmployeeFilesSystemPersonal employeeFilesSystemPersonal = employeeFilesSystemPersonalRepository.findById(id);
		if(null == employeeFilesSystemPersonal) {
			throw new Exception("Empleado inexistente");
		}
		employeeFilesSystemPersonal.setActive(active);
		LOGGER.info("## <-- UserServiceImpl.activeEmployeeFilesSystemPersonal() ##");
		return (null != employeeFilesSystemPersonalRepository.save(employeeFilesSystemPersonal));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeFilesSystemPersonalById(int)
	 */
	@Override
	public EmployeeFilesSystemPersonal findEmployeeFilesSystemPersonalById(int id) throws Exception {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findCatalogAreaAll()
	 */
	@Override
	public List<CatalogArea> findCatalogAreaAll() {
		return catalogAreaRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findCatalogAreaById(int)
	 */
	@Override
	public CatalogArea findCatalogAreaById(int id) {
		return catalogAreaRepository.findById(id);
	}
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findCatalogAreaById(int)
	 */
	@Override
	public RequestOfCourses findRequestOfCoursesById(int id) {
		return requestofcoursesrepository.findById(id);
	}
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findCatalogAreaByValue(java.lang.String)
	 */
	@Override
	public CatalogArea findCatalogAreaByValue(String value) {
		return catalogAreaRepository.findByValue(value);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveCatalogArea(com.kardex.model.CatalogArea)
	 */
	@Override
	public void saveCatalogArea(CatalogArea catalogArea) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCatalogArea() ##");
		catalogAreaRepository.save(catalogArea);
		LOGGER.info("## <-- UserServiceImpl.saveCatalogArea() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveRequestOfCourses(com.kardex.model.RequestOfCourses, int)
	 */
	@Override
	public void saveRequestOfCourses(RequestOfCourses requestOfCourses, int idUser) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveRequestOfCourses() ##");
		requestofcoursesrepository.save(requestOfCourses);
		// Mandar correo de confirmación de guardado
		User user = userRepository.findById(idUser);
		// -- Convertir Objeto a XML
		// ---- Modificar Datos de entrada
		requestOfCourses.setApproved(0); // 0 (MANDAR SOLICITUD - FALTA APROBACION), 1 (SI)
		requestOfCourses.setDateRequest("" + Utils.formatDate(new Date(), "yyyy-MM-dd"));
		requestOfCourses.setFullNameRequest(user.getName() + " " + user.getLastName()); // Nombre (Completo)
		requestOfCourses.setRfcRequest(user.getEmployee().getRfc()); // RFC
		if(null != user.getEmployee().getEmployeeDemographics()) {
			requestOfCourses.setRfcAddress(user.getEmployee().getEmployeeDemographics().getDelegationMunicipality()); // Domicilio
		}
		requestOfCourses.setRfcPhone(user.getEmployee().getPhone()); // Teléfono particular
		requestOfCourses.setRfcMobile(user.getEmployee().getCellPhone()); // Móvil
		requestOfCourses.setRfcMail(user.getEmail()); // Correo Electrónico
		// Rellenar datos vacios
		if(null == requestOfCourses.getCostWoTax() || requestOfCourses.getCostWoTax().equals("")) requestOfCourses.setCostWoTax("N/A");
		if(null == requestOfCourses.getCostWTax() || requestOfCourses.getCostWTax().equals("")) requestOfCourses.setCostWTax("N/A");
		if(null == requestOfCourses.getCompany1() || requestOfCourses.getCompany1().equals("")) requestOfCourses.setCompany1("N/A");
		if(null == requestOfCourses.getCompany2() || requestOfCourses.getCompany2().equals("")) requestOfCourses.setCompany2("N/A");
		if(null == requestOfCourses.getCompany3() || requestOfCourses.getCompany3().equals("")) requestOfCourses.setCompany3("N/A");
		if(null == requestOfCourses.getJustification1() || requestOfCourses.getJustification1().equals("")) requestOfCourses.setJustification1("N/A");
		if(null == requestOfCourses.getJustification2() || requestOfCourses.getJustification2().equals("")) requestOfCourses.setJustification2("N/A");
		if(null == requestOfCourses.getJustification3() || requestOfCourses.getJustification3().equals("")) requestOfCourses.setJustification3("N/A");
		//
		String fileWord = Utils.createWordRequestOfCourses(rootFileSystem + File.separator + "solicitud-cursos.docx", 
				rootFileSystem + Utils.getMailName(user.getEmail()), 
				Utils.jaxbObjectToXML(requestOfCourses), 
				requestOfCourses.getId());
		// Opciones de empresa TABLA
		String optionCompany = "<br>\n" + 
				"<br>\n" + 
				"<table style=\"font-family: arial, sans-serif;border-collapse: collapse;width: 100%;\">\n" + 
				"<tr class=\"tr-zebra\">\n" + 
				"	<th style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Empresa</th>\n" + 
				"	<th style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">Justificación</th>\n" + 
				"</tr>\n" + 
				"<tr class=\"tr-zebra\">\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getCompany1() + 
				"</td>\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getJustification1() + 
				"</td>\n" + 
				"</tr>\n" + 
				"<tr class=\"tr-zebra\">\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getCompany2() + 
				"</td>\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getJustification2() + 
				"</td>\n" + 
				"</tr>\n" + 
				"<tr class=\"tr-zebra\">\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getCompany3() + 
				"</td>\n" + 
				"	<td style=\"border: 1px solid #dddddd;text-align: left;padding: 8px;\">" + 
				requestOfCourses.getJustification3() + 
				"</td>\n" + 
				"</tr>\n" + 
				"</table>\n";
		String msg = mailTemplateRepository.findByNameAndActive("EMAIL-GRAL", 1).getBody()
				.replace("{username}", user.getName() + " " + user.getLastName()).replace("{message}",
						"</br><strong>Se le ha recibido una nueva solicitud de curso/capacitación<strong></br>"
								+ "</br>Nombre del Programa solicitado: " + requestOfCourses.getNameRequestProgram() 
								+ "</br>Duración (En horas): " + requestOfCourses.getDuration()
								+ "</br>Modalidad: " + requestOfCourses.getModality()
								+ "</br>Nombre del Proveedor (Razón Social): " + requestOfCourses.getNameProvider()
								+ "</br>RFC del Proveedor: " + requestOfCourses.getRfcProvider()
								+ "</br>Domicilio Fiscal: " + requestOfCourses.getTaxResidence()
								+ "</br>Nombre, teléfono y correo electrónico del contacto de la empresa capacitadora: " + requestOfCourses.getContactInfo()
								+ "</br>Fecha de Inicio: " + requestOfCourses.getStartDate()
								+ "</br>Fecha de Término: " + requestOfCourses.getEndDate()
								+ "</br>Lugar de Impartición: " + requestOfCourses.getPlaceCurse()
								+ "</br>Costo Total (Sin IVA): " + requestOfCourses.getCostWoTax()
								+ "</br>Costo Total (Con IVA): " + requestOfCourses.getCostWTax()
								+ "</br>Objetivos del Curso y/o examen de certificación: " + requestOfCourses.getObjetivesCourse()
								+ "</br>Objetivos del ingreso al curso y/o examen de certificación relacionadas a su Puesto: " + requestOfCourses.getObjetivesCourseRelatedJobPlace()
								+ "</br>Justificación Técnica: " + requestOfCourses.getTechnicalJustification()
								
								+ "</br><span style=\"color:red;font-weight:bold\">* Nota: este documento debe de ser entregado con sus respectivas firmas.</span></br>" 
								
								+ optionCompany);
		String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(),
				Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()),
				getParameterForName("EMAIL-USERNAME").getValue(), 
				getParameterForName("EMAIL-KEY").getValue(),
				getParameterForName("EMAIL-SETFROM").getValue(), 
				"Solicitud de curso: " + requestOfCourses.getNameRequestProgram(),
				"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
				msg, 
				getParameterForName("EMAIL-TO").getValue(),
				true, 
				fileWord, "Solicitud de curso " + user.getName() + ".docx", "Solicitud de curso " + user.getName() + ".docx");
		if (!"".equals(respEmail)) {
			LOGGER.info("Envio de correo incorrecto: {}", respEmail);
		}
		// Envia notificacion
		String emailTo = getParameterForName("EMAIL-TO").getValue();
		String []args = {
			emailTo + Constants.C_VERTICAL_BAR + "Nuevo curso solicitado de " + emailTo,
			getUserDetails("UserService.saveRequestOfCourses").get("USERNAME") + Constants.C_VERTICAL_BAR + "Solicitaste el curso \"" + requestOfCourses.getNameRequestProgram() + "\" enviada."
		};
		notificationTo(args);
		//
		LOGGER.info("## <-- UserServiceImpl.saveRequestOfCourses() ##");
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeCatalogArea(int, int)
	 */
	@Override
	public boolean activeCatalogArea(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeCatalogArea() ##");
		CatalogArea catalogArea = catalogAreaRepository.findById(id);
		// Activar / Desactivar Información de estudios
		catalogArea.setActive(active);
		// Borrar certificaciones
		if(!catalogArea.getCatalogWorkPlace().isEmpty()) {
			catalogWorkPlaceRepository.deleteInBatch(catalogArea.getCatalogWorkPlace());
		}
		LOGGER.info("## <-- UserServiceImpl.activeCatalogArea() ##");
		return (null != catalogAreaRepository.save(catalogArea));
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findCatalogWorkPlaceById(int)
	 */
	@Override
	public CatalogWorkPlace findCatalogWorkPlaceById(int id) {
		return catalogWorkPlaceRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveCatalogWorkPlace(com.kardex.model.CatalogWorkPlace)
	 */
	@Override
	public void saveCatalogWorkPlace(CatalogWorkPlace catalogWorkPlace) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCatalogWorkPlace() ##");
		catalogWorkPlaceRepository.save(catalogWorkPlace);
		LOGGER.info("## <-- UserServiceImpl.saveCatalogWorkPlace() ##");
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#activeCatalogWorkPlace(int, int, java.lang.String)
	 */
	@Override
	public boolean activeCatalogWorkPlace(int id, int active, String type) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeCatalogWorkPlace() ##");
		if(type.equals("WORK-PLACE")) {
			CatalogWorkPlace catalogWorkPlace = catalogWorkPlaceRepository.findById(id);
			if(null == catalogWorkPlace) {
				throw new Exception("Roles y Responsabilidades");
			}
			// Borrar
			catalogWorkPlaceRepository.delete(catalogWorkPlace);
		}
		LOGGER.info("## <-- UserServiceImpl.activeCatalogWorkPlace() ##");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#sendMailHelpPortal(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<Long, String> sendMailHelpPortal(String email, String data) {
		LOGGER.info("## --> UserServiceImpl.sendMailHelpPortal() ##");
		Map<Long, String> map = new HashMap<>();
		//
		User user = findUserByEmail(email);
		// Mandar correo de confirmación de guardado
		String msg = mailTemplateRepository.findByNameAndActive("EMAIL-CHAT", 1).getBody()
				.replace("{username}", "</br>" + user.getName().trim() + " " + user.getLastName().trim())
				.replace("{type}", "(" + email + ")")
				.replace("{message}", data.trim());
		String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(),
				Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()),
				getParameterForName("EMAIL-USERNAME").getValue(), 
				getParameterForName("EMAIL-KEY").getValue(),
				getParameterForName("EMAIL-SETFROM").getValue(), 
				"Ayuda Chat Bot BeITalent",
				"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
				msg, 
				getParameterForName("EMAIL-TO").getValue(),
				false, 
				"", "", "");
		if (!"".equals(respEmail)) {
			LOGGER.info("Envio de correo incorrecto: {}", respEmail);
			map.put(1L, "Nok");
			map.put(2L, respEmail);
		} else {
			map.put(1L, "Ok");
			// Envia notificacion
			String emailTo = getParameterForName("EMAIL-TO").getValue();
			String []args = {
				emailTo + Constants.C_VERTICAL_BAR + "El usuario</br>" + user.getEmail() + "</br>a enviado una notificación</br>por medio del \"ChatBot\"",
				user.getEmail() + Constants.C_VERTICAL_BAR + "Has enviado una notificación</br>por medio del \"ChatBot\""
			};
			notificationTo(args);
		}
		LOGGER.info("## <-- UserServiceImpl.sendMailHelpPortal() ##");
		return map;
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#sendMailHelpPortalLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<Long, String> sendMailHelpPortalLogin(String email, String data) {
		LOGGER.info("## --> UserServiceImpl.sendMailHelpPortalLogin() ##");
		Map<Long, String> map = new HashMap<>();
		// Mandar correo de confirmación de guardado
		String msg = mailTemplateRepository.findByNameAndActive("EMAIL-CHAT", 1).getBody()
				.replace("{username}", email.trim())
				.replace("{type}", "(login)")
				.replace("{message}", data.trim());
		String respEmail = Utils.sendEmail(getParameterForName("EMAIL-SETHOSTNAME").getValue(),
				Integer.parseInt(getParameterForName("EMAIL-SETSMTPPORT").getValue()),
				getParameterForName("EMAIL-USERNAME").getValue(), 
				getParameterForName("EMAIL-KEY").getValue(),
				getParameterForName("EMAIL-SETFROM").getValue(), 
				"Ayuda Chat Bot BeITalent",
				"1".equals(getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
				msg, 
				getParameterForName("EMAIL-TO").getValue(),
				false, 
				"", "", "");
		if (!"".equals(respEmail)) {
			LOGGER.info("Envio de correo incorrecto: {}", respEmail);
			map.put(1L, "Nok");
			map.put(2L, respEmail);
		} else {
			map.put(1L, "Ok");
			// Envia notificacion
			String emailTo = getParameterForName("EMAIL-TO").getValue();
			String []args = {
				emailTo + Constants.C_VERTICAL_BAR + "El usuario</br>" + email + "</br>a enviado una notificación</br>por medio del \"ChatBot\"",
				email + Constants.C_VERTICAL_BAR + "Has enviado una notificación</br>por medio del \"ChatBot\""
			};
			notificationTo(args);
		}
		LOGGER.info("## <-- UserServiceImpl.sendMailHelpPortalLogin() ##");
		return map;
	}

	@Override
	public List<CertificationTrack> findCertificationTrackAll() {
		return certificationTrackRepository.findAll();
	}

	@Override
	public CertificationTrack findCertificationTrackById(int id) {
		return certificationTrackRepository.findById(id);
	}

	@Override
	public CertificationTrack findCertificationTrackByValue(String value) {
		return null;
	}

	@Override
	public void saveCertificationTrack(CertificationTrack certificationTrack) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCertificationTrack() ##");
		certificationTrackRepository.save(certificationTrack);
		LOGGER.info("## <-- UserServiceImpl.saveCertificationTrack() ##");
	}

	@Override
	public boolean activeCertificationTrack(int id, int active) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeCertificationTrack() ##");
		boolean result = false;
		CertificationTrack certificationTrack = certificationTrackRepository.findById(id);
		// Borrar plan de carrera
		if(!certificationTrack.getCatalogFase().isEmpty()) {
			// Proceder Eliminar BLOQUES
			Iterator<CatalogFase> iterCatalogFase = certificationTrack.getCatalogFase().iterator();
			while(iterCatalogFase.hasNext()) {
				CatalogFase catalogFase = iterCatalogFase.next();
				// Borrar BLOQHES
				if(!catalogFase.getCatalogFaseBlock().isEmpty()) {
					// Proceder Eliminar TECNOLOGIAS
					Iterator<CatalogFaseBlock> iterCatalogFaseBlock = catalogFase.getCatalogFaseBlock().iterator();
					while(iterCatalogFaseBlock.hasNext()) {
						CatalogFaseBlock catalogFaseBlock = iterCatalogFaseBlock.next();
						// Borrar TECNOLOGIAS
						if(!catalogFaseBlock.getCatalogFaseBlockTechnology().isEmpty()) {
							catalogFaseBlockTechnologyRepository.deleteInBatch(catalogFaseBlock.getCatalogFaseBlockTechnology());
						}
					}
					catalogFaseBlockRepository.deleteInBatch(catalogFase.getCatalogFaseBlock());
				}
			}
			catalogFaseRepository.deleteInBatch(certificationTrack.getCatalogFase());
		}
		// Forzar eliminacion del padre
		certificationTrack.setCatalogArea(null);
		certificationTrack.setCatalogFase(new TreeSet<>());
		certificationTrackRepository.delete(certificationTrack);
		result = true;
		LOGGER.info("## <-- UserServiceImpl.activeCertificationTrack() ##");
		return result;
	}

	@Override
	public CatalogFase findCatalogFaseById(int id) {
		return catalogFaseRepository.findById(id);
	}

	@Override
	public void saveCatalogFase(CatalogFase catalogFase) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCatalogFase() ##");
		catalogFaseRepository.save(catalogFase);
		LOGGER.info("## <-- UserServiceImpl.saveCatalogFase() ##");
	}
	
	@Override
	public CatalogFaseBlock findCatalogFaseBlockById(int id) {
		return catalogFaseBlockRepository.findById(id);
	}

	@Override
	public void saveCatalogFaseBlock(CatalogFaseBlock catalogFaseBlock) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCatalogFaseBlock() ##");
		catalogFaseBlockRepository.save(catalogFaseBlock);
		LOGGER.info("## <-- UserServiceImpl.saveCatalogFaseBlock() ##");
	}
	
	@Override
	public CatalogFaseBlockTechnology findCatalogFaseBlockTechnologyById(int id) {
		return catalogFaseBlockTechnologyRepository.findById(id);
	}

	@Override
	public void saveCatalogFaseBlockTechnology(CatalogFaseBlockTechnology catalogFaseBlockTechnology) throws Exception {
		LOGGER.info("## --> UserServiceImpl.saveCatalogFaseBlockTechnology() ##");
		catalogFaseBlockTechnologyRepository.save(catalogFaseBlockTechnology);
		LOGGER.info("## <-- UserServiceImpl.saveCatalogFaseBlockTechnology() ##");
	}

	@Override
	public boolean activeCatalogFaseBlockTechnology(int id, int active, String type) throws Exception {
		LOGGER.info("## --> UserServiceImpl.activeCatalogFaseBlockTechnology() ##");
		// Validate TYPE
		boolean result = false;
		if(type.equals("CATALOG-FASE")) {
			CatalogFase catalogFase = catalogFaseRepository.findById(id);
			// Borrar BLOQHES
			if(!catalogFase.getCatalogFaseBlock().isEmpty()) {
				// Proceder Eliminar TECNOLOGIAS
				Iterator<CatalogFaseBlock> iterCatalogFaseBlock = catalogFase.getCatalogFaseBlock().iterator();
				while(iterCatalogFaseBlock.hasNext()) {
					CatalogFaseBlock catalogFaseBlock = iterCatalogFaseBlock.next();
					// Borrar TECNOLOGIAS
					if(!catalogFaseBlock.getCatalogFaseBlockTechnology().isEmpty()) {
						catalogFaseBlockTechnologyRepository.deleteInBatch(catalogFaseBlock.getCatalogFaseBlockTechnology());
					}
				}
				catalogFaseBlockRepository.deleteInBatch(catalogFase.getCatalogFaseBlock());
			}
			// Forzar eliminacion padre e hijo desde el objeto
			catalogFase.setCertificationTrack(null);
			catalogFase.setCatalogFaseBlock(new TreeSet<>());
			catalogFaseRepository.delete(catalogFase);
			result = true;
		}
		if(type.equals("CATALOG-FASE-BLOCK")) {
			CatalogFaseBlock catalogFaseBlock = catalogFaseBlockRepository.findById(id);
			// Borrar TECNOLOGIAS
			if(!catalogFaseBlock.getCatalogFaseBlockTechnology().isEmpty()) {
				catalogFaseBlockTechnologyRepository.deleteInBatch(catalogFaseBlock.getCatalogFaseBlockTechnology());
			}
			// Forzar eliminacion padre e hijo desde el objeto
			catalogFaseBlock.setCatalogFase(null);
			catalogFaseBlock.setCatalogFaseBlockTechnology(new TreeSet<>());
			catalogFaseBlockRepository.delete(catalogFaseBlock);
			result = true;
		}
		if(type.equals("CATALOG-FASE-BLOCK-TECHNOLOGY")) {
			CatalogFaseBlockTechnology catalogFaseBlockTechnology = catalogFaseBlockTechnologyRepository.findById(id);
			// Forzar eliminacion padre e hijo desde el objeto
			catalogFaseBlockTechnology.setCatalogFaseBlock(null);
			catalogFaseBlockTechnologyRepository.delete(catalogFaseBlockTechnology);
			result = true;
		}
		LOGGER.info("## <-- UserServiceImpl.activeCatalogFaseBlockTechnology() ##");
		return result;
	}

	@Override
	public List<EmployeeLabor> findEmployeeArea(String name){
		return employeeLaborRepository.findByArea(name);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeGralById(int)
	 */
	@Override
	public EmployeeGral findEmployeeGralById(int id) {
		return employeeGralRepository.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeNotificationAll()
	 */
	@Override
	public List<EmployeeNotification> findEmployeeNotificationAll() {
		return employeeNotificationRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#findEmployeeNotificationUserAll(com.kardex.model.User)
	 */
	@Override
	public List<EmployeeNotification> findEmployeeNotificationUserAll(User user) {
		return employeeNotificationRepository.findByUser(user);
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#deleteEmployeeNotification(java.lang.Integer)
	 */
	@Override
	public void deleteEmployeeNotification(Integer id) {
		employeeNotificationRepository.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#notificationTo(java.lang.String[])
	 */
	@Override
	public void notificationTo(String[] args) {
		for (int x = 0; x < args.length; x++) {
			try {
				String []split = args[x].split("\\" + Constants.C_VERTICAL_BAR + "");
				if(split.length == 2) {
					// Validar si son mas DESTINATARIOS
					String []splitEmail = split[0].split("\\;");
					if(splitEmail.length > 0) {
						for (int i = 0; i < splitEmail.length; i++) {
							// Guardar notificacion
							EmployeeNotification employeeNotification = new EmployeeNotification();
							employeeNotification.setId(0);
							employeeNotification.setUser(findUserByEmail(splitEmail[i]));
							employeeNotification.setValue(split[1]);
							employeeNotification.setActive(1);
							saveEmployeeNotification(employeeNotification);
						}
					} else {
						// Guardar notificacion
						EmployeeNotification employeeNotification = new EmployeeNotification();
						employeeNotification.setId(0);
						employeeNotification.setUser(findUserByEmail(split[0]));
						employeeNotification.setValue(split[1]);
						employeeNotification.setActive(1);
						saveEmployeeNotification(employeeNotification);
					}
				}
			} catch (Exception e) {
				LOGGER.info("Notification error: " + e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.kardex.service.UserService#saveEmployeeNotification(com.kardex.model.EmployeeNotification)
	 */
	@Override
	public void saveEmployeeNotification(EmployeeNotification employeeNotification) {
		employeeNotificationRepository.save(employeeNotification);
	}
}