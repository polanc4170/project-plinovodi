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
		if (interventionRepository.hasByUUID(interventionDTO.id())) {
			throw new RuntimeException(String.format(
				"The intervention id [%s] already exists.",
				interventionDTO.id()
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
		return interventionDTOs
			.stream()
			.map(this::postIntervention)
			.toList();
	}

	public List <InterventionDTO> getInterventions () {
		return interventionRepository
			.findAll()
			.stream()
			.map(interventionMapper::toInterventionDTO)
			.toList();
	}

}
