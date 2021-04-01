package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.playlist.PlaylistConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.exception.PlaylistNotFoundException;
import vertex.pro.edu.soung_box_app.exception.SongNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService implements PlaylistCreator {

    private final SongRepository songRepository;
    private final PlaylistConverter playlistConverter;
    private final PlaylistRepository playlistRepository;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public PlaylistEntity createDefaultPlaylist() throws Exception {
        String playlistName = "likes";

        if (findPlaylistByName(playlistName) == null) {
            return new PlaylistEntity(playlistName, userDetailsService.getCurrent(), LocalDateTime.now());
        } else {
            return playlistRepository.findByName(playlistName);
        }
    }

    @Override
    public Playlist createPlaylist(String name) throws Exception {

        PlaylistEntity playlistEntity = new PlaylistEntity(name, userDetailsService.getCurrent(), LocalDateTime.now());

        return playlistConverter.fromEntity(playlistRepository.save(playlistEntity));
    }

    public List<Playlist> showAllPlaylists() throws UserDoesntExistException, UserNotConfirmedException {

        List<PlaylistEntity> playlistEntities = playlistRepository.
                showAllUserPlaylists(userDetailsService.getCurrent().getId());

        return playlistConverter.fromEntities(playlistEntities);
    }

    public List<Playlist> findPlaylistByName(String name) throws Exception {

        List<PlaylistEntity> allPlaylistEntities = playlistRepository.
                showAllUserPlaylists(userDetailsService.getCurrent().getId());

        List<PlaylistEntity> playlist = allPlaylistEntities.stream()
                .filter(u -> u.getName().equals(name)).collect(Collectors.toList());

        return playlistConverter.fromEntities(playlist);
    }

//    public Playlist addSongToPlaylist(String playlistName, String playlistCreatedAt,
//                                      String songId, String title, String genre, String artist, String releaseSongDate)
//            throws UserDoesntExistException, UserNotConfirmedException {
//
//        UserEntity user = userDetailsService.getCurrent();
//        System.out.println(user);
//        SongEntity addedSong = songRepository.findByParamsForPlaylist(songId, title, genre, artist, releaseSongDate);
//        System.out.println(addedSong);
//        PlaylistEntity requiredPlaylist = playlistRepository.findByParams(playlistName, playlistCreatedAt, user);
//        System.out.println(requiredPlaylist);
//
//        if (requiredPlaylist == null) {
//            return playlistConverter.fromEntity(playlistRepository.
//                    save(new PlaylistEntity(playlistName, user, LocalDateTime.now(), addedSong)));
//        } else {
//            requiredPlaylist.getSongs().add(addedSong);
//            addedSong.getPlaylistEntities().add(requiredPlaylist);
//
//            return playlistConverter.fromEntity(playlistRepository.save(requiredPlaylist));
//        }
//    }

    public Playlist addSongToPlaylist(String playlistId, String songId) throws Exception {
        SongEntity addedSong = findSongById(songId);
        System.out.println(addedSong);
        PlaylistEntity requiredPlaylist = findPlaylistById(playlistId);
        System.out.println(requiredPlaylist);

        requiredPlaylist.getSongs().add(addedSong);
        addedSong.getPlaylistEntities().add(requiredPlaylist);

        Playlist newPlaylist = playlistConverter.fromEntity(playlistRepository.save(requiredPlaylist));
        System.out.println(newPlaylist);

        return newPlaylist;
    }

    public PlaylistEntity findPlaylistById(String id) throws PlaylistNotFoundException {
        if (playlistRepository.findById(id).isEmpty()) {
            throw new PlaylistNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        }
        return playlistRepository.findById(id).get();
    }

    public SongEntity findSongById(String id) throws SongNotFoundException {
        if (songRepository.findById(id).isEmpty()) {
            throw new SongNotFoundException(SONG_NOT_FOUND_EXC_MSG);
        }
        return songRepository.findById(id).get();
    }

    private final static String SONG_NOT_FOUND_EXC_MSG = "song doesnt exist";
    private final static String PLAYLIST_NOT_FOUND_EXC_MSG = "playlist doesnt exist";
}