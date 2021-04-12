package edu.kpdteti.backend.entity;

import edu.kpdteti.backend.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = User.COLLECTION_NAME)
public class User {

    public static final String COLLECTION_NAME = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @Field(value = USER_ID)
    private String userId;

    @Field(value = USER_NAME)
    private String userName;

    @Field(value = USER_EMAIL)
    private String userEmail;

    @Field(value = USER_PASSWORD)
    private String userPassword;

    @Field(value = USER_ROLE)
    private UserRoleEnum userRoleEnum;

    @Field(value = CREATED_AT)
    private LocalDateTime userCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime userLastUpdated;

}
