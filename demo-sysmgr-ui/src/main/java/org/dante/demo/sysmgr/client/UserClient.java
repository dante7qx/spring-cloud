package org.dante.demo.sysmgr.client;

import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.dante.demo.sysmgr.hystrix.UserHystrixFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name= "micro-demo-sysmgr-rest", fallback=UserHystrixFallback.class)
public interface UserClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/query_single/{account}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserResp findUserByAccount(@PathVariable("account") String account);
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/query_page", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResp<PageResp<UserResp>> queryUserPage(@RequestBody PageReq userPageReq);
	
}
