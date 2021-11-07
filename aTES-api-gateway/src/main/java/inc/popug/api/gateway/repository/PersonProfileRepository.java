package inc.popug.api.gateway.repository;

import inc.popug.api.gateway.model.PersonProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface PersonProfileRepository extends JpaRepository<PersonProfileEntity,Long> {
}
