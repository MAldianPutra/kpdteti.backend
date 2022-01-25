package edu.kpdteti.backend.serviceImpl;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.enums.IdGeneratorEnum;
import edu.kpdteti.backend.enums.UserRoleEnum;
import edu.kpdteti.backend.model.request.authentication.LoginUserRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterAdminRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterUserRequest;
import edu.kpdteti.backend.model.response.authentication.LoginUserResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterAdminResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterUserResponse;
import edu.kpdteti.backend.repository.UserRepository;
import edu.kpdteti.backend.service.AuthenticationService;
import edu.kpdteti.backend.util.IdGeneratorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final IdGeneratorUtil idGeneratorUtil;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, IdGeneratorUtil idGeneratorUtil) {
        this.userRepository = userRepository;
        this.idGeneratorUtil = idGeneratorUtil;
    }

    @Autowired
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        User user = userRepository.findByUserEmail(request.getUserEmail());
        if(user == null) {
            throw new EntityNotFoundException("User not found with email " + request.getUserEmail());
        }
        LoginUserResponse response = LoginUserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .isAdmin(Boolean.FALSE)
                .token(UUID.randomUUID().toString())
                .build();
        if (user.getUserRoleEnum() == UserRoleEnum.ROLE_ADMIN) {
            response.setIsAdmin(Boolean.TRUE);
        }
        return response;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = User.builder()
                .userId(idGeneratorUtil.generateId(IdGeneratorEnum.USER))
                .userEmail(request.getUserEmail())
                .userName(request.getUserName())
                .userPassword(passwordEncoder().encode(request.getUserPassword()))
                .userRoleEnum(UserRoleEnum.ROLE_USER)
                .userCreatedAt(LocalDateTime.now())
                .userLastUpdated(LocalDateTime.now())
                .build();
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest request) {
        User user = User.builder()
                .userId(idGeneratorUtil.generateId(IdGeneratorEnum.ADMIN))
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder().encode(request.getUserPassword()))
                .userRoleEnum(UserRoleEnum.ROLE_ADMIN)
                .userCreatedAt(LocalDateTime.now())
                .userLastUpdated(LocalDateTime.now())
                .build();
        userRepository.save(user);
        RegisterAdminResponse response = new RegisterAdminResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
