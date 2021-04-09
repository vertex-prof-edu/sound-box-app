package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.playlist.PlaylistConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.exception.PlaylistNotFoundException;
import vertex.pro.edu.soung_box_app.exception.SongNotFoundException;
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

        String playlistTitle = "likes";
        PlaylistEntity requiredPlaylist = playlistRepository.findByName(playlistTitle);

        if (requiredPlaylist.getPlaylistTitle().isEmpty()) {
            return playlistRepository.save
                    (new PlaylistEntity(playlistTitle, userDetailsService.getCurrent(), LocalDateTime.now()));
        } else {
            return requiredPlaylist;
        }
    }

    @Override
    public Playlist createPlaylist(String name) throws Exception {

        PlaylistEntity playlistEntity = new PlaylistEntity(name, userDetailsService.getCurrent(), LocalDateTime.now());

        return playlistConverter.fromEntity(playlistRepository.save(playlistEntity));
    }

    public List<Playlist> showAllPlaylists() throws Exception {

        List<PlaylistEntity> playlistEntities = playlistRepository.
                showAllUserPlaylists(userDetailsService.getCurrent().getId());

        if (playlistEntities.isEmpty()) {
            throw new PlaylistNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        } else {
            return playlistConverter.fromEntities(playlistEntities);
        }
    }

    public List<PlaylistEntity> findPlaylistsByName(String playlistTitle) throws Exception {

        List<PlaylistEntity> allPlaylistEntities = playlistRepository.
                showAllUserPlaylists(userDetailsService.getCurrent().getId());


        List<PlaylistEntity> playlistsByName = allPlaylistEntities.stream()
                .filter(u -> u.getPlaylistTitle().equals(playlistTitle)).collect(Collectors.toList());

        if (playlistsByName.isEmpty()) {
            throw new PlaylistNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        } else {
            return playlistsByName;
        }
    }

    @Transactional
    public Playlist addSongToPlaylist(String playlistId, String songId) throws Exception {

        SongEntity addedSong = findSongById(songId);
        PlaylistEntity requiredPlaylist = findPlaylistById(playlistId);

        requiredPlaylist.getSongs().add(addedSong);

        return playlistConverter.fromEntity(playlistRepository.save(requiredPlaylist));
    }

    // добавить возможность просматривать песни у определенных плейлистов

    public List<PlaylistEntity> showUserPlaylistsSong(String playlistId) throws Exception {

        String currentUser = userDetailsService.getCurrent().getId();

        List<PlaylistEntity> playlist = playlistRepository.showUserPlaylistsWithSongs(currentUser, playlistId);

        return playlist;
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
    private final static String PLAYLIST_NOT_FOUND_EXC_MSG = "playlists doesnt exist";
}