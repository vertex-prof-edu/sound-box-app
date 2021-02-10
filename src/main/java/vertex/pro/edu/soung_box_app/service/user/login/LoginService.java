package vertex.pro.edu.soung_box_app.service.user.login;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;
import vertex.pro.edu.soung_box_app.service.user.registration.security.token.ConfirmationTokenService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserCrudService userCrudService;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

//    public String login(User user) {
//        Optional<User> username = userRepository.findByUsername(user.getUsername());
//        Optional<User>  = userRepository.findByUsername(user.getUsername());
//    }
}
