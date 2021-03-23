package edu.kpdteti.backend.model.response.user;

import edu.kpdteti.backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private Long userId;
    private String userName;
    private UserRole userRole;

}
