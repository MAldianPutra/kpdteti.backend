package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.request.user.GetUserRequest;
import edu.kpdteti.backend.model.response.user.GetUserResponse;

public interface UserService {

    GetUserResponse getUser(GetUserRequest request);

}
