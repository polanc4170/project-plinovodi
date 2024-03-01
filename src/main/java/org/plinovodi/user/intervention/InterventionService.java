package org.plinovodi.user.intervention;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InterventionService {

	private final InterventionRepository interventionRepository;

	public Intervention postIntervention (Intervention intervention) {
		if (interventionRepository.hasByUUID(intervention.getUuid())) {
			throw new RuntimeException(String.format(
				"The intervention uuid [%s] already exists.",
				intervention.getUuid()
			));
		}

		return interventionRepository.save(intervention);
	}

	public void postInterventions (Iterable<Intervention> interventions) {
		interventions.forEach(this::postIntervention);
	}

}
