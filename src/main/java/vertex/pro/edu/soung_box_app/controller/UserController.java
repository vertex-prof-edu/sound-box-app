package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.token.model.ConfirmationToken;
import vertex.pro.edu.soung_box_app.exception.*;
import vertex.pro.edu.soung_box_app.security.token.ConfirmationTokenService;
import vertex.pro.edu.soung_box_app.service.artist.ArtistService;
import vertex.pro.edu.soung_box_app.service.registration.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static vertex.pro.edu.soung_box_app.controller.UserController.Links.*;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final ArtistService artistService;
    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping(value = CONFIRM_USER_URL)
    public String confirm(@RequestParam("token") String token) throws Exception {
        return registrationService.confirmToken(token);
    }

    @GetMapping(value = USER_LOGOUT_URL)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "u are logout";
    }

    @PostMapping(value = RESEND_TOKEN_URL)
    public ConfirmationToken resendConfirmationToken() throws Exception {
        return confirmationTokenService.resendConfirmationToken();
    }

    @PostMapping(value = BECOME_AN_ARTIST)
    public String becomeAnArtist() throws Exception {
        return artistService.becameAnArtist();
    }

    @UtilityClass
    public static class Links {
        public static final String CONFIRM_USER_URL = "/confirm";
        public static final String USER_LOGOUT_URL = "/logout";
        public static final String RESEND_TOKEN_URL = "/resend_token";
        public static final String BECOME_AN_ARTIST = "/become_an_artist";
    }
}
