package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.exception.*;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.service.login.LoginService;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationService;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.CONFIRM_USER_URL;
import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_REGISTER_URL;
import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_LOGIN_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("public/user")
public class PublicUserController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping(value = USER_REGISTER_URL)
    String register(@RequestParam("username") final String username, @RequestParam("email") final String email,
                    @RequestParam("password") final String password) throws UserAlreadyExistException, UsernameOrEmailExistException {

        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        log.info("Register user with this params- username: {}, email: {}, password: {}", username, email, password);

        return registrationService.register(newUser);
    }

    @GetMapping(value = CONFIRM_USER_URL)
    public String confirm(@RequestParam("token") String token) throws TokenNotFoundException, TokenExpiredException {
        return registrationService.confirmToken(token);
    }

    @GetMapping(value = USER_LOGIN_URL)
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password)
            throws InvalidLoginOrPasswordException {
        log.info("Login user with this params- username: {}, password: {}", username, password);

        return (String) loginService.login(username, password);
    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String USER_LOGIN_URL = "/login";
    }
}

