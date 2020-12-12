package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.Role;
import com.kardex.model.User;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@RestController
@RequestMapping("adm/registration/")
public class RegistrationController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map<Long, String> add(@RequestBody User user) {
		Map<Long, String> map = new HashMap<>();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists == null) {
			try {
				// -- Se quita validacion de contraseña --
				userService.saveUser(user);
				map.put(1L, "Ok");
				map.put(2L, "¡Usuario creado con exito!");
			} catch (Exception e) {
				map.put(1L, "Nok");
				map.put(2L, "Error al guardar Empleado: " + e.getMessage());
			}
		} else {
			map.put(1L, "Nok");
			map.put(2L, "Error: ¡Usuario existente!");
		}
		return map;
	}
	
	@RequestMapping(value = "loadRol", method = RequestMethod.POST)
	public List<Role> loadRol() throws ServletException {
		try {
			List<Role> roles = userService.findRoleAll();
			if (!roles.isEmpty()) {
				return roles;
			} else {
				throw new ServletException("Roles vacios");
			}
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
	}
}
