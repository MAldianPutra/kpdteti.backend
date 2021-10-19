package edu.kpdteti.backend.security;

import edu.kpdteti.backend.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleProvider {

    private String userId;
    private UserRoleEnum userRoleEnum;

}
