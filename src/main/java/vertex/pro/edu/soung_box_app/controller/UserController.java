package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.*;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationService;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import static vertex.pro.edu.soung_box_app.controller.UserController.Links.CONFIRM_USER_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.AUTH_USER_URL;


@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final JwtProvider jwtProvider;
    private final UserCrudService userService;
    private final RegistrationService registrationService;

    @GetMapping(value = CONFIRM_USER_URL)
    public String confirm(@RequestParam("token") String token) throws TokenNotFoundException, TokenExpiredException {
        return registrationService.confirmToken(token);
    }

//    @PostMapping(value = Links.LOGIN_USER_URL)
//    public String auth(@RequestParam("username") final String username, @RequestParam("password") final String password) {
//        UserEntity userEntity = userService.findByLoginAndPassword(username, password);
//        return jwtProvider.generateToken(username);
//    }


    @UtilityClass
    public static class Links {
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String AUTH_USER_URL = "/auth";
    }
}
