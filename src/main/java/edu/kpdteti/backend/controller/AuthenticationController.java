package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.request.authentication.LoginUserRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterAdminRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterUserRequest;
import edu.kpdteti.backend.model.response.authentication.LoginUserResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterAdminResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterUserResponse;
import edu.kpdteti.backend.security.JwtTokenProvider;
import edu.kpdteti.backend.security.UserRoleProvider;
import edu.kpdteti.backend.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<LoginUserResponse> loginUser(@Valid @RequestBody LoginUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserEmail(),
                        request.getUserPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserRoleProvider userRoleProvider = tokenProvider.generateUserData(request.getUserEmail());
        LoginUserResponse response = LoginUserResponse.builder()
                .userId(userRoleProvider.getUserId())
                .userName(userRoleProvider.getUserName())
                .token(jwt)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiPath.REGISTER)
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return new ResponseEntity<>(authenticationService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping(ApiPath.REGISTER_ADMIN)
    public ResponseEntity<RegisterAdminResponse> registerAdmin(@Valid @RequestBody RegisterAdminRequest request) {
        return new ResponseEntity<>(authenticationService.registerAdmin(request), HttpStatus.CREATED);
    }
}
