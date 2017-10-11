/*
 * @(#)Login.java 1.0 02/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.Getter;
/**
 * A classe <code>Login</code> representa as credenciais de acesso a aplcacao.
 * 
 * @author Marcelo
 *
 */
@Getter
@Embeddable
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	@Lob
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@SuppressWarnings("unused")
	private Login() {
		this(null, null);
	}
	
	public Login(final String username, final String password) {
		this.username = username;
		this.password = password;
	}
	
	public void update(final Login newLogin) {
		username = newLogin.getUsername();
		password = newLogin.getPassword();
	}	
}