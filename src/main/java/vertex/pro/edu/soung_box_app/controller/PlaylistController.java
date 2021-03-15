package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;
import vertex.pro.edu.soung_box_app.exception.UserAlreadyExistException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;


@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/playlists")
public class PlaylistController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistService playlistService;

    @PostMapping(value = Links.PLAYLIST_CREATION_URL)
    String createPlaylist(@RequestParam("name") final String name) {
        UserEntity user = new UserEntity();

        Playlist playlist = new Playlist(name, user);


        return playlistService.createPlaylist(playlist);
    }

    @UtilityClass
    public static class Links {
        public static final String PLAYLIST_CREATION_URL = "/create";
    }
}
