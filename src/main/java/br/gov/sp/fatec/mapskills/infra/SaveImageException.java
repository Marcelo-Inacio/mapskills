/*
 * @(#)SaveImageException.java 1.0 06/01/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.gov.sp.fatec.mapskills.domain.MapSkillsException;
/**
 * 
 * A classe {@link SaveImageException} lancada quando e
 * encontrada um problema ao salvar a imagem no servidor aplicacao.
 * Retornando status 400 para quem chamou.
 *
 * @author Marcelo
 * @version 1.0 06/01/2017
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaveImageException extends MapSkillsException {

	private static final long serialVersionUID = 1L;
	
	public SaveImageException(final String filename, final Throwable arg1) {
		super("Problema ao salvar ".concat(filename), arg1);
	}
	
}