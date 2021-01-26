package vertex.pro.edu.soung_box_app.service.user.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import vertex.pro.edu.soung_box_app.exception.InvalidLoginOrPasswordException;
import vertex.pro.edu.soung_box_app.exception.UserAlreadyExistException;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.model.user.UserRole;
import vertex.pro.edu.soung_box_app.service.user.UserCrudService;
import vertex.pro.edu.soung_box_app.service.user.registration.security.email.EmailSender;
import vertex.pro.edu.soung_box_app.service.user.registration.security.email.EmailValidator;
import vertex.pro.edu.soung_box_app.service.user.registration.security.token.ConfirmationTokenService;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserCrudService userCrudService;
    private final EmailValidator emailValidator;
//    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSender emailSender;

    public String register(User user) {
        boolean isValidEmail = emailValidator.test(user.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

//        String token = userCrudService.save(User.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .userRole(UserRole.USER)
//                .build());

//        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
//        emailSender.send(
//                request.getEmail(),
//                buildEmail(request.getFirstName(), link));

        return userCrudService.save(User.builder()
                        .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .userRole(UserRole.USER)
                    .build());
    }
}
