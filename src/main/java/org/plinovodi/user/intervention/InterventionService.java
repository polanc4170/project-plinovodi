package org.plinovodi.user.intervention;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterventionService {

	private final InterventionRepository interventionRepository;
	private final InterventionMapper     interventionMapper;

	public InterventionDTO postIntervention (InterventionDTO interventionDTO) {
		if (interventionRepository.hasByUUID(interventionDTO.uuid())) {
			throw new RuntimeException(String.format(
				"The intervention uuid [%s] already exists.",
				interventionDTO.uuid()
			));
		}

		int hourStart = interventionDTO.hourStart();
		int hourEnd   = interventionDTO.hourEnd();

		if (hourEnd < hourStart) throw new RuntimeException("");
		if (hourEnd   > 24)      throw new RuntimeException("");
		if (hourStart < 0)       throw new RuntimeException("");

		interventionRepository.save(
			interventionMapper.toIntervention(interventionDTO)
		);

		return interventionDTO;
	}

	public List<InterventionDTO> postInterventions (List<InterventionDTO> interventionDTOs) {
		interventionDTOs.forEach(this::postIntervention);

		return interventionDTOs;
	}

	public List <InterventionDTO> getInterventions () {
		return interventionRepository
			.findAll()
			.stream()
			.map(interventionMapper::toInterventionDTO)
			.toList();
	}

}
