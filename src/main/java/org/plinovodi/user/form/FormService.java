package org.plinovodi.user.form;

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
public class FormService {

	private final InterventionService interventionService;
	private final InterventionMapper interventionMapper;
	private final FormRepository      formRepository;
	private final FormMapper formMapper;

	public FormDTO postForm (FormDTO formDTO) {
		Optional<Form> dbOptUserForm = formRepository.findByUUID(formDTO.uuid());

		if (dbOptUserForm.isPresent()) {
			throw new RuntimeException("");
		}

		formRepository.save(formMapper.toForm(formDTO));

		return formDTO;
	}

	public List<FormDTO> getForms () {
		return formRepository.findAll()
			.stream()
			.map(formMapper::toFormDTO)
			.toList();
	}

	public FormDTO getForm (String uuid) {
		Optional<Form> dbOptUserForm = formRepository.findByUUID(uuid);

		if (dbOptUserForm.isEmpty()) {
			throw new RuntimeException("");
		}

		return formMapper.toFormDTO(
			dbOptUserForm.get()
		);
	}

	public FormDTO putForm (String uuid, FormDTO formDTO) {
		Optional<Form> dbOptUserForm = formRepository.findByUUID(uuid);

		if (dbOptUserForm.isEmpty()) {
			throw new RuntimeException(String.format(
				"The form with uuid [%s] does not exist.",
				uuid
			));
		}

		Form dbUserForm = dbOptUserForm.get();

		String firstName = formDTO.firstName();
		String lastName  = formDTO.lastName();
		List<InterventionDTO> interventionList = formDTO.interventionList();

		if (firstName != null && !firstName.equalsIgnoreCase(dbUserForm.getFirstName())) {
			throw new RuntimeException(String.format(
				"The first name in form [%s] and db [%s] do not match.",
				firstName, dbUserForm.getFirstName()
			));
		}

		if (lastName != null && !lastName.equalsIgnoreCase(dbUserForm.getLastName())) {
			throw new RuntimeException(String.format(
				"The last name in form [%s] and db [%s] do not match.",
				lastName, dbUserForm.getLastName()
			));
		}

		if (interventionList != null && !interventionList.isEmpty()) {
			List<Intervention> interventions = new ArrayList<>(
				dbUserForm.getInterventionList()
			);

			List<Intervention> formInterventions = interventionList
				.stream()
				.map(interventionMapper::toIntervention)
				.toList();

			interventions.addAll(formInterventions);
			dbUserForm.setInterventionList(interventions);

			interventionService.postInterventions(interventionList);
			formRepository.save(dbUserForm);
		}

		return formMapper.toFormDTO(dbUserForm);
	}

}
