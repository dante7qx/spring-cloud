package org.dante.demo.sysmgr.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_resource")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;
	private String type;
	private Long pid;
	@Column(name = "full_id")
	private String fullId;
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "resource_desc")
	private String resourceDesc;
	
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name="t_authority_resource", joinColumns={@JoinColumn(name = "resource_id")}, inverseJoinColumns={@JoinColumn(name = "authority_id")})
	private Set<Authority> authoritys;
	
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
	public Set<Authority> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(Set<Authority> authoritys) {
		this.authoritys = authoritys;
	}
}
