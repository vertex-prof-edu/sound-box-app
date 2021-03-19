package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.PlaylistController.Links.GET_ALL_PLAYLISTS_URL;
import static vertex.pro.edu.soung_box_app.controller.PlaylistController.Links.PLAYLIST_CREATION_URL;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping(value = PLAYLIST_CREATION_URL)
    String createPlaylist(@RequestParam("name") final String name) throws UserDoesntExistException {
        log.info("Created playlist with name-: {}", name);

        return playlistService.createPlaylist(name);
    }

    @GetMapping(value = GET_ALL_PLAYLISTS_URL)
    List<Playlist> showAllPlaylists() throws UserDoesntExistException {
        return playlistService.showAllPlaylists();
    }

    @UtilityClass
    public static class Links {
        public static final String PLAYLIST_CREATION_URL = "/createPlaylist";
        public static final String GET_ALL_PLAYLISTS_URL = "/showAll";
    }
}
