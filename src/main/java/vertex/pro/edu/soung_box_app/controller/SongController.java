package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.service.song.SongFinder;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.LIKE_SONG_URL;
import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongFinder songFinder;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs(@RequestParam(required = false) String genre, @RequestParam(required = false) String artist) {
        log.info("Retrieving songs, their genre: {} and artist: {}", genre, artist);
        return songFinder.getSongs(genre, artist);
    }

    @PostMapping(value = LIKE_SONG_URL)
    public String likeSong(@RequestParam String songId) {
        return "liked";
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/public/songs/getSongs";
        public static final String LIKE_SONG_URL = "/song/like";
    }
}
