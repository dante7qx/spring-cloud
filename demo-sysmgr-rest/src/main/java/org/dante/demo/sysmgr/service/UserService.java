package org.dante.demo.sysmgr.service;

import java.util.List;

import org.dante.demo.sysmgr.domain.User;
import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.req.UserReq;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;

public interface UserService {
	
	public UserResp findByAccount(String account) throws Exception;
	
	public UserResp findByAccountAndPassword(String account, String password) throws Exception;
	
	public UserResp findByAccountAndAuthCode(String account, List<String> authCodes) throws Exception;
	
	public PageResp<UserResp> findPage(PageReq pageReq) throws Exception;
	
	public List<User> findAll() throws Exception;
	
	public UserResp save(UserReq userReq) throws Exception;
	
	public UserResp update(UserReq userReq) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
}
