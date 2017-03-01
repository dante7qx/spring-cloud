package org.dante.demo.sysmgr.dao;

import java.util.List;
import java.util.Set;

import org.dante.demo.sysmgr.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceDao extends JpaRepository<Resource, Long> {
	
	@Query("select r from Resource r where r.authority.code in :authCodes")
	public List<Resource> findByAuthCodes(@Param("authCodes") List<String> authCodes);
	
	@Query("select distinct r.pid from Resource r")
	public Set<Long> findAllParentId();
}
