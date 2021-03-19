package vertex.pro.edu.soung_box_app.service.user.crud;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCrudService userService;

    @SneakyThrows
    @Override
    public User loadUserByUsername(String username) {
        UserEntity userEntity = userService.findByUsername(username);
        return User.fromUserEntityToCustomUserDetails(userEntity);
    }
}
