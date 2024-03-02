package org.plinovodi.user.intervention;

import org.hibernate.annotations.NaturalId;

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
	name    = "user_intervention",
	indexes = {
		@Index(columnList = "uuid")
	}
)
public class Intervention {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@Column(nullable = false, unique = true, length = 64)
	private String uuid;

	@Column(nullable = false)
	private LocalDate dateStart;

	@Column(nullable = false)
	private Integer hourStart;

	@Column(nullable = false)
	private Integer hourEnd;

	@Column(nullable = false, length = 50)
	private String descShort;

	@Column(nullable = false, length = 500)
	private String descLong;

}
