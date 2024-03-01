package org.plinovodi.user.form;

import org.springframework.stereotype.Component;

@Component
public class FormMapping {

	public FormDTO toFormDTO (Form userForm) {
		return new FormDTO(
			userForm.getUuid(),
			userForm.getFirstName(),
			userForm.getLastName(),
			userForm.getLocalDate(),
			null
		);
	}

	public Form toForm (FormDTO formDTO) {
		return new Form(
			null,
			formDTO.uuid(),
			formDTO.firstName(),
			formDTO.lastName(),
			formDTO.localData(),
			null
		);
	}

}
