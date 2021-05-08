package vertex.pro.edu.soung_box_app.service.crud;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.UserException;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationTokenEntity;
import vertex.pro.edu.soung_box_app.security.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class UserCrudService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Transactional
    public String save(UserEntity user) {
        UserEntity userUsername = userRepository.findByUsername(user.getUsername());
        Optional<UserEntity> usernameEmail = userRepository.findByEmail(user.getEmail());

        if (userUsername != null | usernameEmail.isPresent()) {
            throw new UserException(USER_EXIST_MSG);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);

        return token;
    }

    public UserEntity findByUsername(String login) {
        if (login.isEmpty()) {
            throw new UserException(USER_NOT_FOUND_MSG);
        }
        return userRepository.findByUsername(login);
    }

    public String login(String username, String password) {

        UserEntity user = userRepository.findByUsername(username);

        boolean identicalPasswords = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (username != null & identicalPasswords) {
            return "you are logged in";
        } else {
            throw new IllegalArgumentException(INVALID_LOGIN_OR_PASSWORD_MSG);
        }
    }

    public void enableUser(String username) {
        userRepository.enableUser(username);
    }

    private final static String USER_EXIST_MSG = "A username or email has already been created";
    private final static String USER_NOT_FOUND_MSG = "User with this username doesn't exist";
    private final static String INVALID_LOGIN_OR_PASSWORD_MSG = "invalid login or password";
}
