package edu.kpdteti.backend.service;

import edu.kpdteti.backend.model.response.user.GetUserResponse;

public interface UserService {

    GetUserResponse getUser(Long userId);

}
