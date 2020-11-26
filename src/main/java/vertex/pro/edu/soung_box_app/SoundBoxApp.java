package vertex.pro.edu.soung_box_app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.user.security_config.SecurityConfig;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootApplication
public class SoundBoxApp {
    public static void main(String[] args) {
        SpringApplication.run(SoundBoxApp.class, args);
    }
}
