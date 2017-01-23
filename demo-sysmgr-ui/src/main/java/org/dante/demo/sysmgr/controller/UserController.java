package org.dante.demo.sysmgr.controller;

import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.dante.demo.sysmgr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@RequestMapping(value = "/query", produces="application/json")
	@ResponseBody
	public Object query() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal;
	}
	
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@RequestMapping(value = "/query_page", produces="application/json")
	@ResponseBody
	public PageResp<UserResp> queryPage(@RequestBody PageReq userPageReq) {
		PageResp<UserResp> pageResp = null;
		try {
			BaseResp<PageResp<UserResp>> resp = userService.queryUserPage(userPageReq);
			pageResp = resp.getResult();
		} catch(Exception e) {
			logger.error("query_page, userPageReq:{}", userPageReq, e);
		}
		
		return pageResp;
	}
	
}
