package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.user.security_config.authentication.UserAuthenticationService;

@RestController
@RequestMapping("/users")
//@FieldDefaults
@AllArgsConstructor
public class SecureUsersController {

    @NonNull
    UserAuthenticationService authentication;

    @GetMapping("/current")
    User getCurrent(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal User user) {
        authentication.logout(user);
        return true;
    }
}

