/*
 * @(#)User.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
/**
 * A classe abstrata <code>User</code> � uma entidade que representa usuario generico
 * que pode acessar a aplicacao.
 * 
 * @author Marcelo
 *
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "use_id")
	private int id;
	
	@Column(name = "use_name")
	private String name;

	@Embedded
	private Login login;
	
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "pro_id")
	private ProfileType profile;
		
	public User() {}
	
	public User(final String name, final Login login, final ProfileType profile) {
		this.name = name;
		this.login = login;
		this.profile = profile;
	}
	
	public void profile(final ProfileType profile) {
		this.profile = profile;
	}
	
	public int id() {
		return id;
	}
	
	public String name() {
		return name;
	}
	
	public String username() {
		return login.username();
	}
	
	public String password() {
		return login.password();
	}
	
	public void changeName(final String newName) {
		name = newName;
	}

}