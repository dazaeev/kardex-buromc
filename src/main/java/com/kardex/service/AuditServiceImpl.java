package com.kardex.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kardex.model.Audit;
import com.kardex.repository.AuditRepository;

@Service("auditService")
public class AuditServiceImpl implements AuditService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public boolean insert(String data) {
		boolean resp = true;
		try {
			Map<String, String> mapUserDetails = userService.getUserDetails("AuditService.insert");
			Audit audit = new Audit();
			audit.setName(mapUserDetails.get("USERNAME"));
			audit.setAuditOperation(data);
			auditRepository.save(audit);
		} catch (Exception e) {
			LOGGER.info("Error al agregar auditoria: " + e.getMessage());
			resp = false;
		}
		return resp;
	}

	@Override
	public boolean insertAudit(String data) {
		boolean resp = true;
		
		return resp;
	}
}
