package org.dante.demo.sysmgr.dao;

import org.dante.demo.sysmgr.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
	
}
