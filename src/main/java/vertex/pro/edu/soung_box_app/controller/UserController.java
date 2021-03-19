package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.*;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.login.LoginService;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationService;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static vertex.pro.edu.soung_box_app.controller.PublicUserController.Links.USER_LOGIN_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.CONFIRM_USER_URL;
import static vertex.pro.edu.soung_box_app.controller.UserController.Links.USER_LOGOUT_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping(value = CONFIRM_USER_URL)
    public String confirm(@RequestParam("token") String token) throws TokenNotFoundException, TokenExpiredException {
        return registrationService.confirmToken(token);
    }

    @GetMapping(value = USER_LOGOUT_URL)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "u are logout";
    }


    @UtilityClass
    public static class Links {
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String USER_LOGOUT_URL = "/logout";
    }
}
