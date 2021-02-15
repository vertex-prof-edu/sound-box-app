package vertex.pro.edu.soung_box_app.service.user.crud;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.exception.UserAlreadyExistException;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.model.token.ConfirmationToken;
import vertex.pro.edu.soung_box_app.service.user.registration.security.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Component
@RequiredArgsConstructor
public class UserCrudService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String save(User user) throws UserAlreadyExistException {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (userExists) {
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

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    }

    public void enableUser(String username) {
        userRepository.enableUser(username);
    }

    private final static String USER_EXIST_MSG = "A user with this username has already been created";
    private final static String USER_NOT_FOUND_MSG = "User with this username doesn't exist";
}
