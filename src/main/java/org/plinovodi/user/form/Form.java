package org.plinovodi.user.form;

import org.plinovodi.user.intervention.Intervention;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_form")
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@Column(nullable = false, unique = true, length = 64)
	private String uuid;

	@Column(nullable = false, length = 32)
	private String firstName;

	@Column(nullable = false, length = 32)
	private String lastName;

	@Column(nullable = false)
	private LocalDate localDate;

	@OneToMany
	@JoinTable(
		name               = "user_interventions",
		joinColumns        = {@JoinColumn(name = "form_id")},
		inverseJoinColumns = {@JoinColumn(name = "intervention_id")}
	)
	private List<Intervention> interventionList = new ArrayList<>();

}
