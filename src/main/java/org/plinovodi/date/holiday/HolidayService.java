package org.plinovodi.date.holiday;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HolidayService {

	private final HolidayRepository holidayRepository;

	public List<Holiday> getHolidays () {
		return holidayRepository.findAll();
	}

	public boolean hasHolidayForDate (LocalDate localDate) {
		return holidayRepository.hasHolidayForDate(localDate);
	}

}
