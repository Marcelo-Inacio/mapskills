/*
 * @(#)User.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.domain.user;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
/**
 * A classe abstrata <code>User</code> � uma entidade que 
 * representa usuario generico que pode acessar a aplicacao.
 * 
 * @author Marcelo
 *
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Principal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@Embedded
	private final Login login;
	
	public abstract ProfileType getProfile();
	
	protected User() {
		this(null, null);
	}
		
	public User(final String name, final Login login) {
		this.name = name;
		this.login = login;
	}
	
	public void update(final User userUpdate) {
		this.name = userUpdate.getName();
		updateLogin(userUpdate.getLogin());
	}
		
	public String getUsername() {
		return login.getUsername();
	}
	
	public String getPassword() {
		return login.getPassword();
	}
	
	public void updateLogin(final Login newLogin) {
		login.update(newLogin);
	}	
}