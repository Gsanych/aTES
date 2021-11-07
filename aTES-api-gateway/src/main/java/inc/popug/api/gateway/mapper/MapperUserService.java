package inc.popug.api.gateway.mapper;

import inc.popug.api.gateway.constraint.UserStatus;
import inc.popug.api.gateway.dto.UserSignupDTO;
import inc.popug.api.gateway.model.PersonProfileEntity;
import inc.popug.api.gateway.model.UserEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author :
 */
@Component
public class MapperUserService implements Serializable {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity mapUserFromDTO(UserSignupDTO userSignupDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userSignupDTO.getUserName());
        userEntity.setStatus(UserStatus.ACTIVE.getStatus());
        userEntity.setPassword(passwordEncoder.encode(userSignupDTO.getPassword()));
        userEntity.setCreateDate(LocalDateTime.now());
        return userEntity;
    }

    public PersonProfileEntity mapPersonFromDTO(UserSignupDTO userSignupDTO) {
        PersonProfileEntity profileEntity = new PersonProfileEntity();
        profileEntity.setFirstName(userSignupDTO.getFirstName());
        profileEntity.setMiddleName(userSignupDTO.getMiddleName());
        profileEntity.setLastName(userSignupDTO.getLastName());
        profileEntity.setDateOfBirth(userSignupDTO.getDateOfBirth());
        profileEntity.setEmail(userSignupDTO.getEmail());
        profileEntity.setEmployer(userSignupDTO.getEmployer());
        profileEntity.setCreateDate(LocalDateTime.now());
        return profileEntity;
    }
}
