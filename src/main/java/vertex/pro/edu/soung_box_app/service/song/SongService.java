package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.exception.SongNotFoundException;
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

    @Override
    public List<Song> getSongs(String genre, String artist) {

        List<SongEntity> entities = songRepository.findByParams(genre, artist);

        return songConverter.fromEntities(entities);
    }

    // добавить увелечение числа общего количества лайков у песни
    // пользователь может лайкнуть только один раз
    @Transactional
    public String likeSong(String songId) throws Exception {

        SongEntity likedSong = playlistService.findSongById(songId);
        PlaylistEntity playlistLikes = playlistService.createDefaultPlaylist();

        if (playlistLikes.getSongs().contains(likedSong)) {
            throw new Exception("u have already liked this song");
        } else {
            int amountOfLikes = likedSong.getLikes();
            likedSong.setLikes(amountOfLikes + 1);

            playlistLikes.getSongs().add(likedSong);
            likedSong.getPlaylistEntities().add(playlistLikes);

            playlistRepository.save(playlistLikes);
            return "liked";
        }
    }

//    @Transactional
//    public String dislike(String songId) throws Exception {
//
//        SongEntity likedSong = playlistService.findSongById(songId);
//
//        playlistService.findPlaylistByName("likes")
//    }


    // добавить метод dislike(который будет убирать лайки и песни и удалять из плейлиста)
}
