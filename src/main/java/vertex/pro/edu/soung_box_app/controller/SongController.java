package vertex.pro.edu.soung_box_app.controller;

import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.service.event.SongFinder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SongController {

    private final SongFinder songFinder;

    @GetMapping(value = SONGS_BASE_URL)
    public List<Song> getSongs() {
        log.debug("Retrieving songs");

        return songFinder.getSongs();
    }

    @UtilityClass
    public static class Links {
        public static final String SONGS_BASE_URL = "/songs";
    }

}
