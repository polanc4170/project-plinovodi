package org.plinovodi.date.holiday;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class HolidayMapper {

	public HolidayDTO toDTO (Holiday holiday) {
		return new HolidayDTO(
			holiday.getLocalDate(),
			holiday.getName()
		);
	}

	public Holiday toEntity (HolidayDTO holidayDTO) {
		return new Holiday(
			holidayDTO.localDate(),
			holidayDTO.name()
		);
	}
}
