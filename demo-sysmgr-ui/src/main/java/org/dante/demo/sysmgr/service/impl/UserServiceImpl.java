package org.dante.demo.sysmgr.service.impl;

import org.dante.demo.sysmgr.client.UserClient;
import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.dante.demo.sysmgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserClient userClient;

	@Override
	@HystrixCommand(fallbackMethod="queryUserPage")
	public BaseResp<PageResp<UserResp>> queryUserPage(PageReq userPageReq) throws Exception {
		BaseResp<PageResp<UserResp>> resp = userClient.queryUserPage(userPageReq);
		return resp;
	}

}
