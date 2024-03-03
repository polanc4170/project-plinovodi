package org.plinovodi.date.holiday;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
name = "holiday",
	indexes = {
		@Index(name = "IDX_holiday_date", columnList = "localDate"),
		@Index(name = "IDX_holiday_name", columnList = "name")
	}
)
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primary_key;

	@Column(nullable = false, unique = false)
	private LocalDate localDate;

	@Column(nullable = false, unique = false, length = 32)
	private String name;

	public Holiday (LocalDate localDate, String name) {
		setLocalDate(localDate);
		setName(name);
	}

}
