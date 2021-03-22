package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.PlaylistDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService implements PlaylistCreator {

    private final UserCrudService service;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    @Override
    public void createDefaultPlaylist(PlaylistEntity playlistEntity) {
        playlistRepository.save(playlistEntity);
    }

    @Override
    public String createPlaylist(String name) throws UserDoesntExistException, UserNotConfirmedException {
        PlaylistEntity playlistEntity = new PlaylistEntity(name, getCurrent());

        playlistRepository.save(playlistEntity);

        return name;
    }

    public List<PlaylistEntity> showAllPlaylists() throws UserDoesntExistException, UserNotConfirmedException {
        return playlistRepository.showAllUserPlaylists(getCurrent().getId());
    }

    public List<PlaylistEntity> findPlaylistByName(String name) throws UserDoesntExistException, UserNotConfirmedException {
        List<PlaylistEntity> allPlaylistEntities = playlistRepository.showAllUserPlaylists(getCurrent().getId());

        return allPlaylistEntities.stream()
                .filter(u -> u.getName().equals(name)).collect(Collectors.toList());
    }

    public PlaylistEntity addSongToPlaylist(String songId, String playlistName) throws UserDoesntExistException,
            UserNotConfirmedException, PlaylistDoesntExistException {
        List<PlaylistEntity> allPlaylistEntities = findPlaylistByName(playlistName);
//        Optional<SongEntity> song = songRepository.findById(songId).get();

        if (allPlaylistEntities == null) {
//            Playlist playlistInWhichSaveSongs = new Playlist(playlistName, getCurrent(), (SongEntity) song);
            throw new PlaylistDoesntExistException(PLAYLIST_DOESNT_EXIST_EXC_MSG);
        } else {
            return playlistRepository.save(new PlaylistEntity(playlistName, getCurrent(), songRepository.findById(songId).get()));
        }

//        return playlistRepository.save(new Playlist(playlistName, getCurrent(), (SongEntity) song));
    }


    public UserEntity getCurrent() throws UserDoesntExistException, UserNotConfirmedException {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = service.findByUsername(loggedUser);

        if (user.getEnabled() == null) {
            throw new UserNotConfirmedException(USER_NOT_CONFIRMED_EXC_MSG);
        } else {
            return user;
        }
    }

    private final static String USER_NOT_CONFIRMED_EXC_MSG = "user didn't confirmed yet";
    private final static String PLAYLIST_DOESNT_EXIST_EXC_MSG = "user didn't confirmed yet";

}