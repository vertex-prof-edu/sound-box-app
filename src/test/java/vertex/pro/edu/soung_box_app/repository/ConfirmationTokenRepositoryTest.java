package vertex.pro.edu.soung_box_app.repository;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ConfirmationTokenRepositoryTest extends TestCase {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ConfirmationTokenRepository repository;

    @BeforeEach
    public void setUpRepository() {
//        String token = UUID.randomUUID().toString();
//
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                user);
//        ConfirmationToken token;
//
//        for (int i = 0; i < 10; i++) {
//            this.entityManager.persistAndFlush(token = new new ConfirmationToken(
//                    token,
//                    LocalDateTime.now(),
//                    LocalDateTime.now().plusMinutes(15),
//                    user));
//        }
    }

//    @BeforeEach
//    public void setUpRepository() {
//        SongEntity entity;
//        for (int i = 0; i < 10; i++) {
//            this.entityManager.persistAndFlush(entity = SongEntity.builder()
//                    .album("Album")
//                    .artist(ThreadLocalRandom.current().nextBoolean() ? "MONATIK" : "The Doors")
//                    .genre(ThreadLocalRandom.current().nextBoolean() ? "Rock" : "R&B")
//                    .title("Title")
//                    .build());
//            repository.save(entity);
//        }
//
//        System.out.println(repository);
//    }

}
