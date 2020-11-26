package vertex.pro.edu.soung_box_app.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.user.UserCrudService;
import vertex.pro.edu.soung_box_app.service.user.security_config.authentication.UserAuthenticationService;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_REGISTER_URL;
import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_LOGIN_URL;

@Slf4j
@RestController
@Configuration
@RequiredArgsConstructor
public class PublicUserController {

    @NonNull
    UserAuthenticationService authentication;
    @NonNull
    private final UserCrudService users;

    @PostMapping(value = USER_REGISTER_URL)
    String register(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        users.save(User.builder()
                        .id(username)
                        .username(username)
                        .password(password)
                        .build());

        log.debug("Saving user with this params- username: {}, password: {}", username, password);

        return login(username, password);

    }

    @PostMapping(value = USER_LOGIN_URL)
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        log.debug("Saving user with this params- username: {}, password: {}", username, password);

        return authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
        public static final String USER_LOGIN_URL = "/login";
    }
}

