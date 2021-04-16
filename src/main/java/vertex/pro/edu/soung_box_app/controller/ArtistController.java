package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.controller.request_body.SongRequest;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.service.artist.ArtistService;

import java.util.List;

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
    public Song addNewSong(@RequestBody SongRequest songRequest) throws Exception {
        return songConverter.fromEntity(artistService.addNewSong(songRequest));
    }

    @GetMapping(value = SHOW_ALL_ADDED_SONG)
    public List<Song> showAllAddedSongs() throws Exception{
        return songConverter.fromEntities(artistService.showAllArtistSongs());
    }

    @GetMapping(value = SHOW_SONGS_STATISTIC)
    public List<SongEntity> showArtistStatistics() throws Exception {
        return artistService.showArtistStatistics();
    }

    @UtilityClass
    public static class Links {
        public static final String ADD_NEW_SONG = "/addSong";
        public static final String SHOW_ALL_ADDED_SONG = "/showAllArtistSongs";
        public static final String SHOW_SONGS_STATISTIC = "/showSongsStatistic";
    }
}
