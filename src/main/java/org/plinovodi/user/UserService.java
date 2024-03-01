package org.plinovodi.user;

import org.plinovodi.date.DateService;
import org.plinovodi.date.DateType;
import org.plinovodi.user.form.FormDTO;
import org.plinovodi.user.form.FormService;
import org.plinovodi.user.intervention.InterventionDTO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

	private final FormService formService;
	private final DateService dateService;

	private Map<String, Object> getOnCallHoursFor (FormDTO formDTO) {
		LocalDate localDate = formDTO.localDate();

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

		for (InterventionDTO interventionDTO : formDTO.interventionList()) {
			DateType dateType = dateService.getDateType(
				interventionDTO.localDate()
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

		return Map.of(
//			"weekday-hour-intervention", (double) hourOvertime,
//			"weekday-hour-total",        (double) weekdayHours,
//			"weekday-value",             weekday,
//			"weekend-hour-total",        (double) weekendHours,
//			"weekend-hour-intervention", (double) hourWeekends,
//			"weekend-value",             weekend,
//			"holiday-hour-total",        (double) holidayHours,
//			"holiday-hour-intervention", (double) hourHolidays,
//			"holiday-value",             holiday,
			"user-uuid",                 formDTO.uuid(),
			"user-value",                weekday + weekend + holiday
		);
	}

	public List <Map<String, Object>> getOnCallHoursForAll () {
		return formService
			.getForms()
			.stream()
			.map(this::getOnCallHoursFor)
			.toList();
	}

	public Map<String, Object> getOnCallHoursFor (String uuid) {
		return getOnCallHoursFor(formService.getForm(uuid));
	}

}
