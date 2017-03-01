package org.dante.demo.sysmgr.service.impl;

import java.util.List;

import org.dante.demo.sysmgr.dao.ResourceDao;
import org.dante.demo.sysmgr.domain.Resource;
import org.dante.demo.sysmgr.dto.resp.ResourceResp;
import org.dante.demo.sysmgr.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;


@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Cacheable(value = "sysmrg-resource-cache")
	@Override
	public List<ResourceResp> findAll() throws Exception {
		List<Resource> resources = resourceDao.findAll(new Sort(Sort.Direction.ASC, "orderId"));
		List<ResourceResp> list = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(resources)) {
			for (Resource resource : resources) {
				ResourceResp resp = new ResourceResp();
				BeanUtils.copyProperties(resource, resp, "authoritys");
				resp.setAuthoritys(resource.getAuthority().getCode());
				list.add(resp);
			}
		}
		return list;
	}
	

}
