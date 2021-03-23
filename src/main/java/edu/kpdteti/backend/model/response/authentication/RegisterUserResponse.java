package edu.kpdteti.backend.model.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {

    private Long userId;
    private String userName;
    private String userEmail;

}
