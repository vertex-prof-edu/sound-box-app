package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.exception.SongAlreadyLikedException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistServiceImpl;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final PlaylistServiceImpl playlistServiceImpl;
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
    public String likeSong(String songId) {
        SongEntity likedSong = playlistServiceImpl.findSongById(songId);
        PlaylistEntity playlistLikes = playlistServiceImpl.createDefaultLikesPlaylist();

        if (playlistLikes.getPlaylistSongs().contains(likedSong)) {
            throw new SongAlreadyLikedException(SONG_ALREADY_LIKED);
        } else {
            int amountOfLikes = likedSong.getLikes();
            likedSong.setLikes(amountOfLikes + 1);

            playlistLikes.getPlaylistSongs().add(likedSong);

            playlistRepository.save(playlistLikes);
            return "liked";
        }
    }

    @Transactional
    public String dislike(String songId) {

        SongEntity needfulSong = playlistServiceImpl.findSongById(songId);

        PlaylistEntity likesPlaylist = playlistServiceImpl.findPlaylistsByName("likes");
        PlaylistEntity dislikePlaylist = playlistServiceImpl.createDefaultDislikesPlaylist();

        Set<SongEntity> allDislikedSongs = dislikePlaylist.getPlaylistSongs();
        Set<SongEntity> allLikedSongs = likesPlaylist.getPlaylistSongs();

        if (allLikedSongs.contains(needfulSong) & !allDislikedSongs.contains(needfulSong)) {
            needfulSong.setLikes(needfulSong.getLikes() - 1);

            allLikedSongs.removeIf(song -> song.equals(needfulSong));

            likesPlaylist.setPlaylistSongs(allLikedSongs);
            dislikePlaylist.getPlaylistSongs().add(needfulSong);

            playlistRepository.save(dislikePlaylist);
            playlistRepository.save(likesPlaylist);
        } else {
            throw new UnsupportedOperationException(SOMETHING_WRONG);
        }

        return "you dislike this song";
    }

    private static final String SONG_ALREADY_LIKED = "this songs has already liked";
    private static final String SOMETHING_WRONG = "something wrong";
}
