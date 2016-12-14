/*
 * @(#)StudentSerializer.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;

import br.gov.sp.fatec.mapskills.domain.user.Student;
import br.gov.sp.fatec.mapskills.domain.user.User;

@Component
public class StudentSerializer extends DefaultUserSerializer {
	
	@Override
	public void serialize(final User user, final JsonGenerator generator) throws IOException {
		generator.writeStartObject();
		serializeDefaultValues(user, generator);
		final Student student = (Student) user;
		generator.writeStringField("ra", student.ra());
		generator.writeStringField("institutionCode", student.institutionCode());
		generator.writeStringField("courseCode", student.courseCode());
		generator.writeStringField("phone", student.phone());
		generator.writeBooleanField("isCompleted", student.isCompleted());
		generator.writeEndObject();
		
	}
	
}