package vertex.pro.edu.soung_box_app.entity.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
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
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
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
