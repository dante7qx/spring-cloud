package org.dante.demo.getway.config.security;

import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.RespCode;
import org.dante.demo.getway.dto.resp.UserResp;
import org.dante.demo.getway.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecUserDetailService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private UserService userHystrixService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BaseResp<UserResp> userResp = null;
		try {
			userResp = userHystrixService.queryByAccount(username);
		} catch (Exception e) {
			logger.error("loadUserByUsername error, Account:{}", username, e);
			throw new UsernameNotFoundException("用户名["+username+"]认证失败！");
		}
		int resultCode = userResp.getResultCode();
		if(resultCode != RespCode.SUCCESS.code()) {
			throw new UsernameNotFoundException("用户名["+username+"]不存在！");
		}
		return convertToSecUser(userResp.getResult());
	}

	private SecAuthUser convertToSecUser(UserResp userResp) {
		SecUser user = new SecUser();
		BeanUtils.copyProperties(userResp, user);
		return new SecAuthUser(user, userResp.getPassword());
	}

}
