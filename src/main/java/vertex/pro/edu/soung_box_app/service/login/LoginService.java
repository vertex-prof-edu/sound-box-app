package vertex.pro.edu.soung_box_app.service.login;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.exception.InvalidLoginOrPasswordException;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Object login(String username, String password) throws InvalidLoginOrPasswordException {
        User user = userRepository.findUser(username);

        boolean identicalPasswords = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (username != null & identicalPasswords) {
            return "you are logged in";
        } else {
            throw new InvalidLoginOrPasswordException(USER_EXIST_MSG);
        }
    }

    private final static String USER_EXIST_MSG = "invalid login or password";
}
