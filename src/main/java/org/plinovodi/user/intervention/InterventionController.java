package org.plinovodi.user.intervention;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("user/intervention")
public class InterventionController {

	private final InterventionService interventionService;

	@GetMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity<?> getInterventions () {
		try {
			return ResponseEntity.status(OK).body(
				interventionService.getInterventions()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
