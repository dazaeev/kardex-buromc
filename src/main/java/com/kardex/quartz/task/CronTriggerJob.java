package com.kardex.quartz.task;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kardex.model.CloseCertifications;
import com.kardex.model.EmployeeStudies;
import com.kardex.model.OpenCertifications;
import com.kardex.model.User;
import com.kardex.repository.CloseCertificationsRepository;
import com.kardex.repository.MailTemplateRepository;
import com.kardex.repository.OpenCertificationsRepository;
import com.kardex.service.UserService;
import com.kardex.utils.Constants;
import com.kardex.utils.Utils;

public class CronTriggerJob implements Job {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CronTriggerJob.class);
	
	// Service
	@Autowired
	private UserService userService;
	// Repository
	@Autowired
	private MailTemplateRepository mailTemplateRepository;
	@Autowired
	private OpenCertificationsRepository openCertificationsRepository;
	@Autowired
	private CloseCertificationsRepository closeCertificationsRepository;
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		LOGGER.info("Ejecutando Jobs de Produccion: {}", new Date());
		validateActiveJobs();
	}
	
	/**
	 * Ejecucion de JOBS activos
	 */
	public void validateActiveJobs() {
		// Buscar JOB´s activos
		if(userService.getParameterForName("JOB-CLOSE-CERTIFICATION").getActive() == 1) {
			LOGGER.info("-> Certificaciones prontas a vencer: {}", taskCloseCertifications());
		}
		if(userService.getParameterForName("JOB-CLOSE-CERTIFICATION-EXPIRATION").getActive() == 1) {
			LOGGER.info("-> Certificaciones vencidas: {}", taskCloseCertificationsExpiration());
		}
	}
	
	/**
	 * @return
	 */
	public String taskCloseCertifications() {
		String resp = "";
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				User user = iterUser.next();
				if(null != user.getEmployee() && null != user.getEmployee().getEmployeeStudies() && user.getEmployee().getEmployeeStudies().getActive() > 0) {
					EmployeeStudies employeeStudies = user.getEmployee().getEmployeeStudies();
					if(null != employeeStudies.getOpenCertifications()) {
						Iterator<OpenCertifications> iter = employeeStudies.getOpenCertifications().iterator();
						while(iter.hasNext()) {
							OpenCertifications openCertifications = iter.next();
							if(openCertifications.getActive() > 0 && openCertifications.getSendingExpiredMail() > 0) {
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
											// Envio de correo
											String msg = mailTemplateRepository.findByNameAndActive("EMAIL-JOB-CLOSE-CERTIFICATION", 1).getBody()
													.replace("{username}", user.getName())
													.replace("{body}", "Se le comunica que su certificación \"" + openCertifications.getCertification() 
														+ "\" esta pronta a vencer."
														+ "</br>Con fecha de vencimiento: " + openCertifications.getDateExpiration()
														+ "</br></br>Favor de no contestar este mensaje.");
											String respEmail = Utils.sendEmail(userService.getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
													Integer.parseInt(userService.getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
													userService.getParameterForName("EMAIL-USERNAME").getValue(), 
													userService.getParameterForName("EMAIL-KEY").getValue(), 
													userService.getParameterForName("EMAIL-SETFROM").getValue(), 
													"Certificación pronta a vencer", 
													true, 
													msg,
													user.getEmail(),
													false,
													"", "", "");
											// Procesar a cambiar status de envio de correo
											openCertifications.setSendingExpiredMail(0);
											openCertificationsRepository.save(openCertifications);
											//
											if(!"".equals(respEmail)) {
												resp = "Envio de correo incorrecto: " + respEmail;
											}
											// Envia notificacion
											String emailTo = userService.getParameterForName("EMAIL-TO").getValue();
											String []args = {
												emailTo + Constants.C_VERTICAL_BAR + "Certificación </br>\"" + openCertifications.getCertification() + "\"</br>pronta a vencer de:</br>" + user.getEmail(),
												user.getEmail() + Constants.C_VERTICAL_BAR + "Certificación </br>\"" + openCertifications.getCertification() + "\"</br>esta pronta a vencer."
											};
											userService.notificationTo(args);
											//
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			resp = e.getMessage();
		}
		return resp;
	}
	
	public String taskCloseCertificationsExpiration() {
		String resp = "";
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				User user = iterUser.next();
				if(null != user.getEmployee() && null != user.getEmployee().getEmployeeStudies() && user.getEmployee().getEmployeeStudies().getActive() > 0) {
					EmployeeStudies employeeStudies = user.getEmployee().getEmployeeStudies();
					if(null != employeeStudies.getOpenCertifications()) {
						Iterator<OpenCertifications> iter = employeeStudies.getOpenCertifications().iterator();
						while(iter.hasNext()) {
							OpenCertifications openCertifications = iter.next();
							if(openCertifications.getActive() > 0) {
								// Validar fecha de expiracion
								Date dateCertificate 	= Utils.formatDate("" + openCertifications.getDateExpiration(), "yyyy-MM-dd");
								Date dateCurrent		= new Date();
								String log = user.getName() + " " + dateCertificate;
								if(dateCertificate.compareTo(dateCurrent) <= 0) {
									LOGGER.info("Ya vencio: {}", log);
									// Envio de correo
									String msg = mailTemplateRepository.findByNameAndActive("EMAIL-JOB-CLOSE-CERTIFICATION", 1).getBody()
											.replace("{username}", user.getName())
											.replace("{body}", "Se le comunica que su certificación \"" + openCertifications.getCertification() 
												+ "\" a vencido."
												+ "</br>Con fecha de vencimiento: " + openCertifications.getDateExpiration()
												+ "</br></br>Favor de no contestar este mensaje.");
									String respEmail = Utils.sendEmail(userService.getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
											Integer.parseInt(userService.getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
											userService.getParameterForName("EMAIL-USERNAME").getValue(), 
											userService.getParameterForName("EMAIL-KEY").getValue(), 
											userService.getParameterForName("EMAIL-SETFROM").getValue(), 
											"Certificación vencida", 
											true, 
											msg,
											user.getEmail(),
											false,
											"", "", "");
									// Procesar a mover registro a "close_certifications" - Certificaciones Vencidas
									CloseCertifications closeCertifications = new CloseCertifications();
									closeCertifications.setId(0);
									closeCertifications.setDescription(openCertifications.getDescription());
									closeCertifications.setCertification(openCertifications.getCertification());
									closeCertifications.setNameCertification(openCertifications.getNameCertification());
									closeCertifications.setDateExpiration(openCertifications.getDateExpiration());
									closeCertifications.setActive(openCertifications.getActive());
									closeCertifications.setEmployeeStudies(openCertifications.getEmployeeStudies());
									closeCertificationsRepository.save(closeCertifications);
									// Eliminar registro de "open_certifications" - Certificaciones Vigentes
									openCertificationsRepository.delete(openCertifications);
									//
									if(!"".equals(respEmail)) {
										resp = "Envio de correo incorrecto: " + respEmail;
									}
									// Envia notificacion
									String emailTo = userService.getParameterForName("EMAIL-TO").getValue();
									String []args = {
										emailTo + Constants.C_VERTICAL_BAR + "Certificación </br>\"" + openCertifications.getCertification() + "\"</br>vencida de:</br>" + user.getEmail(),
										user.getEmail() + Constants.C_VERTICAL_BAR + "Certificación </br>\"" + openCertifications.getCertification() + "\"</br>esta vencida."
									};
									userService.notificationTo(args);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			resp = e.getMessage();
		}
		return resp;
	}
}
