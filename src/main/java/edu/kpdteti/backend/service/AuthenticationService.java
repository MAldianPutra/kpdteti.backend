package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.authentication.LoginUserRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterAdminRequest;
import edu.kpdteti.backend.model.request.authentication.RegisterUserRequest;
import edu.kpdteti.backend.model.response.authentication.LoginUserResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterAdminResponse;
import edu.kpdteti.backend.model.response.authentication.RegisterUserResponse;

public interface AuthenticationService {

    LoginUserResponse loginUser(LoginUserRequest request);

    RegisterUserResponse registerUser(RegisterUserRequest request);

    RegisterAdminResponse registerAdmin(RegisterAdminRequest request);

}
