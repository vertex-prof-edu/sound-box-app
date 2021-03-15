package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.song_search.SongFinder;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SongController {

    private final SongFinder songFinder;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs(@RequestParam(required = false) String genre, @RequestParam(required = false) String artist) {
        log.info("Retrieving songs, their genre: {} and artist: {}", genre, artist);
        return songFinder.getSongs(genre, artist);
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/songs";
    }
}
