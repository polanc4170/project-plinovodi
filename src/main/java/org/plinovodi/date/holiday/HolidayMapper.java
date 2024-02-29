package org.plinovodi.date.holiday;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class HolidayMapper {

	public HolidayDTO toHolidayDTO (Holiday holiday) {
		return new HolidayDTO(
			holiday.getLocalDate(),
			holiday.getName()
		);
	}

	public Holiday toHoliday (HolidayDTO holidayDTO) {
		return new Holiday(
			holidayDTO.localDate(),
			holidayDTO.name()
		);
	}
}
