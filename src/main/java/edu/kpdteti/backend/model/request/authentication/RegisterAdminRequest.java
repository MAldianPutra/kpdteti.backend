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
public class RegisterAdminRequest {

    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;

}
