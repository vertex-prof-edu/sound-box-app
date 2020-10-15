package vertex.pro.edu.soung_box_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.service.event.SongFinder;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongFinder songFinder;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs(@RequestParam(required = false) String genre) {
        log.debug("Retrieving songs, genre: {}", genre);

        return songFinder.getSongs(genre);
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/songs";
    }

}
