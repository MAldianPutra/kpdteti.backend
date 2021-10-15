package edu.kpdteti.backend.model.response.user;

import edu.kpdteti.backend.enums.UserRoleEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private String userId;
    private String userName;
    private String userEmail;
    private UserRoleEnum userRoleEnum;

}
