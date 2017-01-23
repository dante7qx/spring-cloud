package org.dante.demo.sysmgr.service;

import java.util.List;
import java.util.Map;

import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

public abstract class AbstractService<T, E> {
	
	@Autowired
	private JpaRepository<E, Long> jpaRepository;
	@Autowired
	private JpaSpecificationExecutor<E> specificationExecutor;
	
	public PageResp<T> queryPage(PageReq pageReq) {
		PageResp<T> pageResp = null;
		int pageNo = pageReq.getPageNo();
		int pageSize = pageReq.getPageSize();
		String sortDir = pageReq.getSortDir();
		String sortCol = pageReq.getSortCol();
		Page<E> page = null;
		Pageable pageRequest = buildPageRequest(pageNo, pageSize, sortDir, sortCol);
		if(!pageReq.getFilter().isEmpty()) {
			Specification<E> spec = buildSpecification(pageReq.getFilter());
			page = specificationExecutor.findAll(spec, pageRequest);
		} else {
			page = jpaRepository.findAll(pageRequest);
		}
		pageResp = new PageResp<T>();
		List<E> dbList = page.getContent();
		if(!CollectionUtils.isEmpty(dbList)) {
			List<T> list = Lists.newArrayList();
			for (E e : dbList) {
				T t = switchDomainToDto(e);
				list.add(t);
			}
			pageResp.setResult(list);
		}
		pageResp.setPageNo(page.getNumber() + 1);
		pageResp.setPageSize(page.getNumberOfElements());
		pageResp.setTotalPage(page.getTotalPages());
		pageResp.setTotalCount(Integer.valueOf(page.getTotalElements() + ""));
		return pageResp;
	}
	
	private Pageable buildPageRequest(int pageNo, int pageSize, String sortDir, String sortCol) {
		Sort sort = null;
		if(StringUtils.isEmpty(sortCol)) {
			sort = new Sort(Direction.DESC, "id");
		} else if(StringUtils.isEmpty(sortDir) || PageReq.ASC.equalsIgnoreCase(sortDir)) {
			sort = new Sort(Direction.ASC, sortCol);
		} else if(PageReq.DESC.equalsIgnoreCase(sortDir)) {
			sort = new Sort(Direction.DESC, sortCol);
		}
//		sort = sort.and(new Sort(Direction.DESC, "name"));  // 多字段排序
		return new PageRequest(pageNo - 1, pageSize, sort);
	}
	
	/**
	 * 将数据库的实体类转化为dto对象
	 * 
	 * @param domain
	 * @return
	 */
	protected abstract T switchDomainToDto(E domain);
	
	/**
	 * 构造查询条件
	 * 
	 * @param filter
	 * @return
	 */
	protected abstract Specification<E> buildSpecification(Map<String, Object> filter);
	
}
