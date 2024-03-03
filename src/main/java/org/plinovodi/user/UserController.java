package org.plinovodi.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@GetMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity<?> getUserHours () {
		try {
			return ResponseEntity.status(OK).body(
				userService.getOnCallHoursForAll()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

	@GetMapping(
		path     = "{id}",
		produces = "application/json"
	)
	public ResponseEntity<?> getUserHour (@PathVariable String id) {
		try {
			return ResponseEntity.status(OK).body(
				userService.getOnCallHoursFor(id)
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
