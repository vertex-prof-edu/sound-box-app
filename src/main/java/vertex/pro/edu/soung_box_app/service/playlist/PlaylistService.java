package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService implements PlaylistCreator {

    private final UserCrudService service;
    private final PlaylistRepository playlistRepository;

    @Override
    public void createDefaultPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public String createPlaylist(String name) throws UserDoesntExistException {
        Playlist playlist = new Playlist(name, getCurrent());

        playlistRepository.save(playlist);

        return name;
    }

    public List<Playlist> showAllPlaylists() throws UserDoesntExistException {
        String userId = getCurrent().getId();

        return playlistRepository.showAllUserPlaylists(userId);
    }

    public UserEntity getCurrent() throws UserDoesntExistException {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        return service.findByUsername(loggedUser);
    }

}