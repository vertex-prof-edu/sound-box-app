package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.SongAlreadyLikedException;
import vertex.pro.edu.soung_box_app.repository.PlaylistRepository;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;
import vertex.pro.edu.soung_box_app.service.playlist.PlaylistService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SongService implements SongFinder {

    private final SongRepository songRepository;
    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;
    private final CustomUserDetailsService userDetailsService;

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
    public String dislike(String songId) throws Exception {

        SongEntity likedSong = playlistService.findSongById(songId);
        PlaylistEntity likesPlaylist = playlistService.findPlaylistsByName("likes");
        Set<SongEntity> allLikedSong = likesPlaylist.getPlaylistSongs();

        if (likesPlaylist.getPlaylistSongs().contains(likedSong)) {
            likedSong.setLikes(likedSong.getLikes() - 1);

            likesPlaylist.getPlaylistSongs().remove(likedSong);

//            System.out.println(likesPlaylist);
            playlistRepository.save(likesPlaylist);
        } else {
            throw new Exception("no such song");
        }

        return "you dislike this song";

    }

    private static final String SONG_ALREADY_LIKED = "this songs has already liked";
}
