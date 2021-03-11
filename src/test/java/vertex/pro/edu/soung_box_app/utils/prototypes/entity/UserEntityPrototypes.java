package vertex.pro.edu.soung_box_app.utils.prototypes.entity;

import lombok.experimental.UtilityClass;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class UserEntityPrototypes {

    public static List<UserEntity> aUserEntityList() {
        return List.of(aUserEntity());
    }

    public static UserEntity aUserEntity() {
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .username("username")
                .email("testEmail")
                .password("password")
                .userRole(UserRole.USER)
                .registrationDate(LocalDateTime.parse("2020-01-01T21:00:00.000"))
                .enabled(false)
                .build();
    }
}
