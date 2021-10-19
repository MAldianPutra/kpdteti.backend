package edu.kpdteti.backend.security;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserDataProvider {

    private final UserRepository userRepository;

    @Autowired
    public UserDataProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRoleProvider provideUserData(String userEmail) {
        UserRoleProvider userRoleProvider = new UserRoleProvider();
        User user = userRepository.findByUserEmail(userEmail);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email " + userEmail);

        }
        BeanUtils.copyProperties(user, userRoleProvider);
        return userRoleProvider;
    }

}
