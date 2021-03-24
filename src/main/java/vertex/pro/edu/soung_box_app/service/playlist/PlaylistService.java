package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.playlist.PlaylistConverter;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
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
    private final SongConverter songConverter;
    private final PlaylistConverter playlistConverter;
    private final PlaylistRepository playlistRepository;

    @Override
    public void createDefaultPlaylist(PlaylistEntity playlistEntity) {
        playlistRepository.save(playlistEntity);
    }

    @Override
    public Playlist createPlaylist(String name) throws UserDoesntExistException, UserNotConfirmedException {
        PlaylistEntity playlistEntity = new PlaylistEntity(name, getCurrent());

        return playlistConverter.fromEntity(playlistRepository.save(playlistEntity));
    }

    public List<Playlist> showAllPlaylists() throws UserDoesntExistException, UserNotConfirmedException {
        List<PlaylistEntity> playlistEntities = playlistRepository.showAllUserPlaylists(getCurrent().getId());

        return playlistConverter.fromEntities(playlistEntities);
    }

    public List<Playlist> findPlaylistByName(String name) throws UserDoesntExistException, UserNotConfirmedException {
        List<PlaylistEntity> allPlaylistEntities = playlistRepository.showAllUserPlaylists(getCurrent().getId());

        List<PlaylistEntity> playlist = allPlaylistEntities.stream()
                .filter(u -> u.getName().equals(name)).collect(Collectors.toList());

        return playlistConverter.fromEntities(playlist);
    }

//    public Playlist addSongToPlaylist(String songTitle, String playlistName) throws UserDoesntExistException,
//            UserNotConfirmedException, PlaylistDoesntExistException {
//        List<Playlist> findablePlaylist = findPlaylistByName(playlistName);
//
//        List<SongEntity> allSongs = songRepository.findAll();
//        SongEntity song = (SongEntity) allSongs.stream().filter(u -> u.getTitle().equals(songTitle));
////        List<SongEntity> findableSong = songRepository.findByName(songName);
//
//        if (findablePlaylist == null) {
////            Playlist playlistInWhichSaveSongs = new Playlist(playlistName, getCurrent(), (SongEntity) song);
//            throw new PlaylistDoesntExistException(PLAYLIST_DOESNT_EXIST_EXC_MSG);
//        } else {
//            PlaylistEntity playlistEntity = playlistRepository.save(new PlaylistEntity(playlistName, getCurrent(),
//                     songConverter.fromEntity(song)));
//
//            return playlistConverter.fromEntity(playlistEntity);
//        }
//
////        List<SongEntity> entities = songRepository.findByParams(genre, artist);
////        return songConverter.fromEntities(entities);
////        return playlistRepository.save(new Playlist(playlistName, getCurrent(), (SongEntity) song));
//    }


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