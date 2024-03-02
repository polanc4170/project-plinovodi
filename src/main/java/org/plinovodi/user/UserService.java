package org.plinovodi.user;

import org.plinovodi.date.DateService;
import org.plinovodi.date.DateType;
import org.plinovodi.user.intervention.InterventionDTO;
import org.plinovodi.user.report.ReportDTO;
import org.plinovodi.user.report.ReportService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Service
public class UserService {

	private static final double WEEKDAY_FACTOR = 0.1d;
	private static final double WEEKEND_FACTOR = 0.2d;
	private static final double HOLIDAY_FACTOR = 0.2d;

	private static final int WEEKDAY_HOUR = 16;
	private static final int WEEKEND_HOUR = 24;
	private static final int HOLIDAY_HOUR = 24;

	private static final int HOUR_WORK_START = 7;
	private static final int HOUR_WORK_END   = 15;

	private final ReportService reportService;
	private final DateService   dateService;

	private Map<String, Object> getOnCallHoursFor (ReportDTO reportDTO) {
		LocalDate localDate = reportDTO.dateStart();

		int weekdays = 0;
		int weekends = 0;
		int holidays = 0;

		for (int i = 0; i < 7; i = i + 1) {
			switch(dateService.getDateType(localDate.plusDays(i))) {
				case HOLIDAY -> holidays = holidays + 1;
				case WEEKEND -> weekends = weekends + 1;
				case WEEKDAY -> weekdays = weekdays + 1;
				default -> throw new IllegalStateException();
			}
		}

		int hourOvertime = 0;
		int hourWeekends = 0;
		int hourHolidays = 0;

		for (InterventionDTO interventionDTO : reportDTO.interventionList()) {
			DateType dateType = dateService.getDateType(
				interventionDTO.dateStart()
			);

			int hourStart    = interventionDTO.hourStart();
			int hourEnd      = interventionDTO.hourEnd();
			int hourDuration = hourEnd - hourStart;

			switch (dateType) {
				case HOLIDAY -> hourHolidays = hourHolidays + hourDuration;
				case WEEKEND -> hourWeekends = hourWeekends + hourDuration;
				case WEEKDAY -> {
					int fixHourEnd   = Math.min(HOUR_WORK_START, hourEnd);
					int fixHourStart = Math.max(HOUR_WORK_END,   hourStart);

					int before = Math.max(0, fixHourEnd - hourStart);
					int after  = Math.max(0, hourEnd - fixHourStart);

					hourOvertime = hourOvertime + before + after;
				}
				default -> throw new IllegalStateException();
			}
		}

		int weekdayHours = WEEKDAY_HOUR * weekdays;
		int weekendHours = WEEKEND_HOUR * weekends;
		int holidayHours = HOLIDAY_HOUR * holidays;

		double weekday = WEEKDAY_FACTOR * (weekdayHours - hourOvertime) + hourOvertime;
		double weekend = WEEKEND_FACTOR * (weekendHours - hourWeekends) + hourWeekends;
		double holiday = HOLIDAY_FACTOR * (holidayHours - hourHolidays) + hourHolidays;

		Map<String, Object> map = new TreeMap<>();

		map.put("weekday-hour-intervention", hourOvertime);
		map.put("weekday-hour-total",        weekdayHours);
		map.put("weekday-value",             weekday);
		map.put("weekend-hour-total",        weekendHours);
		map.put("weekend-hour-intervention", hourWeekends);
		map.put("weekend-value",             weekend);
		map.put("holiday-hour-total",        holidayHours);
		map.put("holiday-hour-intervention", hourHolidays);
		map.put("holiday-value",             holiday);
		map.put("user-uuid",                 reportDTO.uuid());
		map.put("user-value",                weekday + weekend + holiday);

		return map;
	}

	public List<Map<String, Object>> getOnCallHoursForAll () {
		return reportService
			.getReports()
			.stream()
			.map(this::getOnCallHoursFor)
			.toList();
	}

	public Map<String, Object> getOnCallHoursFor (String uuid) {
		return getOnCallHoursFor(reportService.getReport(uuid));
	}

}
