package org.plinovodi.date.holiday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

	@Query("SELECT COUNT(x) > 0 FROM Holiday x WHERE x.localDate = ?1")
	boolean hasHolidayForDate (LocalDate date);

}
