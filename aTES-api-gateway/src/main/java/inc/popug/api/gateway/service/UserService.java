package inc.popug.api.gateway.service;

import inc.popug.api.gateway.dto.UserSignupDTO;
import inc.popug.api.gateway.model.UserEntity;

/**
 * @Author : 6/23/2020
 */
public interface UserService {

    String signup(UserSignupDTO userSignupDTO);

    UserEntity findByUserName(String userName);
}
