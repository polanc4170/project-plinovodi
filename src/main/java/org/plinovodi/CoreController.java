package org.plinovodi;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "")
public class CoreController {

	private final CoreService service;

	@GetMapping(path = "holiday")
	public ResponseEntity<?> getAllHolidays () {
		try {
			return ResponseEntity.status(OK).body(
				service.getAllHolidays()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				"Unhandled exception."
			);
		}
	}

	@GetMapping(path = "query")
	public ResponseEntity<?> getQuery (
		@RequestParam(name = "date", required = false)
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
		LocalDate date
	) {
		try {
			return ResponseEntity.status(OK).body(
				service.getDayType(date)
			);
		}
		catch (DateTimeException exception) {
			return ResponseEntity.status(BAD_REQUEST).body(
				"No query found : use [/?date=yyyy-mm-dd] to check for day type."
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				"Unhandled exception."
			);
		}
	}

}
