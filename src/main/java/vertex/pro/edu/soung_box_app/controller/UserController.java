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
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_LOGIN_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.CONFIRM_USER_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final JwtProvider jwtProvider;
    private final UserCrudService userService;
    private final LoginService loginService;
    private final RegistrationService registrationService;

    @GetMapping(value = CONFIRM_USER_URL)
    public String confirm(@RequestParam("token") String token) throws TokenNotFoundException, TokenExpiredException {
        return registrationService.confirmToken(token);
    }

    @GetMapping(value = USER_LOGIN_URL)
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password)
            throws InvalidLoginOrPasswordException {
        log.info("Login user with this params- username: {}, password: {}", username, password);

        return loginService.login(username, password);
    }


    @UtilityClass
    public static class Links {
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String USER_LOGIN_URL = "/login";
    }
}
