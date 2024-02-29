package org.plinovodi;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HolidayMapper {

	public HolidayDTO toDTO (Holiday holiday) {
		return new HolidayDTO(holiday.getLocalDate(), holiday.getName());
	}

}
