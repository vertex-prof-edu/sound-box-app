package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.controller.request.body.NewUserRequest;
import vertex.pro.edu.soung_box_app.controller.request.body.UserRequest;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationServiceImpl;
import vertex.pro.edu.soung_box_app.service.crud.UserCrudServiceImpl;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.*;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class PublicUserController {

    private final JwtProvider jwtProvider;
    private final UserCrudServiceImpl service;
    private final RegistrationServiceImpl registrationServiceImpl;

    @PostMapping(value = USER_REGISTER_URL)
    public String register(@RequestBody NewUserRequest user) {

        UserEntity newUser = UserEntity.builder()
                .username(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        log.info("Register user with this params- username: {}, email: {}", user.getLogin(), user.getEmail());

        return registrationServiceImpl.register(newUser);
    }

    @PostMapping(value = USER_AUTH_URL)
    public String auth(@RequestParam("username") final String username) {
        return jwtProvider.generateToken(username);
    }

    @GetMapping(value = USER_LOGIN_URL)
    public String login(@RequestBody UserRequest user) {
        log.info("Login user with this params- username: {} ", user.getLogin());

        return service.login(user.getLogin(), user.getPassword());
    }

    @UtilityClass
    public static class Links {
        public static final String USER_REGISTER_URL = "/register";
        public static final String USER_LOGIN_URL = "/login";
        public static final String USER_AUTH_URL = "/auth";
    }
}

