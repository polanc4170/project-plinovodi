package org.plinovodi.user.report;

import org.plinovodi.user.intervention.Intervention;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
	name = "user_report",
	indexes = {
		@Index(name = "IDX_report_id",        columnList = "id"),
		@Index(name = "IDX_report_full_name", columnList = "firstName, lastName"),
		@Index(name = "IDX_report_date",      columnList = "dateStart")
	}
)
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primary_key;

	@Column(nullable = false, unique = true, length = 64)
	private String id;

	@Column(nullable = false, length = 32)
	private String firstName;

	@Column(nullable = false, length = 32)
	private String lastName;

	@Column(nullable = false)
	private LocalDate dateStart;

	@OneToMany(
		fetch   = FetchType.EAGER,
		cascade = CascadeType.ALL
	)
	@JoinTable(
		name               = "user_interventions",
		joinColumns        = {@JoinColumn(name = "id_report")},
		inverseJoinColumns = {@JoinColumn(name = "id_intervention")}
	)
	private List<Intervention> interventions;

}
