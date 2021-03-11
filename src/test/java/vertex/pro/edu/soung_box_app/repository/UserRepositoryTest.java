package vertex.pro.edu.soung_box_app.repository;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
public class UserRepositoryTest extends TestCase {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUpRepository() {
        UserEntity user;

        for (int i = 0; i < 10; i++) {
            this.entityManager.persistAndFlush(user = UserEntity.builder().build());
            repository.save(user);
        }

        System.out.println(repository);
    }

    @Test
    void saveUserToDatabase() {
//        UserEntity userEntity = UserEntity.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .userRole(UserRole.USER)
//                .build();
    }


}














