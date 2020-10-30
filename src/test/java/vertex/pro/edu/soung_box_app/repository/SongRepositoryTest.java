package vertex.pro.edu.soung_box_app.repository;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static vertex.pro.edu.soung_box_app.utils.prototypes.entity.SongEntityPrototypes.aSongEntity;
import static vertex.pro.edu.soung_box_app.utils.prototypes.entity.SongEntityPrototypes.aSongEntityList;

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
                    .artist("MONATIK")
                    .genre(ThreadLocalRandom.current().nextBoolean() ? "Rock" : "R&B")
                    .title("Title")
                    .build());
            repository.save(entity);
        }
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
        SongEntity entity = aSongEntity();

        List<SongEntity> songEntityList = aSongEntityList();

//        List<SongEntity> allEntities = repository.findAll();
        List<SongEntity> entities = repository.findByParams(null, null);



        assertThat(entities).isNotEmpty()
                .extracting(SongEntity::getId)
                .contains(String.valueOf(songEntityList));

//        assertThat(entities).isNotEmpty()
//                .extracting(SongEntity::getId)
//                .extractingResultOf(entity.getGenre())
//                .containsExactly(entity.getId())
//                .doesNotContain(String.valueOf(entity.getGenre() == null))
//                .doesNotContain(String.valueOf(entity.getArtist() == null));
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
        List<SongEntity> entitiesByParamsThatDoesntNeedful = repository.findByParams(entityThatWeDontNeed.getGenre(), null);

        System.out.println(entitiesByParams);
        System.out.println(entitiesByParamsThatDoesntNeedful);

        assertThat(entitiesByParams).isNotEmpty()
                .extracting(SongEntity::getGenre)
                .contains(entityThatWeNeed.getGenre())
                .doesNotContain(entityThatWeDontNeed.getGenre());
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
                .containsExactly(entity.getId())
                .contains(String.valueOf(entity.getGenre() == null));
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

    @AfterClass
    public void tearDown() {
        repository.deleteAll();
    }
}