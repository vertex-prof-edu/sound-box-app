package vertex.pro.edu.soung_box_app.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.annotation.Id;
import vertex.pro.edu.soung_box_app.model.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static vertex.pro.edu.soung_box_app.utils.prototypes.model.UserPrototypes.aUser;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUpRepository() {
        User user;
        for (int i = 0; i < 10; i++) {
            this.entityManager.persistAndFlush(user = User.builder()
                    .username("dontNeedfulUsername")
                    .password("password")
                    .releaseDate(LocalDateTime.parse("2020-01-01T21:00:00.000"))
                    .build());
            repository.save(user);
        }
        repository.save(User.builder()
                .username("needfulUsername")
                .password("password")
                .releaseDate(LocalDateTime.parse("2020-01-01T21:00:00.000"))
                .build());

        System.out.println(repository);
    }

//    private static Stream<Arguments> provideToStringsForSaveUserTest() {
//        return Stream.of(
//                Arguments.of(UUID.randomUUID().toString() ,"needfulUsername", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername1", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername2", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername3", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername4", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername5", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername6", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername7", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername8", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")),
//                Arguments.of(UUID.randomUUID().toString() ,"dontNeedfulUsername9", "password", LocalDateTime.parse("2020-01-01T21:00:00.000")));
//    }

    @Test
    void saveUserToDatabase() {
        User entity = aUser();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        User loadedUser = entityManager.find(User.class, entity.getId());
        assertThat(loadedUser).isEqualToIgnoringGivenFields(entity, "releaseDate");
        assertThat(loadedUser.getReleaseDate()).isEqualToIgnoringSeconds(entity.getReleaseDate());
    }

//    @Test
//    void findUserByUserId() {
//        User entityThatWeNeed = aUser();
//        User entityThatWeDontNeed = aUser();
//
//        entityThatWeNeed.setId(UUID.randomUUID().toString());
//        entityThatWeDontNeed.setId(UUID.randomUUID().toString());
//
//        List<User> allUsers = repository.findAll();
//        System.out.println(allUsers);
//
//        System.out.println("-------");
//
//        Optional<User> userById = repository.findById(entityThatWeNeed.getId());
//        System.out.println(userById);
//        System.out.println("-------");
//        Optional<User> userByAnotherId = repository.findById(entityThatWeDontNeed.getId());
//        System.out.println(userByAnotherId);
////        List<User> userById = repository.findById(entity.getId());
//
//
//        assertThat(userById).isNotEmpty()
////                .extracting(SongEntity::getArtist)
//                .contains(entityThatWeNeed.getId())
//                .doesNotContain(entityThatWeDontNeed.getArtist());
//
//    }

//    @Test
//    void findUserByUsername() {
//        User entityThatWeNeed = aUser();
//        User entityThatWeDontNeed = aUser();
//
//        entityThatWeNeed.setUsername("needfulUsername");
//        System.out.println("entityThatWeNeed - " + entityThatWeNeed);
//        entityThatWeDontNeed.setUsername("dontNeedfulUsername");
//        System.out.println("dontNeedfulUsername - " + entityThatWeDontNeed);
//
//        Optional<User> searchUser = repository.findByUsername(entityThatWeNeed.getUsername());
//        System.out.println("searchUser - " + searchUser);
//
//        assertThat(searchUser).isNotEmpty()
//                .contains(entityThatWeNeed);
//
//
//    }
}
















//        assertThat(searchUser).asList()
//                .isNotEmpty()
//                .contains(entityThatWeNeed)
//                .doesNotContain(entityThatWeDontNeed);