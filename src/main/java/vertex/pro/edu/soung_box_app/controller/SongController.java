package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.converter.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.service.song.SongFinder;
import vertex.pro.edu.soung_box_app.service.song.SongService;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongFinder songFinder;
    private final SongService songService;
    private final SongConverter songConverter;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs(@RequestParam(required = false) String genre,
                               @RequestParam(required = false) String artist) {
        log.info("Retrieving songs, their genre: {} and artist: {}", genre, artist);
        return songConverter.fromEntities(songFinder.getSongs(genre, artist));
    }

    @PostMapping(value = LIKE_SONG_URL)
    public String likeSong(@RequestParam String songId) {
        return songService.likeSong(songId);
    }

    @PostMapping(value = DISLIKE_SONG_URL)
    public String dislikeSong(@RequestParam String songId) {
        return songService.dislike(songId);
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/public/songs/getSongs";
        public static final String LIKE_SONG_URL = "/song/like";
        public static final String DISLIKE_SONG_URL = "/song/dislike";
    }
}
