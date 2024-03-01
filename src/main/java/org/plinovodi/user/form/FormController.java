package org.plinovodi.user.form;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("user/form")
public class FormController {

	private final FormService formService;

	@PostMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity<?> postForm (
		@RequestBody FormDTO formDTO
	) {
		try {
			return ResponseEntity.status(OK).body(
				formService.postForm(formDTO)
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

	@GetMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity<?> getForms () {
		try {
			return ResponseEntity.status(OK).body(
				formService.getForms()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

	@GetMapping(
		path     = "/{userId}",
		produces = "application/json"
	)
	public ResponseEntity<?> getForm (
		@PathVariable String userId
	) {
		try {
			return ResponseEntity.status(OK).body(
				formService.getForm(userId)
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

	@PutMapping(
		path     = "/{userId}",
		produces = "application/json"
	)
	public ResponseEntity<?> getForm (
		@PathVariable String uuid,
		@RequestBody FormDTO formDTO
	) {
		try {
			return ResponseEntity.status(OK).body(
				formService.putForm(uuid, formDTO)
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
