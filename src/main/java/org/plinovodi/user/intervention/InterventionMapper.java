package org.plinovodi.user.intervention;

import org.springframework.stereotype.Component;

@Component
public class InterventionMapper {

	public InterventionDTO toInterventionDTO (Intervention intervention) {
		return new InterventionDTO(
			intervention.getId(),
			intervention.getDateStart(),
			intervention.getHourStart(),
			intervention.getHourEnd(),
			intervention.getDescShort(),
			intervention.getDescLong()
		);
	}

	public Intervention toIntervention (InterventionDTO interventionDTO) {
		return new Intervention(
			null,
			interventionDTO.id(),
			interventionDTO.dateStart(),
			interventionDTO.hourStart(),
			interventionDTO.hourEnd(),
			interventionDTO.descShort(),
			interventionDTO.descLong()
		);
	}

}
