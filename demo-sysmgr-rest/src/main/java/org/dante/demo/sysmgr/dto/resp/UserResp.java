package org.dante.demo.sysmgr.dto.resp;

import java.util.Set;

import com.google.common.collect.Sets;

public class UserResp {
	private String account;
	private String name;
	private String email;
	private String password;
	private Set<String> authoritys;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getAuthoritys() {
		if (this.authoritys == null) {
			this.authoritys = Sets.newHashSet();
		}
		return authoritys;
	}

}
