package org.plinovodi.user.form;

import org.plinovodi.user.intervention.InterventionDTO;

import java.time.LocalDate;
import java.util.List;

public record FormDTO(
	String uuid,
	String firstName,
	String lastName,
	LocalDate localDate,
	List<InterventionDTO> interventionList
) {

}
