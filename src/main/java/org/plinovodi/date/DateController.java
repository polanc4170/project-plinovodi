package org.plinovodi.date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "date")
public class DateController {

	private final DateService dateService;

	@GetMapping(
		path     = "/{date}",
		produces = "application/json"
	)
	public ResponseEntity<?> getDateType (
		@PathVariable
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
		LocalDate date
	) {
		try {
			return ResponseEntity.status(OK).body(
				dateService.getDateType(date)
			);
		}
		catch (DateTimeException exception) {
			return ResponseEntity.status(BAD_REQUEST).body(
				exception.getMessage()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
