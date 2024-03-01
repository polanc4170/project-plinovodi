package org.plinovodi.user.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

	@Query("SELECT x FROM Form x WHERE x.uuid = ?1")
	Optional <Form> findByUUID (String uuid);

	@Query("SELECT COUNT(x) > 0 FROM Form x WHERE x.uuid = ?1")
	boolean hasUUID (String uuid);

	@Query("SELECT x FROM Form x WHERE x.firstName = ?1 AND x.lastName = ?2")
	List <Form> findByName (String firstName, String lastName);

	@Query("SELECT COUNT(x) > 0 FROM Form x WHERE x.firstName = ?1 AND x.lastName = ?2")
	boolean hasUserName (String firstName, String lastName);

}
