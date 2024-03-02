package org.plinovodi.user.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("SELECT x FROM Report x WHERE x.uuid = ?1")
	Optional<Report> findByUUID (String uuid);

	@Query("SELECT COUNT(x) > 0 FROM Report x WHERE x.uuid = ?1")
	boolean hasUUID (String uuid);

	@Query("SELECT x FROM Report x WHERE x.firstName = ?1 AND x.lastName = ?2")
	List<Report> findByName (String firstName, String lastName);

	@Query("SELECT COUNT(x) > 0 FROM Report x WHERE x.firstName = ?1 AND x.lastName = ?2")
	boolean hasUserName (String firstName, String lastName);

}
