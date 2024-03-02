package org.plinovodi.user.report;

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
@RequestMapping("user/report")
public class ReportController {

	private final ReportService reportService;

	@PostMapping(
		path     = "",
		produces = "application/json"
	)
	public ResponseEntity<?> addReport (
		@RequestBody ReportDTO reportDTO
	) {
		try {
			return ResponseEntity.status(OK).body(
				reportService.addReport(reportDTO)
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
	public ResponseEntity<?> getReports () {
		try {
			return ResponseEntity.status(OK).body(
				reportService.getReports()
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
	public ResponseEntity<?> getReport (
		@PathVariable String userId
	) {
		try {
			return ResponseEntity.status(OK).body(
				reportService.getReport(userId)
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
	public ResponseEntity<?> updateReport (
		@PathVariable String    uuid,
		@RequestBody  ReportDTO reportDTO
	) {
		try {
			return ResponseEntity.status(OK).body(
				reportService.updateReport(uuid, reportDTO)
			);
		}
		catch (Exception exception) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
				exception.getMessage()
			);
		}
	}

}
