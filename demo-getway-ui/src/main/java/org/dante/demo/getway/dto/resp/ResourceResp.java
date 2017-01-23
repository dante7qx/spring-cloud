package org.dante.demo.getway.dto.resp;

import java.util.Set;

public class ResourceResp {
	private Long id;
	private String name;
	private String url;
	private String type;
	private Long pid;
	private String fullId;
	private Integer orderId;
	private String resourceDesc;
	private Set<String> authoritys;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getFullId() {
		return fullId;
	}
	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public Set<String> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(Set<String> authoritys) {
		this.authoritys = authoritys;
	}
	
}
