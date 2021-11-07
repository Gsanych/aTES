package inc.popug.api.gateway.repository;

import inc.popug.api.gateway.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserNameAndStatus(String userName, String status);

    UserEntity findByUserName(String userName);
}
