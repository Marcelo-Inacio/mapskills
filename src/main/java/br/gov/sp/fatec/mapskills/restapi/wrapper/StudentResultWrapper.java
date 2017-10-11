/*
 * @(#)StudentResultWrapper.java 1.0 03/01/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.restapi.wrapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.domain.skill.Skill;
import br.gov.sp.fatec.mapskills.restapi.serializer.StudentResultSerializer;

/**
 * 
 * A classe {@link StudentResultWrapper} contem os conjuntos de informacoes
 * necessarios para renderizacao do grafico de radar com o resultado de um aluno.
 *
 * @author Marcelo
 * @version 1.0 03/01/2017
 */
@JsonSerialize(using = StudentResultSerializer.class)
public class StudentResultWrapper {
	
	private final List<String> skillLabels = new LinkedList<>();
	private final List<BigDecimal> skillValues = new LinkedList<>();
	private final List<Skill> skills = new LinkedList<>();
	
	public StudentResultWrapper(final List<Object[]> context) {		
		for(final Object[] result : context) {
			this.skillLabels.add(String.valueOf(result[1]));
			this.skillValues.add(new BigDecimal(result[3].toString()));
			this.skills.add(new Skill(String.valueOf(result[1]), String.valueOf(result[2])));
		}

	}
	
	public List<String> getSkills() {
		return Collections.unmodifiableList(skillLabels);
	}
	
	public List<BigDecimal> getValues() {
		return Collections.unmodifiableList(skillValues);
	}
	
	public List<Skill> getSkillsDeatils() {
		return Collections.unmodifiableList(skills);
	}
}