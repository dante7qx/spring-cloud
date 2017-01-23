package org.dante.demo.sysmgr.service;

import java.util.List;

import org.dante.demo.sysmgr.dto.resp.ResourceResp;

public interface ResourceService {
	public List<ResourceResp> findAll() throws Exception;
}
