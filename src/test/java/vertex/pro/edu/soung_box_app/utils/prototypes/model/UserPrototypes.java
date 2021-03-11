package vertex.pro.edu.soung_box_app.utils.prototypes.model;

import lombok.experimental.UtilityClass;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserPrototypes {

    public static List<User> aUserList() {
        return List.of(aUser());
    }

    public static User aUser() {
        return User.builder()
                .username("username")
                .email("testEmail")
                .password("password")
                .userRole(UserRole.USER)
                .build();
    }
}
