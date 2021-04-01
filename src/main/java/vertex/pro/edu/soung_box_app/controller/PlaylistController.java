package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.exception.PlaylistNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.PlaylistController.Links.*;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping(value = PLAYLIST_CREATION_URL)
    Playlist createPlaylist(@RequestParam("name") final String name) throws Exception {
        log.info("Created playlist with name-: {}", name);

        return playlistService.createPlaylist(name);
    }

    @GetMapping(value = GET_ALL_PLAYLISTS_URL)
    List<Playlist> showAllPlaylists() throws Exception {
        return playlistService.showAllPlaylists();
    }

    @GetMapping(value = FIND_PLAYLISTS_URL)
    List<Playlist> findPlaylistByName(@RequestParam("name") final String name) throws Exception {
        return playlistService.findPlaylistByName(name);
    }

//    @PostMapping(value = ADD_SONG_TO_PLAYLIST_URL)
//    Playlist addSongToThePlaylist(@RequestBody AddSongToPlaylistRequest request)
//            throws UserDoesntExistException, UserNotConfirmedException {
//        return playlistService.addSongToPlaylist(request.getPlaylistName(), request.getPlaylistCreatedAt(),
//                request.getId(), request.getTitle(), request.getGenre(), request.getArtist(),
//                request.getReleaseSongDate());
//    }

    @PostMapping(value = ADD_SONG_TO_PLAYLIST_URL)
    Playlist addSongToThePlaylist(@RequestParam String playlistId, @RequestParam String songId) throws Exception {
        return playlistService.addSongToPlaylist(playlistId, songId);
    }

    @UtilityClass
    public static class Links {
        public static final String PLAYLIST_CREATION_URL = "/createPlaylist";
        public static final String GET_ALL_PLAYLISTS_URL = "/showAll";
        public static final String FIND_PLAYLISTS_URL = "/findPlaylist";
        public static final String ADD_SONG_TO_PLAYLIST_URL = "/addSong";
    }
}
