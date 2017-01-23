package org.dante.demo.getway.service;

import org.dante.demo.getway.client.UserClient;
import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserClient userClient;
	
	public BaseResp<UserResp> queryByAccount(String account) {
		return userClient.querySingleUser(account);
	}

}
