package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.*;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.login.LoginService;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationService;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.*;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class PublicUserController {

    private final JwtProvider jwtProvider;
    private final LoginService loginService;
    private final RegistrationService registrationService;

    @PostMapping(value = USER_REGISTER_URL)
    String register(@RequestParam("username") final String username, @RequestParam("email") final String email,
                    @RequestParam("password") final String password) throws UsernameOrEmailExistException {

        UserEntity newUser = UserEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        log.info("Register user with this params- username: {}, email: {}, password: {}", username, email, password);

        return registrationService.register(newUser);
    }

    @GetMapping(value = USER_AUTH_URL)
    public String auth(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        return jwtProvider.generateToken(username);
    }

    @GetMapping(value = USER_LOGIN_URL)
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password)
            throws InvalidLoginOrPasswordException {
        log.info("Login user with this params- username: {}, password: {}", username, password);

        return loginService.login(username, password);
    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
        public static final String USER_LOGIN_URL = "/login";
        public static final String USER_AUTH_URL = "/auth";
    }
}
