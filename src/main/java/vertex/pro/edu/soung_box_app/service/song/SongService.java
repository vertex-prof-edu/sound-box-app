package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.SongNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService implements SongFinder {

    private final SongConverter songConverter;
    private final SongRepository songRepository;
    private final PlaylistService playlistService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public List<Song> getSongs(String genre, String artist) {
        List<SongEntity> entities = songRepository.findByParams(genre, artist);

        return songConverter.fromEntities(entities);
    }

    public String likeSong(String songId) throws Exception {
//        UserEntity user = userDetailsService.getCurrent();
//        Optional<SongEntity> likedSong = songRepository.findById(songId);

        PlaylistEntity defaultPlaylist = playlistService.createDefaultPlaylist();

        SongEntity addedSong = playlistService.findSongById(songId);

        defaultPlaylist.getSongs().add(addedSong);
        addedSong.getPlaylistEntities().add(defaultPlaylist);

        return "you liked the song";
    }
}
