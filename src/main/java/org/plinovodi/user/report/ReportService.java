package org.plinovodi.user.report;

import org.plinovodi.user.intervention.Intervention;
import org.plinovodi.user.intervention.InterventionDTO;
import org.plinovodi.user.intervention.InterventionMapper;
import org.plinovodi.user.intervention.InterventionService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportService {

	private final InterventionService interventionService;
	private final InterventionMapper  interventionMapper;
	private final ReportRepository    reportRepository;
	private final ReportMapper        reportMapper;

	public ReportDTO addReport (ReportDTO reportDTO) {
		Optional<Report> dbReportOpt = reportRepository.findByID(reportDTO.id());

		if (dbReportOpt.isPresent()) {
			throw new RuntimeException("");
		}

		reportRepository.save(reportMapper.toEntity(reportDTO));

		return reportDTO;
	}

	public List<ReportDTO> getReports () {
		return reportRepository.findAll()
			.stream()
			.map(reportMapper::toDTO)
			.toList();
	}

	public ReportDTO getReport (String uuid) {
		Optional<Report> dbReportOpt = reportRepository.findByID(uuid);

		if (dbReportOpt.isEmpty()) {
			throw new RuntimeException("");
		}

		return reportMapper.toDTO(
			dbReportOpt.get()
		);
	}

	public ReportDTO updateReport (String uuid, ReportDTO reportDTO) {
		Optional<Report> dbReportOpt = reportRepository.findByID(uuid);

		if (dbReportOpt.isEmpty()) {
			throw new RuntimeException(String.format(
				"The form with uuid [%s] does not exist.",
				uuid
			));
		}

		Report dbUserReport = dbReportOpt.get();

		String firstName = reportDTO.firstName();
		String lastName  = reportDTO.lastName();
		List<InterventionDTO> interventionList = reportDTO.interventions();

		if (firstName != null && !firstName.equalsIgnoreCase(dbUserReport.getFirstName())) {
			throw new RuntimeException(String.format(
				"The first name in form [%s] and db [%s] do not match.",
				firstName, dbUserReport.getFirstName()
			));
		}

		if (lastName != null && !lastName.equalsIgnoreCase(dbUserReport.getLastName())) {
			throw new RuntimeException(String.format(
				"The last name in form [%s] and db [%s] do not match.",
				lastName, dbUserReport.getLastName()
			));
		}

		if (interventionList != null && !interventionList.isEmpty()) {
			List<Intervention> interventions = new ArrayList<>(
				dbUserReport.getInterventions()
			);

			List<Intervention> formInterventions = interventionList
				.stream()
				.map(interventionMapper::toIntervention)
				.toList();

			interventions.addAll(formInterventions);
			dbUserReport.setInterventions(interventions);

			interventionService.postInterventions(interventionList);
			reportRepository.save(dbUserReport);
		}

		return reportMapper.toDTO(dbUserReport);
	}

}
