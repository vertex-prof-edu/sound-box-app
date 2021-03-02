package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService implements PlaylistCreator{

    private final User user;
    private final UserCrudService userCrudService;
    private final PlaylistRepository playlistRepository;

    @Override
    public void createDefaultPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public String createPlaylist(Playlist playlist) {
        String playlistName = playlist.getName();
        String username = user.getUsername();


        UserDetails user = userCrudService.loadUserByUsername(username);
        playlist = new Playlist(playlistName, (User) user);

        playlistRepository.save(playlist);

        return playlistName;
    }

    public Optional<Playlist> getPlaylists(String name) {
        return playlistRepository.findByName(name);
    }

}
