package org.plinovodi.user.intervention;

import org.springframework.stereotype.Component;

@Component
public class InterventionMapper {

	public InterventionDTO toInterventionDTO (Intervention intervention) {
		return new InterventionDTO(
			intervention.getUuid(),
			intervention.getLocalDate(),
			intervention.getHourStart(),
			intervention.getHourEnd(),
			intervention.getDescShort(),
			intervention.getDescLong()
		);
	}

	public Intervention toIntervention (InterventionDTO interventionDTO) {
		return new Intervention(
			null,
			interventionDTO.uuid(),
			interventionDTO.localDate(),
			interventionDTO.hourStart(),
			interventionDTO.hourEnd(),
			interventionDTO.descShort(),
			interventionDTO.descLong()
		);
	}

}
