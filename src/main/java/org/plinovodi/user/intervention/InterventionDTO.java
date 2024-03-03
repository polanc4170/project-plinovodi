package org.plinovodi.user.intervention;

import java.time.LocalDate;

public record InterventionDTO (
	String id,
	LocalDate dateStart,
	Integer hourStart,
	Integer hourEnd,
	String descShort,
	String descLong
) {

}
