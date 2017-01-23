package org.dante.demo.getway.client;

import java.util.List;

import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.ResourceResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "micro-demo-sysmgr-rest")
public interface ResourceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/resource/query_resource_list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResp<List<ResourceResp>> queryResources();
}
