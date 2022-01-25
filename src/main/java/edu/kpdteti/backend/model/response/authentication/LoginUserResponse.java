package edu.kpdteti.backend.model.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserResponse {

    private String userId;
    private String userName;
    private Boolean isAdmin;
    private String token;

}
