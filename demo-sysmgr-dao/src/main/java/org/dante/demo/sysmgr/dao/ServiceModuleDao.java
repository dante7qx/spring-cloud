package org.dante.demo.sysmgr.dao;

import org.dante.demo.sysmgr.domain.ServiceModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceModuleDao extends JpaRepository<ServiceModule, Long> {

}