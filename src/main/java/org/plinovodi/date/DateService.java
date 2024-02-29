package org.plinovodi.date;

import org.plinovodi.date.holiday.HolidayDTO;
import org.plinovodi.date.holiday.HolidayMapper;
import org.plinovodi.date.holiday.HolidayService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.plinovodi.date.DateType.HOLIDAY;
import static org.plinovodi.date.DateType.WEEKDAY;
import static org.plinovodi.date.DateType.WEEKEND;

@RequiredArgsConstructor
@Service
public class DateService {

	private final HolidayService holidayService;
	private final HolidayMapper  holidayMapper;

	public List<HolidayDTO> getAllHolidays () {
		return holidayService.getAllHolidays()
			.stream()
			.map(holidayMapper::toHolidayDTO)
			.toList();
	}

	public String getDateType (LocalDate date) {
		if (date == null) {
			throw new DateTimeException("The date is null");
		}

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
