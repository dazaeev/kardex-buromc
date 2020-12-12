package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.directory.SearchControls;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.User;
import com.kardex.service.UserService;
import com.kardex.utils.LdapUtils;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller exclusivo para LDAP
 */
@RestController
@RequestMapping("cat/ldap/")
public class LdapController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "findUser", method = RequestMethod.POST)
	public Map<String, String> findUser(@RequestBody Map<String, String> model) {
		Map<String, String> row = new TreeMap<>();
		try {
			if(null == model.get("name") || model.get("name").equals("")) {
				throw new Exception("Escribir cuenta.");
			}
			LOGGER.info("Buscar en Directorio Activo: " + model.get("name"));
			// Validar si exsite usuario en LDAP
			LdapContext ldapContext = LdapUtils.getLdapContext(
					userService.getParameterForName("LDAP-INITIAL-CONTEXT-FACTORY").getValue(),
					userService.getParameterForName("LDAP-SECURITY-AUTHENTICATION").getValue(),
					userService.getParameterForName("LDAP-SECURITY-PRINCIPAL").getValue(),
					userService.getParameterForName("LDAP-SECURITY-CREDENTIALS").getValue(),
					userService.getParameterForName("LDAP-PROVIDER-URL").getValue(),
					userService.getParameterForName("LDAP-REFERRAL").getValue());
			SearchControls searchControls = LdapUtils.getSearchControls();
			row = LdapUtils.userInfo(model.get("name"), userService.getParameterForName("LDAP_DC").getValue(), ldapContext, searchControls);
			row.put("error", "0");
		} catch (Exception e){
			row.put("error", "1");
			row.put("message", e.getMessage());
        }
		return row;
    }
	
	@RequestMapping(value = "loadUserAll", method = RequestMethod.POST)
	public List<Map<String, String>> loadUserAll() {
		List<Map<String, String>> listUser = new LinkedList<>();
		try {
			LOGGER.info("Buscar en Directorio Activo a todos los usuarios");
			// Validar si exsite usuario en LDAP
			LdapContext ldapContext = LdapUtils.getLdapContext(
					userService.getParameterForName("LDAP-INITIAL-CONTEXT-FACTORY").getValue(),
					userService.getParameterForName("LDAP-SECURITY-AUTHENTICATION").getValue(),
					userService.getParameterForName("LDAP-SECURITY-PRINCIPAL").getValue(),
					userService.getParameterForName("LDAP-SECURITY-CREDENTIALS").getValue(),
					userService.getParameterForName("LDAP-PROVIDER-URL").getValue(),
					userService.getParameterForName("LDAP-REFERRAL").getValue());
			SearchControls searchControls = LdapUtils.getSearchControls();
			listUser = LdapUtils.userInfoAll(userService.getParameterForName("LDAP_DC").getValue(), ldapContext, searchControls);
		} catch (Exception e){
			LOGGER.info("Error loadUserAll: " + e.getMessage());;
        }
		return listUser;
    }
	
	@RequestMapping(value = "processingUser", method = RequestMethod.POST)
	public Map<Long, String> processingUser(@RequestBody Map<String, String> model) {
		Map<Long, String> map = new HashMap<>();
		User userExists = userService.findUserByEmail(model.get("email"));
		if (userExists != null) {
			try {
				// Valida si pwd esta vacio
				boolean active = false;
				if("".equals(userExists.getPassword())) {
					// mandar a activar
					active = true;
				}
				userExists.setPassword(model.get("keyAccount"));
				userService.saveUserForLdap(userExists);
				map.put(1L, "Ok");
				map.put(2L, "¡Usuario modificado con exito!");
				map.put(3L, active ? "Ok" : "Nok");
			} catch (Exception e) {
				map.put(1L, "Nok");
				map.put(2L, "Error al guardar Empleado: " + e.getMessage());
			}
		} else {
			map.put(1L, "Nok");
			map.put(2L, "Error: ¡Usuario inexistente!");
		}
		return map;
	}
	
	@RequestMapping(value = "validateUserCredentials", method = RequestMethod.POST)
	public Map<Long, String> validateUserCredentials(@RequestBody Map<String, String> model) {
		Map<Long, String> map = new HashMap<>();
		if(model.isEmpty()) {
			map.put(1L, "Nok");
			map.put(2L, "Error: Parametros incorrectos.");
		}
		try {
			String validateUserLdap = LdapUtils.validateUserCredentials(userService.getParameterForName("LDAP-INITIAL-CONTEXT-FACTORY").getValue(),
					userService.getParameterForName("LDAP-SECURITY-AUTHENTICATION").getValue(),
					userService.getParameterForName("LDAP-SECURITY-PRINCIPAL").getValue(),
					userService.getParameterForName("LDAP-SECURITY-CREDENTIALS").getValue(),
					userService.getParameterForName("LDAP-PROVIDER-URL").getValue(), 
					userService.getParameterForName("LDAP_SAMA").getValue(), 
					model.get("account"), 
					model.get("keyAccount"), 
					userService.getParameterForName("LDAP_DC").getValue());
			if(validateUserLdap.equals("Ok")) {
				map.put(1L, validateUserLdap);
				map.put(2L, "Usuario validado.");
				if(model.get("active").equals("Ok")) {
					// Mandar correo de activacion
					userService.sendEmailActive(userService.findUserByEmail(model.get("email")));
					map.put(3L, "record");
				}
			} else {
				map.put(1L, "Nok");
				map.put(2L, validateUserLdap);
				if(model.get("active").equals("Ok")) {
					// Dejar activacion
					userService.saveUserForActive(userService.findUserByEmail(model.get("email")));
				} 
			}
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error en AD: " + e.getMessage());
		}
		return map;
	}
}