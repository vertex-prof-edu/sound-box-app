package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.service.save.UserSaver;

import static vertex.pro.edu.soung_box_app.controller.UserController.Links.USER_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserSaver userSaver;
    private final User user;

    @GetMapping(value = USER_BASE_URL)
    public void saveUser(@RequestParam String username, @RequestParam String password) {

        user.setUsername(username);
        user.setPassword(password);

        userSaver.saveUser(User.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .releaseDate(user.getReleaseDate())
                            .build());

        log.debug("Saving user with this params- username: {}, password: {}", username, password);
    }

    @UtilityClass
    public static class Links {
        public static final String USER_BASE_URL = "/register";
    }
}

