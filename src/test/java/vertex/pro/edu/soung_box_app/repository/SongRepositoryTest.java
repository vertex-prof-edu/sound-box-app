package vertex.pro.edu.soung_box_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vertex.pro.edu.soung_box_app.utils.prototypes.entity.SongEntityPrototypes.aSongEntity;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
class SongRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SongRepository repository;

    @Test
    void savesSongsToDatabase() {
        SongEntity entity = aSongEntity();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        SongEntity loadedSong = entityManager.find(SongEntity.class, entity.getId());
        assertThat(loadedSong).isEqualToIgnoringGivenFields(entity, "releaseDate");
        assertThat(loadedSong.getReleaseDate()).isEqualToIgnoringSeconds(entity.getReleaseDate());
    }

    @Test
    void findsSongsByNullGenre() {
        SongEntity entity = aSongEntity();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        List<SongEntity> entities = repository.findByParams(null, null);

        assertThat(entities).isNotEmpty()
                .extracting(SongEntity::getId)
                .containsExactly(entity.getId());
    }

    @Test
    void findsSongsByGenre() {
        SongEntity entity = aSongEntity();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        List<SongEntity> entities = repository.findByParams(entity.getGenre(), entity.getArtist());

        assertThat(entities).isNotEmpty()
                .extracting(SongEntity::getId)
                .containsExactly(entity.getId());
    }

    @Test
    void findsSongsByArtist() {
        SongEntity entity = aSongEntity();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        List<SongEntity> entities = repository.findByParams(null,entity.getArtist());

        assertThat(entities).isNotEmpty()
                .extracting(SongEntity::getId)
                .containsExactly(entity.getId());
    }

    @Test
    void findsSongsByGenreAndArtist() {
        SongEntity entity = aSongEntity();

        entity = repository.save(entity);
        entityManager.flush();
        entityManager.clear();

        List<SongEntity> entities = repository.findByParams(entity.getGenre(),entity.getArtist());

        assertThat(entities).isNotEmpty()
                .extracting(SongEntity::getId)
                .containsExactly(entity.getId());
    }
















}