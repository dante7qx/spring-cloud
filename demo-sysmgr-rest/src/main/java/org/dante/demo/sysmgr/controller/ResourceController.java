package org.dante.demo.sysmgr.controller;

import java.util.List;

import org.dante.demo.sysmgr.dto.resp.BaseResp;
import org.dante.demo.sysmgr.dto.resp.ResourceResp;
import org.dante.demo.sysmgr.dto.resp.RespCode;
import org.dante.demo.sysmgr.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "query_resource_list", method = RequestMethod.POST, produces = "application/json")
	public BaseResp<List<ResourceResp>> queryAllResource() {
		BaseResp<List<ResourceResp>> resp = new BaseResp<List<ResourceResp>>();
		List<ResourceResp> list = null;
		try {
			list = resourceService.findAll();
		} catch (Exception e) {
			logger.error("query_resource_list", e);
			resp.setResultCode(RespCode.FAILURE.code());
		}
		resp.setResult(list);
		return resp;
	}
}
