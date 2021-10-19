package edu.kpdteti.backend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private String id;

    private String userName;

    @JsonIgnore
    private String userEmail;

    @JsonIgnore
    private String userPassword;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<UserRoleEnum> userRoleEnumList = new ArrayList<>();
        userRoleEnumList.add(user.getUserRoleEnum());
        List<GrantedAuthority> authorities = userRoleEnumList.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getUserRole()))
                .collect(Collectors.toList());
        return new UserPrincipal(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPassword(),
                authorities
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
