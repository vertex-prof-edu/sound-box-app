package vertex.pro.edu.soung_box_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import vertex.pro.edu.soung_box_app.model.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static vertex.pro.edu.soung_box_app.utils.prototypes.model.UserPrototypes.aUser;
@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

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

    @Test
    void findUserByUserId() {

    }
}
