package org.dante.demo.sysmgr.controller;

import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.req.UserAuthReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.RespCode;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.dante.demo.sysmgr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/query_page", method = RequestMethod.POST, produces = "application/json")
	public BaseResp<PageResp<UserResp>> queryUserPage(@RequestBody PageReq userPageReq) {
		BaseResp<PageResp<UserResp>> resp = new BaseResp<PageResp<UserResp>>();
		PageResp<UserResp> pageResp = null;
		try {
			pageResp = userService.findPage(userPageReq);
			resp.setResult(pageResp);
		} catch (Exception e) {
			logger.error("query_page, userPageReq:{}", userPageReq, e);
			resp.setResultCode(RespCode.FAILURE.code());
		}
		return resp;
	}

	@RequestMapping(value = "/query_single/{account}", produces = "application/json")
	public BaseResp<UserResp> queryByAccount(@PathVariable String account) {
		BaseResp<UserResp> resp = new BaseResp<UserResp>();
		UserResp userResp = null;
		try {
			if (StringUtils.isEmpty(account)) {
				resp.setResultCode(RespCode.FAILURE.code());
				resp.setErrorMsg("account is required.");
				return resp;
			}
			logger.info("query_single, account:{}", account);
			userResp = userService.findByAccount(account);
		} catch (Exception e) {
			logger.error("query_single, account:{}", account, e);
			resp.setResultCode(RespCode.FAILURE.code());
		}
		resp.setResult(userResp);
		return resp;
	}

	@RequestMapping(value = "/query_with_account_password", method = RequestMethod.POST, produces = "application/json")
	public BaseResp<UserResp> queryByAccountAndPassword(@RequestBody UserAuthReq userAuth) {
		BaseResp<UserResp> resp = new BaseResp<UserResp>();
		UserResp userResp = null;
		try {
			if (StringUtils.isEmpty(userAuth.getAccount()) || StringUtils.isEmpty(userAuth.getPassword())) {
				resp.setResultCode(RespCode.FAILURE.code());
				resp.setErrorMsg("account, password is required.");
				return resp;
			}
			logger.info("query_with_account_password:{}", userAuth);
			userResp = userService.findByAccountAndAuthCode(userAuth.getAccount(), userAuth.getAuthCodes());
		} catch (Exception e) {
			logger.error("query_with_account_password:{}", userAuth, e);
			resp.setResultCode(RespCode.FAILURE.code());
		}
		resp.setResult(userResp);
		return resp;
	}

	@RequestMapping(value = "/query_with_account_authcode", method = RequestMethod.POST, produces = "application/json")
	public BaseResp<UserResp> queryAuthAccount(@RequestBody UserAuthReq userAuth) {
		BaseResp<UserResp> resp = new BaseResp<UserResp>();
		UserResp userResp = null;
		try {
			if (StringUtils.isEmpty(userAuth.getAccount()) || CollectionUtils.isEmpty(userAuth.getAuthCodes())) {
				resp.setResultCode(RespCode.FAILURE.code());
				resp.setErrorMsg("account, authCodes is required.");
				return resp;
			}
			logger.info("query_with_account_authcode:{}", userAuth);
			userResp = userService.findByAccountAndAuthCode(userAuth.getAccount(), userAuth.getAuthCodes());
		} catch (Exception e) {
			logger.error("query_with_account_authcode:{}", userAuth, e);
			resp.setResultCode(RespCode.FAILURE.code());
		}
		resp.setResult(userResp);
		return resp;
	}
}
