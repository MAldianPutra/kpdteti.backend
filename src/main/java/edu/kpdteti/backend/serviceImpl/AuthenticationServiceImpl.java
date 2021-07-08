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
import edu.kpdteti.backend.util.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.idGenerator = idGenerator;
    }

    @Autowired
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        return null;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = User.builder()
                .userId(idGenerator.generateId(IdGeneratorEnum.USER))
                .userRoleEnum(UserRoleEnum.ROLE_USER)
                .userCreatedAt(LocalDateTime.now())
                .userLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, user);
        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        BeanUtils.copyProperties(savedUser, response);
        return response;
    }

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest request) {
        User user = User.builder()
                .userId(idGenerator.generateId(IdGeneratorEnum.ADMIN))
                .userRoleEnum(UserRoleEnum.ROLE_ADMIN)
                .userCreatedAt(LocalDateTime.now())
                .userLastUpdated(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(request, user);
        User savedUser = userRepository.save(user);
        RegisterAdminResponse response = new RegisterAdminResponse();
        BeanUtils.copyProperties(savedUser, response);
        return response;
    }
}
