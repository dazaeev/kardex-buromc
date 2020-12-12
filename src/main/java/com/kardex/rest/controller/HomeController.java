package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.EmployeeMessage;
import com.kardex.model.EmployeeNotification;
import com.kardex.model.Menu;
import com.kardex.model.User;
import com.kardex.repository.EmployeeMessageRepository;
import com.kardex.repository.MenuRepository;
import com.kardex.service.UserService;
import com.kardex.utils.Utils;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@RestController
@RequestMapping("home/create/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private EmployeeMessageRepository employeeMessageRepository;

	/**
	 * Creacion de Home USERS y ADMIN
	 * @param columnLarge Largo de la columna
	 * @return
	 */
	@RequestMapping(value = "createHomeUsers/{columnLarge}", method = RequestMethod.POST)
	public Map<Long, String> createHomeUsers(@PathVariable String columnLarge) {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.createHomeUsers");
		Map<Long, String> map = new HashMap<>();
		try {
			map.put(1L, "Ok");
			map.put(2L, Utils.createHomeUsers(menuRepository.findMenuRoleById(mapUserDetails.get("USERNAME")), columnLarge));
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "findPhoto", method = RequestMethod.POST)
	public Map<Long, String> findPhoto() {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.findPhoto");
		Map<Long, String> map = new HashMap<>();
		try {
			User user = userService.findUserByEmail(mapUserDetails.get("USERNAME"));
			String html = "";
			if(null != user.getEmployee()) {
				html = Utils.createPhoto(user.getEmployee().getSex());
			} else {
				html = Utils.createPhoto("");
			}
			map.put(1L, "Ok");
			map.put(2L, html);
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "helpPortal", method = RequestMethod.POST)
	public Map<Long, String> helpPortal(@RequestBody Map<String, String> data) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null != data.get("email") && !"".equals(data.get("email"))) {
				if(data.get("email").equals("Nok")) {
					map.put(1L, "Nok");
					map.put(2L, "Email incorrecto");
				} else {
					map = userService.sendMailHelpPortalLogin(data.get("email"), data.get("msg"));
					map.put(1L, "Ok");
				}
			} else {
				map = userService.sendMailHelpPortal(userService.getUserDetails("HomeController.helpPortal").get("USERNAME"), data.get("msg"));
				map.put(1L, "Ok");
			}
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "searchPortal", method = RequestMethod.POST)
	public Map<Long, Object> searchPortal(@RequestBody Map<String, String> data) {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.searchPortal");
		Map<Long, Object> map = new HashMap<>();
		try {
			if("".equals(data.get("search"))) {
				throw new Exception("Datos vacios para buscar");
			}
			List<Menu> menu = menuRepository.findMenuSearch(mapUserDetails.get("USERNAME"), "%" + data.get("search") + "%");
			if(menu.isEmpty()) {
				throw new Exception("No se encontro ningun dato.");
			}
			map.put(1L, "Ok");
			map.put(2L, menu);
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		map.put(3L, mapUserDetails.get("ROLE"));
		return map;
	}
	
	@RequestMapping(value = "findNotifications", method = RequestMethod.POST)
	public Map<Long, Object> findNotifications() {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.findNotifications");
		Map<Long, Object> map = new HashMap<>();
		try {
			Set<EmployeeNotification> employeeNotifications = userService.findUserByEmail(mapUserDetails.get("USERNAME")).getEmployeeNotification();
			String divNotifications = Utils.createNotification(employeeNotifications.iterator());
			map.put(1L, "Ok");
			map.put(2L, divNotifications);
			map.put(3L, employeeNotifications.size());
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "callNotification/{id}", method = RequestMethod.POST)
	public Map<Long, Object> callNotification(@PathVariable Integer id) {
		Map<Long, Object> map = new HashMap<>();
		try {
			userService.deleteEmployeeNotification(id);
			map.put(1L, "Ok");
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "validateMessage", method = RequestMethod.POST)
	public Map<Long, Object> validateMessage(@RequestBody Map<String, String> data) {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.validateMessage");
		Map<Long, Object> map = new HashMap<>();
		try {
			if("".equals(data.get("message")) || "".equals(data.get("check"))) {
				throw new Exception("Atributo vacio de: validateMessage");
			}
			// Valida si existe mensaje en el USER
			User user = userService.findUserByEmail(mapUserDetails.get("USERNAME"));
			EmployeeMessage employeeMessage = employeeMessageRepository.findByUserAndName(user, data.get("message"));
			if(null == employeeMessage) {
				// Guardar tipo de mensaje
				EmployeeMessage employeeMessageNotFound =  new EmployeeMessage();
				employeeMessageNotFound.setName(data.get("message"));
				employeeMessageNotFound.setActive(data.get("check").equals("true") ? 0 : 1); // 0 = true, 1 = false
				employeeMessageNotFound.setUser(user);
				employeeMessageRepository.save(employeeMessageNotFound);
			} else {
				// Existe mensaje por lo tal se cambia el status (active)
				employeeMessage.setActive(data.get("check").equals("true") ? 0 : 1); // 0 = true, 1 = false
				employeeMessageRepository.save(employeeMessage);
			}
			map.put(1L, "Ok");
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "viewMessage", method = RequestMethod.POST)
	public Map<Long, Object> viewMessage(@RequestBody Map<String, String> data) {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.viewMessage");
		Map<Long, Object> map = new HashMap<>();
		try {
			if("".equals(data.get("message"))) {
				throw new Exception("Atributo vacio de: validateMessage");
			}
			// Valida si existe mensaje en el USER
			User user = userService.findUserByEmail(mapUserDetails.get("USERNAME"));
			EmployeeMessage employeeMessage = employeeMessageRepository.findByUserAndName(user, data.get("message"));
			if(null == employeeMessage) {
				// Guardar tipo de mensaje
				EmployeeMessage employeeMessageNotFound =  new EmployeeMessage();
				employeeMessageNotFound.setName(data.get("message"));
				employeeMessageNotFound.setActive(1); // 1 = true, 0 = false
				employeeMessageNotFound.setUser(user);
				employeeMessageRepository.save(employeeMessageNotFound);
				//
				map.put(1L, "Ok");
				map.put(2L, "1");
			} else {
				map.put(1L, "Ok");
				map.put(2L, employeeMessage.getActive());
			}
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
	
	@RequestMapping(value = "callDynamicSearch", method = RequestMethod.POST)
	public Map<Long, Object> callDynamicSearch(@RequestBody Map<String, String> data) {
		Map<String, String> mapUserDetails = userService.getUserDetails("HomeController.callDynamicSearch");
		Map<Long, Object> map = new HashMap<>();
		try {
			if("".equals(data.get("search"))) {
				throw new Exception("Datos vacios para buscar");
			} else if(!mapUserDetails.get("ROLE").equals("ADMIN")) {
				throw new Exception("No tienes el permiso para realizar esta busqueda.");
			}
			// Create dir
			String account = Utils.getMailName(mapUserDetails.get("USERNAME"));
			String path = userService.getBdRootFileSystem() + account;
			// Crear folder
			Utils.createFolder(path, true);
			List<Map<String, String>> listDynamicSearch = Utils.createDynamicSearch(menuRepository.getTable("test", data.get("search"), path + "/"));
			// Eliminar folder
			Utils.deleteFolder(path);
			if(listDynamicSearch.isEmpty()) {
				throw new Exception("No se encontro ningun dato.");
			}
			map.put(1L, "Ok");
			map.put(2L, listDynamicSearch);
			//
		} catch (Exception e) {
            map.put(1L, "Nok");
			map.put(2L, e.getMessage());
        }
		return map;
	}
}