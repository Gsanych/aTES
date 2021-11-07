package inc.popug.api.gateway.repository;

import inc.popug.api.gateway.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface RoleRepository extends JpaRepository<UserRoleEntity,Long> {
}
