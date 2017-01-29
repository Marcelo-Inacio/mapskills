/*
 * @(#)SecurityConfig.java 1.0 07/01/2017
 *
 * Copyright (c) 2016, Fatec-Jessen Vidal. All rights reserved.Fatec-Jessen Vidal 
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.JOSEException;

import br.gov.sp.fatec.mapskills.authentication.AuthenticationListener;
import br.gov.sp.fatec.mapskills.authentication.PreAuthenticatedUserFilter;
import br.gov.sp.fatec.mapskills.authentication.jwt.JwtSignatureVerifier;
import br.gov.sp.fatec.mapskills.authentication.jwt.JwtVerifier;

/**
 * A classe <code>SecurityConfig</code> representa a configuracao de seguranca
 * da aplicacao em nivel de URI
 * 
 * @author Marcelo
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("jwtAuthenticationManager")
    private AuthenticationManager jwtAuthenticationManager;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    @Qualifier("providerManager")
    private AuthenticationManager providerManager;
	/**
	 * configuração de seguranca em nivel de uri 
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class).csrf().disable();
		http.addFilter(preAuthenticationFilter());
		http.addFilter(loginFilter());
		http.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
	}
	
	@Bean
    public SCMCorsFilter corsFilter() {
        return new SCMCorsFilter();
    }
	
	@Bean
	public Filter preAuthenticationFilter() {
		final PreAuthenticatedUserFilter filter = new PreAuthenticatedUserFilter();
		filter.setAuthenticationManager(jwtAuthenticationManager);
		return filter;
	}
	
	@Bean
	public Filter loginFilter() {
		final UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(providerManager);
		filter.setAuthenticationSuccessHandler(successHandler);
		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}
	
	@Bean
	public AuthenticationManager providerManager(@Qualifier("defaultAuthenticationProvider") final AuthenticationProvider provider) {
		return new ProviderManager(Arrays.asList(provider));
	}
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5);
    }
	
	@Bean
    public List<AuthenticationListener> authenticationListeners(
            @Qualifier("responseHeaderAuthenticationListener") final AuthenticationListener responseHeaderListener) {
        final List<AuthenticationListener> list = new ArrayList<>(1);
        list.add(responseHeaderListener);
        return list;
    }
	
	@Bean
    public List<JwtVerifier> verifiersList(@Qualifier("issuerReferenceClaimsVerifier") final JwtVerifier issuerVerifier,
            @Qualifier("notBeforeTimeClaimsVerifier") final JwtVerifier notBeforeTimeVerifier,
            @Qualifier("referenceDateClaimsVerifier") final JwtVerifier referenceDateVerifier,
            @Qualifier("jwtSignatureVerifier") final JwtVerifier jwtSignatureVerifier) {
        final List<JwtVerifier> verifiersList = new ArrayList<>(4);
        verifiersList.add(jwtSignatureVerifier);
        verifiersList.add(issuerVerifier);
        verifiersList.add(notBeforeTimeVerifier);
        verifiersList.add(referenceDateVerifier);
        return verifiersList;
    }
	
	@Bean
    public JwtVerifier jwtSignatureVerifier(@Value("${jwt.secret}") final String secret) throws JOSEException {
        return new JwtSignatureVerifier(secret);
    }
	
	@Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        final MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(new Object[] {SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
        return methodInvokingFactoryBean;
    }

}
