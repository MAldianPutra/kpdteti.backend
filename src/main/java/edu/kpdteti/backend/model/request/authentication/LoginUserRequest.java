package edu.kpdteti.backend.model.request.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {

    private String userEmail;
    private String userPassword;

}
