package org.plinovodi.date.holiday;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "date/holiday")
public class HolidayController {

	private final HolidayService holidayService;

	@GetMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity <?> getHoliday () {
		try {
			return ResponseEntity.status(OK).body(
				holidayService.getHolidays()
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
