package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.user.UserCrudService;
import vertex.pro.edu.soung_box_app.service.user.registration.RegistrationService;
//import vertex.pro.edu.soung_box_app.service.user.security_config.authentication.UserAuthenticationService;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_REGISTER_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/public/user")
public class PublicUserController {

//    @NonNull
//    private final UserCrudService users;
    private final RegistrationService registrationService;

    @PostMapping(value = USER_REGISTER_URL)
    String register(@RequestParam("username") final String username, @RequestParam("email") final String email, @RequestParam("password") final String password) {

        return registrationService.register(User.builder()
                .id(username)
                .username(username)
                .email(email)
                .password(password)
                .build());

//        log.info("Saving user with this params- username: {}, password: {}", username, password);


    }

//    @PostMapping(value = USER_LOGIN_URL)
//    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
//        log.info("Login user with this params- username: {}, password: {}", username, password);
//
//        return authentication
//                .login(username, password)
//                .orElseThrow(() -> new InvalidLoginOrPasswordException("invalid login and/or password"));
//    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
//        public static final String USER_LOGIN_URL = "/login";
    }
}

