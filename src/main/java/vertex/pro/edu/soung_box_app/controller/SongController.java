package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.event.SongFinder;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController {

    private SongRepository repository;
    private final SongFinder songFinder;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs(@RequestParam(required = false) String genre, @RequestParam(required = false) String artist) {
        SongEntity entity;
        for (int i = 0; i < 10; i++) {
            entity = SongEntity.builder()
                    .album("Album")
                    .artist(ThreadLocalRandom.current().nextBoolean() ? "MONATIK" : "The Doors")
                    .genre(ThreadLocalRandom.current().nextBoolean() ? "Rock" : "R&B")
                    .title("Title")
                    .build();
            repository.save(entity);
        }
        log.info("Retrieving songs, their genre: {} and artist: {}", genre, artist);
        return songFinder.getSongs(genre, artist);
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/songs";
    }
}
