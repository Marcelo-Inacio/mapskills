/*
 * @(#)AcademicRegistry.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AcademicRegistry {
	
	@Column(name = "stu_ra")
	private String ra;
	
	@Column(name = "ins_code", nullable = false)
	private String institutionCode;
	
	@Column(name = "crs_code", nullable = false)
	private String courseCode;
	
	public AcademicRegistry() {}
	
	public AcademicRegistry(final String ra, final String institutionCode, final String courseCode) throws MapSkillsException {
		this.ra = ra;
		this.institutionCode = institutionCode;
		this.courseCode = courseCode;
	}
	
	public String getRa() {
		return ra;
	}
	
	public String getInstitutionCode() {
		return institutionCode;
	}
	
	public String getYear() {
		return ra.substring(6, 8);
	}
	
	public String getSemester() {
		return ra.substring(8, 9);
	}
	
	public String getStudentCode() {
		return ra.substring(9);
	}

	public String getCourseCode() {
		return courseCode;
	}

}
