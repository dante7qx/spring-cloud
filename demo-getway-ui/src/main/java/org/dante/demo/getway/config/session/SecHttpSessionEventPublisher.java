package org.dante.demo.getway.config.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.dante.demo.getway.config.security.SecAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

public class SecHttpSessionEventPublisher extends HttpSessionEventPublisher {
	private static final Logger logger = LoggerFactory.getLogger(SecHttpSessionEventPublisher.class);
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);
		HttpSession session = event.getSession();
		SessionInformation sessionInfo = sessionRegistry.getSessionInformation(session.getId());
		if(sessionInfo != null) {
			SecAuthUser principal = (SecAuthUser) sessionInfo.getPrincipal();
			logger.info("session -> {} [{}] create.", session.getId(), principal.getUsername());
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		SecurityContext sc = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if(sc != null) {
			SecAuthUser principal = (SecAuthUser) sc.getAuthentication().getPrincipal();
			logger.info("session -> {} [{}] destroy.", session.getId(), principal.getUsername());
		}
		super.sessionDestroyed(event);
	}
}
