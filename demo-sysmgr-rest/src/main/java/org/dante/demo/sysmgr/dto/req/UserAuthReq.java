package org.dante.demo.sysmgr.dto.req;

import java.util.List;

public class UserAuthReq {

	private String account;
	private String password;
	private List<String> authCodes;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthCodes() {
		return authCodes;
	}

	public void setAuthCodes(List<String> authCodes) {
		this.authCodes = authCodes;
	}

	@Override
	public String toString() {
		return "UserAuthReq [account=" + account + ", authCodes=" + authCodes + "]";
	}

}
