package org.dante.demo.getway.config.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dante.demo.getway.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static final Logger logger = LoggerFactory.getLogger(SecFilterInvocationSecurityMetadataSource.class);
	
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object paramObject) throws IllegalArgumentException {
		logger.info("request -> {}", paramObject);
		Map<RequestMatcher, Set<ConfigAttribute>> resourceMap = resourceService.findResourceMap();
		HttpServletRequest request = ((FilterInvocation) paramObject).getRequest();
		for (Map.Entry<RequestMatcher,Set<ConfigAttribute>> entry : resourceMap.entrySet()) {
			if (((RequestMatcher) entry.getKey()).matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> paramClass) {
		return FilterInvocation.class.isAssignableFrom(paramClass);
	}


}
