package org.plinovodi.date;

public enum DateType {

	HOLIDAY("holiday"),
	WEEKDAY("weekday"),
	WEEKEND("weekend");

	public final String label;

	DateType (String label) {
		this.label = label;
	}

	@Override
	public String toString () {
		return label;
	}

}
