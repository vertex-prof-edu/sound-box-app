package vertex.pro.edu.soung_box_app.service.user.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.InvalidLoginOrPasswordException;
import vertex.pro.edu.soung_box_app.exception.UserAlreadyExistException;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationToken;
import vertex.pro.edu.soung_box_app.security.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@Component
@RequiredArgsConstructor
public class UserCrudService {

    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String save(UserEntity user) throws UserAlreadyExistException {
        UserEntity userUsername = userRepository.findByUsername(user.getUsername());
        Optional<UserEntity> usernameEmail = userRepository.findByEmail(user.getEmail());

        if (userUsername != null | usernameEmail.isPresent()) {
            throw new UserAlreadyExistException(USER_EXIST_MSG);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public UserEntity findByUsername(String login) throws UserDoesntExistException {
        if (login.isEmpty()) {
            throw new UserDoesntExistException(USER_NOT_FOUND_MSG);
        }
        return userRepository.findByUsername(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) throws UserDoesntExistException,
            InvalidLoginOrPasswordException {

        UserEntity userEntity = findByUsername(login);
        if (userEntity != null) {
            if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        } else {
            throw new InvalidLoginOrPasswordException(INVALID_LOGIN_OR_PASSWORD_MSG);
        }
        return null;
    }

    public void enableUser(String username) {
        userRepository.enableUser(username);
    }

    private final static String USER_EXIST_MSG = "A username or email  has already been created";
    private final static String USER_NOT_FOUND_MSG = "User with this username doesn't exist";
    private final static String INVALID_LOGIN_OR_PASSWORD_MSG = "invalid login or password";
}
