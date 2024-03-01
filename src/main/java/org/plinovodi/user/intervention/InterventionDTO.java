package org.plinovodi.user.intervention;

import java.time.LocalDate;

public record InterventionDTO (
	String uuid,
	LocalDate localDate,
	Integer hourStart,
	Integer hourEnd,
	String descShort,
	String descLong
) {

}
