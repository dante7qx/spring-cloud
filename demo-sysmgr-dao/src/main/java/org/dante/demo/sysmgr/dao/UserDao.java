package org.dante.demo.sysmgr.dao;

import org.dante.demo.sysmgr.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	public User findByAccount(String account) throws Exception;
	
	public User findByAccountAndPassword(String account, String password) throws Exception;
}
