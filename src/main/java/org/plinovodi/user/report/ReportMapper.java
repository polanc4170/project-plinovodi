package org.plinovodi.user.report;

import org.plinovodi.user.intervention.InterventionMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReportMapper {

	private final InterventionMapper interventionMapper;

	public ReportDTO toDTO (Report userReport) {
		return new ReportDTO(
			userReport.getId(),
			userReport.getFirstName(),
			userReport.getLastName(),
			userReport.getDateStart(),
			userReport.getInterventions()
				.stream()
				.map(interventionMapper::toInterventionDTO)
				.toList()
		);
	}

	public Report toEntity (ReportDTO reportDTO) {
		return new Report(
			null,
			reportDTO.id(),
			reportDTO.firstName(),
			reportDTO.lastName(),
			reportDTO.dateStart(),
			reportDTO.interventions()
				.stream()
				.map(interventionMapper::toIntervention)
				.toList()
		);
	}

}
