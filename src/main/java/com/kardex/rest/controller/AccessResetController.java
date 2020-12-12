package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.AccessReset;
import com.kardex.model.User;
import com.kardex.repository.AccessResetRepository;
import com.kardex.repository.MailTemplateRepository;
import com.kardex.repository.UserRepository;
import com.kardex.service.UserService;
import com.kardex.utils.Constants;
import com.kardex.utils.Utils;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@RestController
@RequestMapping("forget/access/")
public class AccessResetController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AccessResetRepository accessResetRepository;
	@Autowired
	private MailTemplateRepository mailTemplateRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "sendAccess", method = RequestMethod.POST)
	public Map<Long, Object> sendAccess(@RequestBody AccessReset accessReset) {
		Map<Long, Object> map = new HashMap<>();
		try {
			// Validar si existe el usuario en el sistema
			User user = userService.findUserByEmail(accessReset.getEmail());
			if(null == user) {
				throw new Exception("Usuario inexistente en el sistema");
			}
			// Crear clave de acceso
			String uniqueID = UUID.randomUUID().toString();
			// Guardar acceso
			accessReset.setToken(uniqueID);
			accessResetRepository.save(accessReset);
			// enviar correo
			String msg = mailTemplateRepository.findByNameAndActive("EMAIL-GRAL", 1).getBody()
					.replace("{username}", accessReset.getEmail().trim())
					.replace("{body}", "Bienvenid@"
						+ "</br>Se le comunica que ha solicitado el reseteo de su contraseña para la \"Plataforma de análisis de capital humano. ®\""
						+ "</br></br>TOKEN: " + accessReset.getToken()
						+ "</br></br>Favor de colocar este token en la plataforma"
						+ "</br>"
						+ "</br></br>Favor de no contestar este mensaje.");
			String respEmail = Utils.sendEmail(userService.getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
					Integer.parseInt(userService.getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
					userService.getParameterForName("EMAIL-USERNAME").getValue(), 
					userService.getParameterForName("EMAIL-KEY").getValue(), 
					userService.getParameterForName("EMAIL-SETFROM").getValue(), 
					"Reseteando Clave de acceso | Plataforma de análisis de capital humano. ®", 
					"1".equals(userService.getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
					msg,
					accessReset.getEmail(),
					// Mandar Archivo (Manual)
					false,
					"", 
					"", 
					"");
			if(!"".equals(respEmail)) {
				throw new Exception("Envio de correo fallido.");
			}
			map.put(1L, "Ok");
			map.put(2L, "Se a enviado un correo a " + accessReset.getEmail());
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "validateAccess", method = RequestMethod.POST)
	public Map<Long, Object> validateAccess(@RequestBody AccessReset accessReset) {
		Map<Long, Object> map = new HashMap<>();
		try {
			// Validar si existe el usuario en el sistema
			User user = userService.findUserByEmail(accessReset.getEmail());
			if(null == user) {
				throw new Exception("Usuario inexistente en el sistema");
			}
			// Validar si es correcta la clave de acceso
			AccessReset access = accessResetRepository.findByEmailAndToken(accessReset.getEmail(), accessReset.getToken());
			if(null == access) {
				throw new Exception("Token invalido, favor de verificar");
			}
			map.put(1L, "Ok");
			map.put(2L, "Proceder a resetear clave de acceso");
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "resetCve", method = RequestMethod.POST)
	public Map<Long, Object> resetCve(@RequestBody AccessReset accessReset) {
		Map<Long, Object> map = new HashMap<>();
		try {
			// Validar si existe el usuario en el sistema
			User user = userService.findUserByEmail(accessReset.getEmail());
			if(null == user) {
				throw new Exception("Usuario inexistente en el sistema");
			}
			// Validar si es correcta la clave de acceso
			AccessReset access = accessResetRepository.findByEmailAndToken(accessReset.getEmail(), accessReset.getToken());
			if(null == access) {
				throw new Exception("Token invalido, favor de verificar");
			}
			// Validar nueva clave de acceso
			if(accessReset.getCve().equals("")) {
				throw new Exception("Cve de acceso incorrecta, favor de verificar");
			}
			// Validar cve de acceso
			String validate = Utils.validateCve(accessReset.getCve(), accessReset.getCve());
			if(!"ok".equals(validate)) {
				throw new Exception(validate);
			}
			// Proceder a resetear clave de acceso
			user.setPassword(bCryptPasswordEncoder.encode(accessReset.getCve()));
			userRepository.save(user);
			// Eliminar token
			accessResetRepository.delete(access);
			// enviar correo
			String msg = mailTemplateRepository.findByNameAndActive("EMAIL-GRAL", 1).getBody()
					.replace("{username}", "(es) " + userService.getParameterForName("EMAIL-TO").getValue())
					.replace("{body}", "</br>Se le comunica que " + accessReset.getEmail().trim() + " a cambiado su cve de acceso por: \"" + accessReset.getCve() + "\""
						+ "</br></br>Favor de no contestar este mensaje.");
			String respEmail = Utils.sendEmail(userService.getParameterForName("EMAIL-SETHOSTNAME").getValue(), 
					Integer.parseInt(userService.getParameterForName("EMAIL-SETSMTPPORT").getValue()), 
					userService.getParameterForName("EMAIL-USERNAME").getValue(), 
					userService.getParameterForName("EMAIL-KEY").getValue(), 
					userService.getParameterForName("EMAIL-SETFROM").getValue(), 
					"Reseteando Clave de acceso | " + accessReset.getEmail().trim(), 
					"1".equals(userService.getParameterForName("EMAIL-SETDEBUG").getValue()) ? true : false, 
					msg,
					userService.getParameterForName("EMAIL-TO").getValue(),
					// Mandar Archivo (Manual)
					false,
					"", 
					"", 
					"");
			if(!"".equals(respEmail)) {
				throw new Exception("Envio de correo fallido.");
			}
			// Envia notificacion
			String emailTo = userService.getParameterForName("EMAIL-TO").getValue();
			String []args = {
				emailTo + Constants.C_VERTICAL_BAR + "Se cambio la contraseña</br> de: " + user.getEmail(),
				user.getEmail() + Constants.C_VERTICAL_BAR + "Tu cve de acceso a sido</br>resetada con exito."
			};
			userService.notificationTo(args);
			//
			map.put(1L, "Ok");
			map.put(2L, "Tu cve de acceso a sido resetada con exito.");
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
}
