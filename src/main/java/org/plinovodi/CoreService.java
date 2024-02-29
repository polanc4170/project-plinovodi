package org.plinovodi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@RequiredArgsConstructor
@Service
public class CoreService {

	private static final String WEEKDAY = "weekday";
	private static final String WEEKEND = "weekend";
	private static final String HOLIDAY = "holiday";

	private final HolidayRepository holidayRepository;
	private final HolidayMapper     holidayMapper;

	public List<HolidayDTO> getAllHolidays () {
		return holidayRepository
			.findAll()
			.stream()
			.map(holidayMapper::toDTO)
			.toList();
	}

	public String getDayType (LocalDate date) {
		if (date == null) {
			throw new DateTimeException("The date is null");
		}

		if (holidayRepository.hasHolidayForDate(date)) {
			return HOLIDAY;
		}

		DayOfWeek day = date.getDayOfWeek();

		if (day == SATURDAY || day == SUNDAY) {
			return WEEKEND;
		}

		return WEEKDAY;
	}

}
