package org.plinovodi.date;

import org.plinovodi.date.holiday.HolidayService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.plinovodi.date.DateType.HOLIDAY;
import static org.plinovodi.date.DateType.WEEKDAY;
import static org.plinovodi.date.DateType.WEEKEND;

@RequiredArgsConstructor
@Service
public class DateService {

	private final HolidayService holidayService;

	public String getDateType (LocalDate date) {
		if (holidayService.hasHolidayForDate(date)) {
			return HOLIDAY.label;
		}

		DayOfWeek day = date.getDayOfWeek();

		if (day == SATURDAY || day == SUNDAY) {
			return WEEKEND.label;
		}

		return WEEKDAY.label;
	}

}
