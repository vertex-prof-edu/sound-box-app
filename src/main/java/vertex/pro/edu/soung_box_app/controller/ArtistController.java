package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.controller.request.body.SongRequest;
import vertex.pro.edu.soung_box_app.converter.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.service.artist.ArtistService;

import java.util.List;
import java.util.Map;

import static vertex.pro.edu.soung_box_app.controller.ArtistController.Links.*;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final SongConverter songConverter;

    @PostMapping(value = ADD_NEW_SONG)
    public Song addNewSong(@RequestBody SongRequest songRequest) {
        return songConverter.fromEntity(artistService.addNewSong(songRequest));
    }

    @PostMapping(value = DELETE_ADDED_SONG)
    public String deleteSong(@RequestParam String songId) {
        return artistService.removeAddedSong(songId);
    }

    @GetMapping(value = SHOW_ALL_ADDED_SONG)
    public List<Song> showAllAddedSongs() {
        return songConverter.fromEntities(artistService.showAllArtistSongs());
    }

    @GetMapping(value = SHOW_SONGS_STATISTIC)
    public Map<String, Integer> showSongsStatistics() {
        return artistService.showSongsStatistics();
    }

    @GetMapping(value = SHOW_ARTIST_STATISTIC)
    public Map<String, Integer> showArtistStatistics() {
        return artistService.showSubscriptionStatistics();
    }

    @UtilityClass
    public static class Links {
        public static final String ADD_NEW_SONG = "/addSong";
        public static final String DELETE_ADDED_SONG = "/deleteSong";
        public static final String SHOW_ALL_ADDED_SONG = "/showAllArtistSongs";
        public static final String SHOW_SONGS_STATISTIC = "/showSongsStatistic";
        public static final String SHOW_ARTIST_STATISTIC = "/showArtistStatistic";
    }
}
