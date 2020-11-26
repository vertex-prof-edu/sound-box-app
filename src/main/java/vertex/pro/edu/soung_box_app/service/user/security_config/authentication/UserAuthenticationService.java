package vertex.pro.edu.soung_box_app.service.user.security_config.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.util.Optional;

//@Configuration
public interface UserAuthenticationService {

    Optional<String> login(@Param("username") String username, @Param("password") String password);

    Optional<User> findByToken(String token);

    void logout(User user);

}
