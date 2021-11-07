package inc.popug.api.gateway.service;

import inc.popug.api.gateway.constraint.UserStatus;
import inc.popug.api.gateway.dto.UserSignupDTO;
import inc.popug.api.gateway.mapper.MapperUserService;
import inc.popug.api.gateway.model.PersonProfileEntity;
import inc.popug.api.gateway.model.UserEntity;
import inc.popug.api.gateway.model.UserRoleEntity;
import inc.popug.api.gateway.repository.UserRepository;
import inc.popug.api.gateway.util.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperUserService mapperUserService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername() method call...");
        boolean isAccountNonLocked = true;
        UserEntity userEntity = userRepository.findByUserNameAndStatus(userName, UserStatus.ACTIVE.getStatus());
        if (userEntity == null) {
            LOGGER.error("User not found or user deactivated.");
            throw new RuntimeException("User not found or account deactivated.");
        }
//        Boolean isNonVerified = customerEmailVerificationRepository.existsByUserNameAndCustomer_CustomeridAndEmailVerification(user.getUserName(), user.getCustomer().getCustomerid(), 0);
//        if (isNonVerified) {
//            LOGGER.error("Sign up complete but, Email is not verified");
//            throw new RuntimeException("Sign up complete but, Email is not verified");
//        }
        //This section for account lock or unlocked.
//        Integer result = loginAttemptAuditService.countLoginAttmptByDate(user.getUserName());
//        if (result != null) {
//            if (result >= invalidLoginAttempt) {
//                isAccountNonLocked = false;
//            }
//        } else {
//            System.out.println("Some thing wrong in user information.");
//        }
        return new UserDetail(userEntity, isAccountNonLocked);
    }

    @Override
    public String signup(UserSignupDTO userSignupDTO) {
        LOGGER.info("signup() method call...");
        try {

            //Map Into Person Entity
            PersonProfileEntity profileEntity = mapperUserService.mapPersonFromDTO(userSignupDTO);

            //Find Patient ROLE
            UserRoleEntity roleEntity = roleService.getAllRoles().stream().filter(r -> r.getRoleName().equalsIgnoreCase(UserStatus.PATIENT.getStatus())).findAny().orElse(null);
            if (null == roleEntity || roleEntity.getStatus().equalsIgnoreCase(UserStatus.INACTIVE.getStatus())) {
                throw new RuntimeException("Signup Request can't process");
            }
            //Finally User Signup
            UserEntity userEntity = mapperUserService.mapUserFromDTO(userSignupDTO);
            userEntity.setUserRoleEntity(roleEntity);
            userEntity.setPersonProfileEntity(profileEntity);
            UserEntity userAfterSave = userRepository.save(userEntity);
            if (userAfterSave != null && userAfterSave.getUserId() != null) {
                return HttpStatus.OK.name();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return HttpStatus.EXPECTATION_FAILED.name();
        }
        return null;
    }

    @Override
    public UserEntity findByUserName(String userName) {
        LOGGER.info("signup() method call...");
        return userRepository.findByUserName(userName);
    }
}
