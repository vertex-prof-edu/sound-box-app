package vertex.pro.edu.soung_box_app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import vertex.pro.edu.soung_box_app.repository.SongRepository;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class SoundBoxApp {

//    private final SongRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SoundBoxApp.class, args);
    }

//    @Override
//    public void run(String... args) {
//        for (int i = 0; i < 10; i++) {
//            SongEntity entity = SongEntity.builder()
//                    .album("Album")
//                    .artist(ThreadLocalRandom.current().nextBoolean() ? "MONATIK" : "AC/DC")
//                    .genre(ThreadLocalRandom.current().nextBoolean() ? "Rock" : "R&B")
//                    .title("Title")
//                    .build();
//
//            log.info("Entity: {}", entity);
//            repository.save(entity);
//        }
//    }
}
