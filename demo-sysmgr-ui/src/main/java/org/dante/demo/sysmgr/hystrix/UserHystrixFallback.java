package org.dante.demo.sysmgr.hystrix;

import org.dante.demo.sysmgr.client.UserClient;
import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class UserHystrixFallback implements UserClient {
	
	private static final Logger logger = LoggerFactory.getLogger(UserHystrixFallback.class);
	
	@Override
	public UserResp findUserByAccount(String account) {
		return null;
	}

	@Override
	public BaseResp<PageResp<UserResp>> queryUserPage(PageReq userPageReq) {
		logger.error("Fallback call queryUserPage, userPageReq:{}", userPageReq);
		BaseResp<PageResp<UserResp>> baseResp = new BaseResp<PageResp<UserResp>>();
		PageResp<UserResp> pageResp = new PageResp<UserResp>();
		pageResp.setResult(Lists.newArrayList());
		baseResp.setResult(pageResp);
		return baseResp; 
	}
}
