package org.dante.demo.getway.client;

import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.UserResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "micro-demo-sysmgr-rest")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/query_single/{account}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResp<UserResp> querySingleUser(@PathVariable("account") String account);

}
