package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService implements SongFinder {

    private final SongConverter songConverter;
    private final SongRepository songRepository;
    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;
    private final CustomUserDetailsService userDetailsService;

    @Override
    public List<Song> getSongs(String genre, String artist) {
        List<SongEntity> entities = songRepository.findByParams(genre, artist);

        return songConverter.fromEntities(entities);
    }

    // добавить увелечение числа общего количества лайков у песни
    // пользователь может лайкнуть только один раз
    @Transactional
    public void likeSong(String songId) throws Exception {
        SongEntity likedSong = playlistService.findSongById(songId);
        System.out.println(likedSong);
        PlaylistEntity playlistLikes = playlistService.createDefaultPlaylist();

        playlistLikes.getSongs().add(likedSong);
        System.out.println(playlistLikes);
    }
}
