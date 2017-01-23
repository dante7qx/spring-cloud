package org.dante.demo.getway.config.security;

import java.io.Serializable;
import java.util.Set;

public class SecUser implements Serializable {
	
	private static final long serialVersionUID = -4221008994707777365L;
	
	private String account;
	private String name;
	private String email;
	private Set<String> authoritys;

	public SecUser() {
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<String> authoritys) {
		this.authoritys = authoritys;
	}
}
