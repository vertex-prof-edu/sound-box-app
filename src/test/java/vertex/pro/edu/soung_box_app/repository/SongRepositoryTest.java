package vertex.pro.edu.soung_box_app.repository;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static vertex.pro.edu.soung_box_app.utils.prototypes.entity.SongEntityPrototypes.aSongEntity;

@EnableJpaRepositories("src/main/java/vertex/pro/edu/soung_box_app/repository/SongRepository.java")
@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
class SongRepositoryTest extends TestCase {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SongRepository repository;

    @BeforeEach
    public void setUpRepository() {
        SongEntity entity;
        for (int i = 0; i < 10; i++) {
            this.entityManager.persistAndFlush(entity = SongEntity.builder()
                    .album("Album")
                    .artist(ThreadLocalRandom.current().nextBoolean() ? "MONATIK" : "The Doors")
                    .genre(ThreadLocalRandom.current().nextBoolean() ? "Rock" : "R&B")
                    .title("Title")
                    .build());
            repository.save(entity);
        }

        System.out.println(repository);
    }

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
    void findsSongsByNullParams() {
        List<SongEntity> allEntities = repository.findAll();
        List<SongEntity> entities = repository.findByParams(null, null);

        assertThat(entities).isNotEmpty()
                .containsAll(allEntities);
    }


    @Test
    void findsSongsByGenre() {
        SongEntity entityThatWeNeed = aSongEntity();
        SongEntity entityThatWeDontNeed = aSongEntity();

        entityThatWeNeed.setGenre("Rock");
        entityThatWeDontNeed.setGenre("R&B");

        List<SongEntity> allEntities = repository.findAll();
        System.out.println(allEntities);

        System.out.println("-------");

        List<SongEntity> entitiesByParams = repository.findByParams(entityThatWeNeed.getGenre(), null);
        System.out.println(entitiesByParams);

        assertThat(entitiesByParams).isNotEmpty()
                .extracting(SongEntity::getGenre)
                .contains(entityThatWeNeed.getGenre())
                .doesNotContain(entityThatWeDontNeed.getGenre());
    }

    @Test
    void findsSongsByArtist() {
        SongEntity entityThatWeNeed = aSongEntity();
        SongEntity entityThatWeDontNeed = aSongEntity();

        entityThatWeNeed.setArtist("MONATIK");
        entityThatWeDontNeed.setArtist("The Doors");

        List<SongEntity> entitiesByParams = repository.findByParams(null,entityThatWeNeed.getArtist());

        assertThat(entitiesByParams).isNotEmpty()
                .extracting(SongEntity::getArtist)
                .contains(entityThatWeNeed.getArtist())
                .doesNotContain(entityThatWeDontNeed.getArtist());
    }

    @Test
    void findsSongsByGenreAndArtist() {
        SongEntity entityThatWeNeed = aSongEntity();
        SongEntity entityThatWeDontNeed = aSongEntity();

        entityThatWeNeed.setGenre("Rock");
        entityThatWeNeed.setArtist("MONATIK");
        entityThatWeDontNeed.setGenre("R&B");
        entityThatWeDontNeed.setArtist("The Doors");

        List<SongEntity> entitiesByParams = repository.findByParams(entityThatWeNeed.getGenre(),entityThatWeNeed.getArtist());

        assertThat(entitiesByParams).isNotEmpty()
                .extracting(SongEntity::getGenre)
                .contains(entityThatWeNeed.getGenre())
                .doesNotContain(entityThatWeDontNeed.getGenre());

        assertThat(entitiesByParams).isNotEmpty()
                .extracting(SongEntity::getArtist)
                .contains(entityThatWeNeed.getArtist())
                .doesNotContain(entityThatWeDontNeed.getArtist());
    }

    @AfterClass
    public void tearDown() {
        repository.deleteAll();
    }
}