package com.kardex.rest.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kardex.model.CatalogArea;
import com.kardex.model.CatalogFase;
import com.kardex.model.CatalogFaseBlock;
import com.kardex.model.CatalogFaseBlockTechnology;
import com.kardex.model.CertificationTrack;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller Plan de Carrera
 */
@RestController
@RequestMapping("adm/certification-track/")
public class CertificationTrackController {
	
	@Autowired
	private UserService userService;
	
	/*
	 *******************************************************************************************************
	 *******************************************************************************************************
	 ************************************ Controllers de Plan de Carrea ************************************
	 *******************************************************************************************************
	 *******************************************************************************************************
	 */
	
	@RequestMapping(value = "findCertificationTrack", method = RequestMethod.POST)
	public List<Map<String, Object>> findCertificationTrack() throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			List<CertificationTrack> listfindCertificationTrack = userService.findCertificationTrackAll();
			Iterator<CertificationTrack> iterUser = listfindCertificationTrack.iterator();
			while(iterUser.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CertificationTrack certificationTrack = iterUser.next();
				if(certificationTrack.getActive() > 0) {
					//
					row.put("id", certificationTrack.getId());
					row.put("name", certificationTrack.getName());
					row.put("area", "" + certificationTrack.getArea());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "saveCertificationTrack", method = RequestMethod.POST)
	public Map<Long, String> saveCertificationTrack(@RequestBody CertificationTrack certificationTrack) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null == certificationTrack) {
				throw new Exception("Plan de Carrera vacio.");
			}
			if(certificationTrack.getId() > 0) {
				// Catalogo nuevo
				CertificationTrack certificationTrackExist = userService.findCertificationTrackById(certificationTrack.getId());
				certificationTrack.setCatalogFase(certificationTrackExist.getCatalogFase());
			}
			// Setaar valor de "catalog_area_id"
			CatalogArea catalogArea = userService.findCatalogAreaByValue(certificationTrack.getArea());
			certificationTrack.setCatalogArea(catalogArea);
			// 
			userService.saveCertificationTrack(certificationTrack);
			map.put(1L, "Ok");
			map.put(2L, "Plan de carrera guardado con exito");
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error al guardar Plan de carrera: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "enabledCertificationTrack/{id}/{active}", method = RequestMethod.POST)
	public Map<Long, String> enabledCertificationTrack(@PathVariable int id, @PathVariable int active) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeCertificationTrack(id, active)) {
				throw new Exception("Información de estudios no modificado");
			}
			map.put(1L, "Ok");
			map.put(2L, "Plan de carrera " + (active > 0 ? "alterado" : "eliminado") + " con exíto");
			map.put(3L, "" + id);
		} catch (Exception e) {
			map.put(1L, "Nok");
			map.put(2L, "Error: " + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "findCertificationCatalogFase/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findCertificationCatalogFase(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CertificationTrack certificationTrack = userService.findCertificationTrackById(id);
			Iterator<CatalogFase> iterCatalog = certificationTrack.getCatalogFase().iterator();
			while(iterCatalog.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogFase catalogFase = iterCatalog.next();
				if(certificationTrack.getActive() > 0) {
					//
					row.put("idCatalogFase", catalogFase.getId());
					row.put("name", catalogFase.getName());
					row.put("description", "" + catalogFase.getDescription());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "findCertificationCatalogFaseBlock/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findCertificationCatalogFaseBlock(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CatalogFase catalogFase = userService.findCatalogFaseById(id);
			Iterator<CatalogFaseBlock> iterCatalog = catalogFase.getCatalogFaseBlock().iterator();
			while(iterCatalog.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogFaseBlock catalogFaseBlock = iterCatalog.next();
				if(catalogFaseBlock.getActive() > 0) {
					//
					row.put("idCatalogFaseBlock", catalogFaseBlock.getId());
					row.put("name", catalogFaseBlock.getName());
					row.put("description", "" + catalogFaseBlock.getDescription());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "findCertificationCatalogFaseBlockTechnology/{id}", method = RequestMethod.POST)
    public List<Map<String, Object>> findCertificationCatalogFaseBlockTechnology(@PathVariable int id) throws ServletException {
		List<Map<String, Object>> responseData = new LinkedList<>();
		try {
			CatalogFaseBlock catalogFaseBlock = userService.findCatalogFaseBlockById(id);
			Iterator<CatalogFaseBlockTechnology> iterCatalog = catalogFaseBlock.getCatalogFaseBlockTechnology().iterator();
			while(iterCatalog.hasNext()) {
				Map<String, Object> row = new HashMap<>();
				CatalogFaseBlockTechnology catalogFaseBlockTechnology = iterCatalog.next();
				if(catalogFaseBlockTechnology.getActive() > 0) {
					//
					row.put("idCatalogFaseBlockTechnology", catalogFaseBlockTechnology.getId());
					row.put("technology", catalogFaseBlockTechnology.getTechnology());
					row.put("product", "" + catalogFaseBlockTechnology.getProduct());
					//
					responseData.add(row);
				}
			}
			return responseData;
		} catch (Exception e){
            throw new ServletException(e.getMessage());
        }
    }
	
	@RequestMapping(value = "saveCertificationCatalogFase/{type}", method = RequestMethod.POST)
	public Map<Long, String> saveCertificationCatalogFase(@RequestBody CertificationTrack certificationTrack, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(null == certificationTrack) {
				throw new Exception("Catálogo vacio.");
			}
			if(type.equals("CATALOG-FASE")) {
				if(certificationTrack.getId() > 0 && null != certificationTrack.getCatalogFase()) {
					CatalogFase catalogFase = certificationTrack.getCatalogFase().iterator().next();
					// Catalogo nuevo
					CertificationTrack certificationTrackExist = userService.findCertificationTrackById(certificationTrack.getId());
					catalogFase.setCertificationTrack(certificationTrackExist);
					userService.saveCatalogFase(catalogFase);
				}
			}
			if(type.equals("CATALOG-FASE-BLOCK")) {
				if(certificationTrack.getId() > 0 && null != certificationTrack.getCatalogFase() && certificationTrack.getCatalogFase().size() > 0) {
					CatalogFase catalogFase = certificationTrack.getCatalogFase().iterator().next();
					CatalogFaseBlock catalogFaseBlock = catalogFase.getCatalogFaseBlock().iterator().next();
					// Catalogo nuevo
					catalogFase = userService.findCatalogFaseById(catalogFase.getId());
					catalogFaseBlock.setCatalogFase(catalogFase);
					userService.saveCatalogFaseBlock(catalogFaseBlock);
				}
			}
			if(type.equals("CATALOG-FASE-BLOCK-TECHNOLOGY")) {
				if(certificationTrack.getId() > 0 && null != certificationTrack.getCatalogFase() && certificationTrack.getCatalogFase().size() > 0) {
					CatalogFase catalogFase = certificationTrack.getCatalogFase().iterator().next();
					CatalogFaseBlock catalogFaseBlock = catalogFase.getCatalogFaseBlock().iterator().next();
					CatalogFaseBlockTechnology catalogFaseBlockTechnology = catalogFaseBlock.getCatalogFaseBlockTechnology().iterator().next();
					// Catalogo nuevo
					catalogFaseBlock = userService.findCatalogFaseBlockById(catalogFaseBlock.getId());
					catalogFaseBlockTechnology.setCatalogFaseBlock(catalogFaseBlock);
					userService.saveCatalogFaseBlockTechnology(catalogFaseBlockTechnology);
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
	
	@RequestMapping(value = "enabledCatalogFase/{id}/{active}/{type}", method = RequestMethod.POST)
	public Map<Long, String> enabledCatalogFase(@PathVariable int id, @PathVariable int active, @PathVariable String type) {
		Map<Long, String> map = new HashMap<>();
		try {
			if(!userService.activeCatalogFaseBlockTechnology(id, active, type)) {
				throw new Exception("Información fases y bloques no modificado");
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