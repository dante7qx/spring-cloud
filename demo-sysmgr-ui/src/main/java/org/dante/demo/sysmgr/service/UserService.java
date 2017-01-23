package org.dante.demo.sysmgr.service;

import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;

public interface UserService {
	
	public BaseResp<PageResp<UserResp>> queryUserPage(PageReq userPageReq) throws Exception;
}
