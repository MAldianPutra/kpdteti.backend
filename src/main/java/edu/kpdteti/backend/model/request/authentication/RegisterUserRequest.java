package edu.kpdteti.backend.model.request.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;

}
