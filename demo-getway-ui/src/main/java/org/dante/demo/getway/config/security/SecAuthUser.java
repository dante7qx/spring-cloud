package org.dante.demo.getway.config.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * 认证用户接口
 * 
 * @author dante
 *
 */
public class SecAuthUser implements UserDetails {
	private static final long serialVersionUID = -3727833892326080358L;
	
	private SecUser user;
	private String password;
	
	public SecAuthUser(SecUser user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = Lists.newArrayList();
		Set<String> authCodes = this.user.getAuthoritys();
		if(!CollectionUtils.isEmpty(authCodes)) {
			auths = AuthorityUtils.createAuthorityList(authCodes.toArray(new String[authCodes.size()]));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return user.getAccount();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public SecUser getUser() {
		return user;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SecAuthUser) {
			return getUsername().equals(((SecAuthUser) obj).getUsername());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return getUsername().hashCode(); 
	}
}
