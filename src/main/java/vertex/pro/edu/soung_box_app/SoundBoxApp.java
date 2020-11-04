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
    public static void main(String[] args) {
        SpringApplication.run(SoundBoxApp.class, args);
    }
}
