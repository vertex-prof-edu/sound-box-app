package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.user.security_config.authentication.UserAuthenticationService;

import static vertex.pro.edu.soung_box_app.controller.SecureUsersController.Links.USER_CURRENT_URL;
import static vertex.pro.edu.soung_box_app.controller.SecureUsersController.Links.USER_LOGOUT_URL;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class SecureUsersController {

    @NonNull
    UserAuthenticationService authentication;

    @GetMapping(value = USER_CURRENT_URL)
    User getCurrent(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(value = USER_LOGOUT_URL)
    boolean logout(@AuthenticationPrincipal User user) {
        authentication.logout(user);
        return true;
    }

    @UtilityClass
    public static class Links {
        public static final String USER_CURRENT_URL = "/current";
        public static final String USER_LOGOUT_URL = "/logout";
    }
}

