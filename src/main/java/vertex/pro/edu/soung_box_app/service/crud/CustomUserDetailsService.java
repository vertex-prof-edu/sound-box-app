package vertex.pro.edu.soung_box_app.service.crud;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;
import vertex.pro.edu.soung_box_app.entity.user.model.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.UserNotAnArtistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;

import java.util.Objects;

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

    public UserEntity getCurrent() throws Exception {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userService.findByUsername(loggedUser);

        if (user.getEnabled() == null) {
            throw new UserNotConfirmedException(USER_NOT_CONFIRMED_EXC);
        } else {
            return user;
        }
    }

    public UserEntity getCurrentNotConfirmedUser() throws Exception {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.findByUsername(loggedUser);
    }

    private final static String USER_NOT_CONFIRMED_EXC = "user didn't confirmed yet";
}
