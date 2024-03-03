package org.plinovodi.user.report;

import org.plinovodi.user.intervention.InterventionDTO;

import java.time.LocalDate;
import java.util.List;

public record ReportDTO (
	String id,
	String firstName,
	String lastName,
	LocalDate dateStart,
	List<InterventionDTO> interventions
) {

}
