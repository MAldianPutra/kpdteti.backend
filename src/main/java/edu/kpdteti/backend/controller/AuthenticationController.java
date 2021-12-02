package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.request.authentication.LoginUserRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterAdminRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterUserRequest;
import edu.kpdteti.backend.model.response.authentication.LoginUserResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterAdminResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterUserResponse;
import edu.kpdteti.backend.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<LoginUserResponse> loginUser(@Valid @RequestBody LoginUserRequest request) {
        System.out.println(request.getUserEmail());
        System.out.println(request.getUserPassword());
        return new ResponseEntity<>(authenticationService.loginUser(request), HttpStatus.OK);
    }

    @PostMapping(ApiPath.REGISTER)
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return new ResponseEntity<>(authenticationService.registerUser(request), HttpStatus.OK);
    }

    @PostMapping(ApiPath.REGISTER_ADMIN)
    public ResponseEntity<RegisterAdminResponse> registerAdmin(@Valid @RequestBody RegisterAdminRequest request) {
        return new ResponseEntity<>(authenticationService.registerAdmin(request), HttpStatus.OK);
    }
}
