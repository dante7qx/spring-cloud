package org.dante.demo.getway.config;

import java.util.List;

import org.dante.demo.getway.config.security.SecFilterInvocationSecurityMetadataSource;
import org.dante.demo.getway.config.security.SecUserDetailService;
import org.dante.demo.getway.config.security.SecVoter;
import org.dante.demo.getway.config.security.SecurityConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.google.common.collect.Lists;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public SecUserDetailService userDetailsService() {
		return new SecUserDetailService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/static/**", "/**/webjars/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin().
					loginPage("/login")
					.defaultSuccessUrl("/")
				.permitAll()
			.and()
				.logout()
				.deleteCookies("JSESSIONID").permitAll()
			.and()
				.csrf()
				//	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
				.addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
				.addFilterAt(concurrencyFilter(), ConcurrentSessionFilter.class)
				.sessionManagement()
				.sessionAuthenticationStrategy(compositeSessionAuthenticationStrategy())
				.invalidSessionUrl(SecurityConstant.SESSION_TIMEOUT);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public ConcurrentSessionFilter concurrencyFilter() {
		ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry(),
				SecurityConstant.SESSION_TIMEOUT);
		return concurrentSessionFilter;
	}

	@Bean
	public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
		List<SessionAuthenticationStrategy> delegateStrategies = Lists.newLinkedList();
		delegateStrategies.add(concurrentSessionControlAuthenticationStrategy());
		delegateStrategies.add(sessionFixationProtectionStrategy());
		delegateStrategies.add(registerSessionAuthenticationStrategy());
		CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy = new CompositeSessionAuthenticationStrategy(
				delegateStrategies);
		return compositeSessionAuthenticationStrategy;
	}

	@Bean
	public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistry());
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1); // 单个用户最大并行会话数
		concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false); // 设置为true时会报错且后登录的会话不能登录，默认为false不报错且将前一会话置为失效
		return concurrentSessionControlAuthenticationStrategy;
	}

	@Bean
	public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
		SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
		sessionFixationProtectionStrategy.setMigrateSessionAttributes(true);
		return sessionFixationProtectionStrategy;
	}

	@Bean
	public RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(sessionRegistry());
	}

	/**
	 * 授权配置定义
	 * 
	 * @return
	 */
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = Lists.newArrayList();
		decisionVoters.add(accessDecisionVoter());
		AccessDecisionManager accessDecisionManager = new AffirmativeBased(decisionVoters);
		return accessDecisionManager;
	}

	@Bean
	public AccessDecisionVoter accessDecisionVoter() {
		SecVoter roleVoter = new SecVoter();
		roleVoter.setRolePrefix(SecurityConstant.ROLE_PREFIX);
		return roleVoter;
	}

	@Bean
	public FilterInvocationSecurityMetadataSource securityMetadataSource() {
		FilterInvocationSecurityMetadataSource securityMetadataSource = new SecFilterInvocationSecurityMetadataSource();
		return securityMetadataSource;
	}

	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
		return filterSecurityInterceptor;
	}
}
