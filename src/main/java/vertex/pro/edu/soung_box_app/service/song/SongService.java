package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService implements SongFinder {

    private final SongRepository songRepository;
    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;

    @Override
    public List<SongEntity> getSongs(String genre, String artist) {

        return songRepository.findByParams(genre, artist);
    }

    @Override
    public List<SongEntity> findSongsByArtist(String artist) {

        return songRepository.findByArtist(artist);
    }

    @Override
    public List<SongEntity> findSongsByGenre(String genre) {

        return songRepository.findByGenre(genre);
    }

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


}
