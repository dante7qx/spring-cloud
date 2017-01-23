package org.dante.demo.getway.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dante.demo.getway.client.ResourceClient;
import org.dante.demo.getway.config.security.SecurityConstant;
import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.ResourceResp;
import org.dante.demo.getway.dto.resp.RespCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ResourceService {

	@Autowired
	private ResourceClient resourceClient;

	@HystrixCommand(fallbackMethod = "fallbackFindResourceMap", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50000") })
	public Map<RequestMatcher, Set<ConfigAttribute>> findResourceMap() {
		Map<RequestMatcher, Set<ConfigAttribute>> resourceMap = new HashMap<RequestMatcher, Set<ConfigAttribute>>();
		BaseResp<List<ResourceResp>> resp = resourceClient.queryResources();
		int resultCode = resp.getResultCode();
		if (resultCode != RespCode.SUCCESS.code()) {
			System.exit(0);
		}
		List<ResourceResp> resources = resp.getResult();
		for (ResourceResp resource : resources) {
			String url = resource.getUrl();
			Set<String> authoritys = resource.getAuthoritys();
			if (CollectionUtils.isEmpty(authoritys)) {
				continue;
			}
			RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
			if (resourceMap.containsKey(requestMatcher)) {
				Set<ConfigAttribute> configAttributes = resourceMap.get(requestMatcher);
				configAttributes.addAll(authorityToConfigAttribute(authoritys));
				resourceMap.put(requestMatcher, configAttributes);
			} else {
				resourceMap.put(requestMatcher, authorityToConfigAttribute(authoritys));
			}
		}
		return resourceMap;
	}
	
	public Map<RequestMatcher, Set<ConfigAttribute>> fallbackFindResourceMap() {
		return new HashMap<RequestMatcher, Set<ConfigAttribute>>();
	}

	public BaseResp<List<ResourceResp>> queryResourceList() {

		return null;
	}
	
	private Set<ConfigAttribute> authorityToConfigAttribute(Set<String> authoritys) {
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		for (String authority : authoritys) {
			configAttributes.add(new SecurityConfig(SecurityConstant.ROLE_PREFIX + authority));
		}
		return configAttributes;
	}

}
