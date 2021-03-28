package edu.kpdteti.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.kpdteti.backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = User.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.USER_ID),
        @UniqueConstraint(columnNames = User.USER_EMAIL)
})
public class User {

    public static final String TABLE_NAME = "users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = USER_ID)
    private Long userId;

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = USER_EMAIL)
    private String userEmail;

    @Column(name = USER_PASSWORD)
    private String userPassword;

    @Column(name = USER_ROLE)
    private UserRole userRole;

    @Column(name = CREATED_AT)
    private LocalDateTime userCreatedAt;

    @Column(name = LAST_UPDATED)
    private LocalDateTime userLastUpdated;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Publication> publications;

}
