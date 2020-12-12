package com.kardex.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.kardex.service.UserService;
import com.kardex.utils.LdapUtils;

@Component
public class SpringSecurityInitializer implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityInitializer.class);
	
	@Autowired
    HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	private UserService userService;
	
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
    	LOGGER.info("## --> SpringSecurityInitializer.onApplicationEvent() ##");
    	// TODO: daza con LDAP --------------------------------------------
    	validateLdap();
		// TODO: daza sin LDAP --------------------------------------------
    	// LOGGER.debug("No existe LDAP para validar.");
    	// ----------------------------------------------------------------
    	LOGGER.info("## <-- SpringSecurityInitializer.onApplicationEvent() ##");
    }
    
    public void validateLdap() {
    	try {
    		LOGGER.info("Validando LDAP por segunda ocacion.");
    		// Validar LDAP despues de acceder al login.html
    		String pEmail = request.getParameter(LdapUtils.decrypt(userService.getParameterForName("LDAP_PEMAIL").getValue()));
    		String []email = pEmail.split("\\@");
    		if(email.length > 1) {
    			pEmail = email[0];
    		} else {
    			throw new Exception("Parametros invalidos.");
    		}
        	String pKey = request.getParameter(LdapUtils.decrypt(userService.getParameterForName("LDAP_PKEY").getValue()));
        	//
        	String validateUserLdap = LdapUtils.validateUserCredentials(userService.getParameterForName("LDAP-INITIAL-CONTEXT-FACTORY").getValue(),
					userService.getParameterForName("LDAP-SECURITY-AUTHENTICATION").getValue(),
					userService.getParameterForName("LDAP-SECURITY-PRINCIPAL").getValue(),
					userService.getParameterForName("LDAP-SECURITY-CREDENTIALS").getValue(),
					userService.getParameterForName("LDAP-PROVIDER-URL").getValue(), 
					userService.getParameterForName("LDAP_SAMA").getValue(), 
					pEmail, 
					pKey, 
					userService.getParameterForName("LDAP_DC").getValue());
			if(validateUserLdap.equals("Ok")) {
				LOGGER.info("Usuario validado BEFORE");
			} else {
				LOGGER.info("Error before: {}", validateUserLdap);
				deleteSession();
				response.sendRedirect("/logout");
			}
    	} catch (Exception e1) {
    		LOGGER.info("Error before e1: {}", e1.getMessage());
    		try {
    			deleteSession();
    			response.sendRedirect("/logout");
			} catch (Exception e2) {
				LOGGER.info("Error before e2: {}", e1.getMessage());
			}
		}
    }
    
    public void deleteSession() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}