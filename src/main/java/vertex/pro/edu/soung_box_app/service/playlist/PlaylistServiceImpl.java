package vertex.pro.edu.soung_box_app.service.playlist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.PlaylistConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.EntityNotFoundException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final SongRepository songRepository;
    private final PlaylistConverter playlistConverter;
    private final PlaylistRepository playlistRepository;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public PlaylistEntity createDefaultLikesPlaylist() {

        String playlistTitle = "likes";
        UserEntity user = userDetailsService.getCurrent();
        PlaylistEntity requiredPlaylist = playlistRepository.findByName(playlistTitle, user.getId());

        return Objects.requireNonNullElseGet(requiredPlaylist, () ->
                playlistRepository.save(new PlaylistEntity(playlistTitle, user, LocalDateTime.now())));
    }
    @Override
    public PlaylistEntity createDefaultDislikesPlaylist() {

        String playlistTitle = "dislikes";
        UserEntity user = userDetailsService.getCurrent();
        PlaylistEntity requiredPlaylist = playlistRepository.findByName(playlistTitle, user.getId());

        return Objects.requireNonNullElseGet(requiredPlaylist, () ->
                playlistRepository.save(new PlaylistEntity(playlistTitle, user, LocalDateTime.now())));
    }

    @Override
    public Playlist createPlaylist(String name) {

        PlaylistEntity playlistEntity = new PlaylistEntity(name, userDetailsService.getCurrent(), LocalDateTime.now());

        return playlistConverter.fromEntity(playlistRepository.save(playlistEntity));
    }

    public List<PlaylistEntity> showAllPlaylists() {

//        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//
//        Page<PlaylistEntity> playlistPage = playlistRepository.findAll(paging);
//
//        if (playlistPage.hasContent()) {
//            return playlistConverter.fromEntities(playlistPage.getContent());
//        } else {
//            return new ArrayList<>();
//        }

        List<PlaylistEntity> playlistEntities = playlistRepository.
                showAllUserPlaylists(userDetailsService.getCurrent().getId());

        if (playlistEntities.isEmpty()) {
            throw new EntityNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        } else {
            return playlistEntities;
        }
    }

    public PlaylistEntity findPlaylistsByName(String playlistTitle) {

        UserEntity user = userDetailsService.getCurrent();
        PlaylistEntity playlistsByName = playlistRepository.findByName(playlistTitle, user.getId());

        if (playlistsByName == null) {
            throw new EntityNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        } else {
            return playlistsByName;
        }
    }

    @Transactional
    public Playlist addSongToPlaylist(String playlistId, String songId) {

        SongEntity addedSong = findSongById(songId);
        PlaylistEntity requiredPlaylist = findPlaylistById(playlistId);

        requiredPlaylist.getPlaylistSongs().add(addedSong);

        PlaylistEntity updatedPlaylist = playlistRepository.save(requiredPlaylist);

        return playlistConverter.fromEntity(updatedPlaylist);
    }

    public PlaylistEntity findPlaylistById(String id) {

        if (playlistRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(PLAYLIST_NOT_FOUND_EXC_MSG);
        }
        return playlistRepository.findById(id).get();
    }

    public SongEntity findSongById(String id) {

        if (songRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(SONG_NOT_FOUND_EXC_MSG);
        }

        return songRepository.findById(id).get();
    }

    private final static String SONG_NOT_FOUND_EXC_MSG = "song doesnt exist";
    private final static String PLAYLIST_NOT_FOUND_EXC_MSG = "playlists doesnt exist";
}