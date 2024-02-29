package org.plinovodi.date;

public enum DateType {

	HOLIDAY("holiday"),
	WEEKDAY("weekday"),
	WEEKEND("weekend");

	public final String label;

	private DateType (String label) {
		this.label = label;
	}

}
