package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.CatalogArea;
import com.kardex.model.CatalogWorkPlace;
import com.kardex.model.User;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller exclusivo para catalogos
 */
@RestController
@RequestMapping("adm/catalogs/")
public class CatalogController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "findUserAll", method = RequestMethod.POST)
	public List<Map<String, Object>> findUserAll() throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<User> listUser = userService.findUserAll();
			Iterator<User> iterUser = listUser.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				User user = iterUser.next();
				if(user.getActive() > 0) {
					//
					row.put("id", user.getId());
					row.put("name", user.getName());
					row.put("lastName", "" + user.getLastName());
					row.put("email", "" + user.getEmail());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ************************************* Controllers de Catalog Area *************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	@RequestMapping(value = "findCatalogArea", method = RequestMethod.POST)
	public List<Map<String, Object>> findCatalogArea() throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<CatalogArea> listCatalogArea = userService.findCatalogAreaAll();
			Iterator<CatalogArea> iterUser = listCatalogArea.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogArea catalogArea = iterUser.next();
				if(catalogArea.getActive() > 0) {
					//
					row.put("id", catalogArea.getId());
					row.put("name", catalogArea.getName());
					row.put("value", "" + catalogArea.getValue());
					row.put("description", "" + catalogArea.getDescription());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "findCatalogAreaByValue", method = RequestMethod.POST)
	public List<Map<String, Object>> findCatalogAreaByValue(@RequestBody Map<String, String> model) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CatalogArea catalogArea = userService.findCatalogAreaByValue(model.get("value"));
			if(catalogArea.getActive() > 0) {
				//
				Map<String, Object> row = new HashMap<>();
				row.put("id", catalogArea.getId());
				row.put("name", catalogArea.getName());
				row.put("value", "" + catalogArea.getValue());
				row.put("description", "" + catalogArea.getDescription());
				//
				responseData.add(row);
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "saveCatalogArea", method = RequestMethod.POST)
	public Map<Long, String> saveCatalogArea(@RequestBody CatalogArea catalogArea) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null == catalogArea) {
				throw new Exception("Catálogo vacio.");
			}
			if(catalogArea.getId() > 0) {
				// Catalogo nuevo
				CatalogArea catalogAreaExist = userService.findCatalogAreaById(catalogArea.getId());
				catalogArea.setCatalogWorkPlace(catalogAreaExist.getCatalogWorkPlace());
			}
			userService.saveCatalogArea(catalogArea);
			map.put(1L, "Ok");
			map.put(2L, "Catálogo guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar catálogo: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "enabledCatalogArea/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledCatalogArea(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeCatalogArea(id, active)) {
				throw new Exception("Información de estudios no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "findEmployeeCatalogWorkPlace/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeCatalogWorkPlace(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CatalogArea catalogArea = userService.findCatalogAreaById(id);
			Iterator<CatalogWorkPlace> iterCatalog = catalogArea.getCatalogWorkPlace().iterator();
			while(iterCatalog.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogWorkPlace catalogWorkPlace = iterCatalog.next();
				if(catalogArea.getActive() > 0) {
					//
					row.put("idCatalogWorkPlace", catalogWorkPlace.getId());
					row.put("name", catalogWorkPlace.getName());
					row.put("value", "" + catalogWorkPlace.getValue());
					row.put("description", "" + catalogWorkPlace.getDescription());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "findEmployeeCatalogWorkPlaceValue", method = RequestMethod.POST)
    public List<Map<String, Object>> findEmployeeCatalogWorkPlaceValue(@RequestBody Map<String, String> model) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			if(model.isEmpty()) {
				throw new Exception("Nombre de catálogo vacio.");
			}
			// Validar si la acción entra de "editEmployee"
			if(null != model.get("isValue") && !model.get("isValue").equals("")) {
				String msgCatalog = "Catalogo: " + model.get("value") + " -> " + model.get("isValue");
				LOGGER.info("findEmployeeCatalogWorkPlaceValue -> Contiene isValue: {}", msgCatalog);
				Map<String, Object> row = new HashMap<>();
				row.put("isValue", model.get("isValue"));
				responseData.add(row);
			}
			CatalogArea catalogArea = userService.findCatalogAreaByValue(model.get("value"));
			Iterator<CatalogWorkPlace> iterCatalog = catalogArea.getCatalogWorkPlace().iterator();
			while(iterCatalog.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogWorkPlace catalogWorkPlace = iterCatalog.next();
				if(catalogArea.getActive() > 0) {
					//
					row.put("idCatalogWorkPlace", catalogWorkPlace.getId());
					row.put("name", catalogWorkPlace.getName());
					row.put("value", "" + catalogWorkPlace.getValue());
					row.put("description", "" + catalogWorkPlace.getDescription());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "saveUserEmployeeCatalogWorkPlace/{type}", method = RequestMethod.POST)
	public Map<Long, String> saveUserEmployeeCatalogWorkPlace(@RequestBody CatalogArea catalogArea, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null == catalogArea) {
				throw new Exception("Catálogo vacio.");
			}
			if(type.equals("WORK-PLACE")) {
				if(catalogArea.getId() > 0 && null != catalogArea.getCatalogWorkPlace()) {
					CatalogWorkPlace catalogWorkPlace = catalogArea.getCatalogWorkPlace().iterator().next();
					// Catalogo nuevo
					CatalogArea catalogAreaExist = userService.findCatalogAreaById(catalogArea.getId());
					catalogWorkPlace.setCatalogArea(catalogAreaExist);
					userService.saveCatalogWorkPlace(catalogWorkPlace);
				}
			}
			map.put(1L, "Ok");
			map.put(2L, "Catálogo guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Empleado: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "enabledCatalogAreaWorkPlace/{id}/{active}/{type}", method = RequestMethod.POST)
	public Map<Long, String> enabledCatalogAreaWorkPlace(@PathVariable int id, @PathVariable int active, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeCatalogWorkPlace(id, active, type)) {
				throw new Exception("Información de Roles y Responsabilidades no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Empleado " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
}