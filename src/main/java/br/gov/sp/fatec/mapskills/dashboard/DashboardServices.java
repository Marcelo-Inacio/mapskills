/*
 * @(#)DashboardServices.java 1.0 1 17/09/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.dashboard;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;

/**
 * A classe {@link DashboardServices}
 *
 * @author Marcelo
 * @version 1.0 17/09/2017
 */
@Component
@AllArgsConstructor
public class DashboardServices {
	
	private final DashboardRepository repository;
	
	public List<GlobalStudentsIndicator> getGlobalStudentIndicators(final String yearSemester) {
		final String yearSemesterFilter = StringUtils.isEmpty(yearSemester) ? getYearSemesterCurrent() : yearSemester;
		return repository.findGlobalStudentsIndicator(yearSemesterFilter);
	}
	
	public List<InstitutionStudentsIndicator> getInstitutionStudentIndicators(final String yearSemester, final String level) {
		final String yearSemesterFilter = StringUtils.isEmpty(yearSemester) ? getYearSemesterCurrent() : yearSemester;
		return repository.findInstitutionStudentsIndicator(yearSemesterFilter, level);
	}
	
	/**
	 * Metodo que recupera o ano e semestre corrente.
	 * Ex.: 172; onde 17 eh o ano e 2 eh o semestre.
	 */
	private String getYearSemesterCurrent() {
		final LocalDate currentDate = LocalDate.now();
		final String semester = currentDate.getMonthValue() < 7 ? "1" : "2";
		final String year = String.valueOf(currentDate.getYear());
		return year.substring(2).concat(semester);
	}

}