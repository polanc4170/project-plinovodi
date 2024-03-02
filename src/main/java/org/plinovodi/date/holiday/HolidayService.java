package org.plinovodi.date.holiday;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HolidayService {

	private final HolidayRepository holidayRepository;
	private final HolidayMapper     holidayMapper;

	public List<HolidayDTO> getHolidays () {
		return holidayRepository
			.findAll()
			.stream()
			.map(holidayMapper::toDTO)
			.toList();
	}

	public boolean hasHolidayForDate (LocalDate localDate) {
		return holidayRepository.hasHolidayForDate(localDate);
	}

}
