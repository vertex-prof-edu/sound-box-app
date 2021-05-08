package vertex.pro.edu.soung_box_app.repository;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;


@EnableJpaRepositories
@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
public class UserRepositoryTest extends TestCase {
    @Autowired
    private TestEntityManager entityManager;
    private UserRepository repository;

//    @BeforeEach
//    public void setUpRepository() {
//        UserEntity user;
//
//        for (int i = 0; i < 10; i++) {
//            this.entityManager.persistAndFlush(user = UserEntity.builder().build());
//            repository.save(user);
//        }
//
//        System.out.println(repository);
//    }

    @Test
    void saveUserToDatabase() {
        String id = UUID.randomUUID().toString();

        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .username("username")
                .email("email")
                .password("password")
                .userRole(UserRole.USER)
                .enabled(false)
                .registrationDate(LocalDateTime.now())
                .build();

        repository.save(userEntity);

//        Assert.assertNotNull(repository.findByUsername("username"));

        UserEntity queryResult = repository.findByUsername("username");

        assertNotNull(queryResult);
    }


}














