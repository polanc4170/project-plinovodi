package org.plinovodi.user.form;

import org.plinovodi.user.intervention.InterventionMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FormMapper {

	private final InterventionMapper interventionMapper;

	public FormDTO toFormDTO (Form userForm) {
		return new FormDTO(
			userForm.getUuid(),
			userForm.getFirstName(),
			userForm.getLastName(),
			userForm.getLocalDate(),
			userForm.getInterventionList()
				.stream()
				.map(interventionMapper::toInterventionDTO)
				.toList()
		);
	}

	public Form toForm (FormDTO formDTO) {
		return new Form(
			null,
			formDTO.uuid(),
			formDTO.firstName(),
			formDTO.lastName(),
			formDTO.localData(),
			formDTO.interventionList()
				.stream()
				.map(interventionMapper::toIntervention)
				.toList()
		);
	}

}
