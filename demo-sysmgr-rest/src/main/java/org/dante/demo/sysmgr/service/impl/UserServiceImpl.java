package org.dante.demo.sysmgr.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dante.demo.sysmgr.dao.UserDao;
import org.dante.demo.sysmgr.dao.UserSpecs;
import org.dante.demo.sysmgr.domain.Authority;
import org.dante.demo.sysmgr.domain.Role;
import org.dante.demo.sysmgr.domain.User;
import org.dante.demo.sysmgr.dto.req.PageReq;
import org.dante.demo.sysmgr.dto.resp.PageResp;
import org.dante.demo.sysmgr.dto.resp.UserResp;
import org.dante.demo.sysmgr.service.AbstractService;
import org.dante.demo.sysmgr.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractService<UserResp, User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserResp findByAccount(String account) throws Exception {
		UserResp userResp = null;
		User user = userDao.findByAccount(account);
		if (user != null) {
			userResp = new UserResp();
			BeanUtils.copyProperties(user, userResp);
			userResp.getAuthoritys().addAll(convertRoleToAuths(user.getRoles()));
		}
		return userResp;
	}
	
	@Override
	public UserResp findByAccountAndPassword(String account, String password) throws Exception {
		User user = userDao.findByAccountAndPassword(account, password);
		return switchDomainToDto(user);
	}
	
	@Override
	public UserResp findByAccountAndAuthCode(String account, List<String> authCodes) throws Exception {
		User user = userDao.findOne(UserSpecs.queryByAccountAndAuthCode(account, authCodes));
		UserResp userResp = switchDomainToDto(user);
		return userResp;
	}
	
	@Override
	public List<User> findAll() throws Exception {
		return userDao.findAll(new Sort(Sort.Direction.DESC, "name"));
	}
	
	@Override
	public PageResp<UserResp> findPage(PageReq pageReq) throws Exception {
		return super.queryPage(pageReq);
	}
	
	private Set<String> convertRoleToAuths(Set<Role> roles) {
		Set<String> auths = Sets.newHashSet();
		if (CollectionUtils.isEmpty(roles)) {
			return auths;
		}
		for (Role role : roles) {
			Set<Authority> authoritys = role.getAuthoritys();
			if (!CollectionUtils.isEmpty(authoritys)) {
				for (Authority authority : authoritys) {
					auths.add(authority.getCode());
				}
			}
			
		}
		return auths;
	}

	@Override
	protected UserResp switchDomainToDto(User user) {
		UserResp userResp = null;
		if(user != null) {
			userResp = new UserResp();
			BeanUtils.copyProperties(user, userResp);
			userResp.getAuthoritys().addAll(convertRoleToAuths(user.getRoles()));
		}
		return userResp;
	}

	@Override
	protected Specification<User> buildSpecification(Map<String, Object> filter) {
		return new Specification<User>(){
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				String account = (String) filter.get("account");
				String email = (String) filter.get("email");
				if(!StringUtils.isEmpty(email)) {
					Predicate emailLike = cb.like(root.get("email").as(String.class), "%"+email+"%");
					predicates.add(emailLike);
				}
				if(!StringUtils.isEmpty(account)) {
					Predicate accountLike = cb.like(root.get("account").as(String.class), "%"+account+"%");
					predicates.add(accountLike);
				}
				return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}};
	}

}
