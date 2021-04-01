package vertex.pro.edu.soung_box_app.entity.user.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;

import java.util.Collection;
import java.util.Collections;

@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static User fromUserEntityToCustomUserDetails(UserEntity userEntity) {
        User user = new User();
        user.username = userEntity.getUsername();
        user.email = userEntity.getEmail();
        user.password = userEntity.getPassword();
        user.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getUserRole().name()));
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
