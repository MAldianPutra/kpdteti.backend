package edu.kpdteti.backend.model.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminResponse {

    private String userId;
    private String userName;
    private String userEmail;

}
