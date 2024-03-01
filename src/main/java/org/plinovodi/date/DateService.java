package org.plinovodi.date;

import org.plinovodi.date.holiday.HolidayService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.plinovodi.date.DateType.HOLIDAY;
import static org.plinovodi.date.DateType.WEEKDAY;
import static org.plinovodi.date.DateType.WEEKEND;

@RequiredArgsConstructor
@Service
public class DateService {

	private final HolidayService holidayService;

	public DateType getDateType (LocalDate date) {
		if (holidayService.hasHolidayForDate(date)) {
			return HOLIDAY;
		}

		DayOfWeek day = date.getDayOfWeek();

		if (day == SATURDAY || day == SUNDAY) {
			return WEEKEND;
		}

		return WEEKDAY;
	}

}
