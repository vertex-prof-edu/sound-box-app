package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.exception.TokenExpiredException;
import vertex.pro.edu.soung_box_app.exception.TokenNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserAlreadyExistException;
import vertex.pro.edu.soung_box_app.exception.UsernameOrEmailExistException;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.user.login.LoginService;
import vertex.pro.edu.soung_box_app.service.user.registration.RegistrationService;

import static vertex.pro.edu.soung_box_app.controller.UserController.Links.CONFIRM_USER_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.USER_REGISTER_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.USER_LOGIN_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

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

//    @PostMapping(value = USER_LOGIN_URL)
//    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
//        log.info("Login user with this params- username: {}, password: {}", username, password);
//
//        return null;
//        return loginService
////                .login(username, password)
//                .orElseThrow(() -> new InvalidLoginOrPasswordException("invalid login and/or password"));
//    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String USER_LOGIN_URL = "/login";
    }
}
