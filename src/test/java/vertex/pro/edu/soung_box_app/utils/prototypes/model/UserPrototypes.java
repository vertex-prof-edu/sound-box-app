package vertex.pro.edu.soung_box_app.utils.prototypes.model;

import lombok.experimental.UtilityClass;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserPrototypes {

    public static List<User> aUserList() {
        return List.of(aUser());
    }

    public static User aUser() {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .username("username")
                .password("password")
                .releaseDate(LocalDateTime.parse("2020-01-01T21:00:00.000"))
                .build();
    }
}
