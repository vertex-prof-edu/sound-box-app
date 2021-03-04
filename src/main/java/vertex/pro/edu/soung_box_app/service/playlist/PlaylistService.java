package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService implements PlaylistCreator {

//    private final UserEntity user;
    private final PlaylistRepository playlistRepository;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public void createDefaultPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public String createPlaylist(Playlist playlist) {
        String playlistName = playlist.getName();
//        String username = user.getUsername();


//        User user = userDetailsService.loadUserByUsername(username);
//        playlist = new Playlist(playlistName, user);

        playlistRepository.save(playlist);

        return playlistName;
    }

    public Optional<Playlist> getPlaylists(String name) {
        return playlistRepository.findByName(name);
    }

}
