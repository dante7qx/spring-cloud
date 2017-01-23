package org.dante.demo.sysmgr.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.dante.demo.sysmgr.domain.Authority;
import org.dante.demo.sysmgr.domain.Role;
import org.dante.demo.sysmgr.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecs {

		
	public static Specification<User> queryByAccountAndAuthCode(String account, List<String> authCodes)  {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				SetJoin<User, Role> roleJoin = root.join(root.getModel().getSet("roles", Role.class), JoinType.LEFT);
				Root<Role> roleRoot = query.from(Role.class);
				SetJoin<Role, Authority> authJoin = roleJoin.join(roleRoot.getModel().getSet("authoritys", Authority.class), JoinType.LEFT);
				
				Predicate accountEq = cb.equal(root.get("account").as(String.class), account);
				Predicate authCodeIn = authJoin.get("code").as(String.class).in(authCodes);
				query.where(cb.and(accountEq,authCodeIn));
				
				return query.getRestriction();
			}
			
		};
	}
}
