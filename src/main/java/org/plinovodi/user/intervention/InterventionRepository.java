package org.plinovodi.user.intervention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {

	@Query("SELECT x FROM Intervention x WHERE x.dateStart = ?1")
	List<Intervention> findByDate (LocalDate dateStart);

	@Query("SELECT COUNT(x) > 0 FROM Intervention x WHERE x.dateStart = ?1")
	boolean hasByDate (LocalDate dateStart);

	@Query("SELECT x FROM Intervention x WHERE x.id = ?1")
	Optional<Intervention> findByID (String uuid);

	@Query("SELECT COUNT(x) FROM Intervention x WHERE x.id = ?1")
	boolean hasByUUID (String id);

}
