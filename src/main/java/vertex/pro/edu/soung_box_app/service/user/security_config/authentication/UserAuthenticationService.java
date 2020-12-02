package vertex.pro.edu.soung_box_app.service.user.security_config.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.util.Optional;

@Service
public interface UserAuthenticationService {

    Optional<String> login(@Param("username") String username, @Param("password") String password);

    Optional<User> findByToken(String token);

    void logout(User user);

}
