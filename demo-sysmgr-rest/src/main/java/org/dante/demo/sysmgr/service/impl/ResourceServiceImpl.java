package org.dante.demo.sysmgr.service.impl;

import java.util.List;
import java.util.Set;

import org.dante.demo.sysmgr.dao.ResourceDao;
import org.dante.demo.sysmgr.domain.Authority;
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
import com.google.common.collect.Sets;


@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Cacheable(value = "sysmrg-resourcecache")
	@Override
	public List<ResourceResp> findAll() throws Exception {
		List<Resource> resources = resourceDao.findAll(new Sort(Sort.Direction.ASC, "orderId"));
		List<ResourceResp> list = Lists.newArrayList();
		if(!CollectionUtils.isEmpty(resources)) {
			for (Resource resource : resources) {
				ResourceResp resp = new ResourceResp();
				BeanUtils.copyProperties(resource, resp, "authoritys");
				resp.setAuthoritys(convertAuthority(resource.getAuthoritys()));
				list.add(resp);
			}
		}
		return list;
	}

	
	private Set<String> convertAuthority(Set<Authority> authoritys) {
		Set<String> auths = Sets.newHashSet();
		if(!CollectionUtils.isEmpty(authoritys)) {
			for (Authority authority : authoritys) {
				auths.add(authority.getCode());
			}
		}
		return auths;
	}
}
