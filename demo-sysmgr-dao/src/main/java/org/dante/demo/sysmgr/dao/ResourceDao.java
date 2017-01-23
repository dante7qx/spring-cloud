package org.dante.demo.sysmgr.dao;

import org.dante.demo.sysmgr.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<Resource, Long> {

}
