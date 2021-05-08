package vertex.pro.edu.soung_box_app.service.artist;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.controller.request_body.SongRequest;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;
import vertex.pro.edu.soung_box_app.exception.EntityNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserException;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ArtistService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final CustomUserDetailsService userDetailsService;

    @Transactional
    @Modifying(clearAutomatically=true)
    public String becameAnArtist() {

        UserEntity user = userDetailsService.getCurrent();
        UserRole userRole = user.getUserRole();

        if (Objects.equals(userRole, UserRole.ARTIST)) {
            throw new UserException(YOU_ARE_ARTIST);
        } else {
            user.setUserRole(UserRole.ARTIST);
            user.setSubscribers(0);

            userRepository.save(user);

            return jwtProvider.generateToken(user.getUsername());
        }
    }

    public SongEntity addNewSong(SongRequest song) {

        UserEntity artist = checkingUserRole();
        LocalDateTime releaseDate = LocalDateTime.now();
        int likes = 0;

        SongEntity newSong = SongEntity.builder()
                .title(song.getTitle())
                .album(song.getAlbum())
                .artist(artist.getUsername())
                .genre(song.getGenre())
                .likes(likes)
                .releaseDate(releaseDate)
                .build();

        songRepository.save(newSong);

        return newSong;
    }

    @Transactional
    public String removeAddedSong(String songId) {

        Optional<SongEntity> song = songRepository.findById(songId);

        song.ifPresent(songRepository::delete);

        return "successfully delete " + song.get().getTitle();
    }

    public List<SongEntity> showAllArtistSongs() {

        UserEntity artist = checkingUserRole();

        List<SongEntity> songs = songRepository.findByArtist(artist.getUsername());

        if (songs.isEmpty()) {
            throw new EntityNotFoundException(ARTIST_NOT_ADDED_SONGS);
        }

        return songs;
    }

    public Map<String, Integer> showSongsStatistics() {

        UserEntity user = checkingUserRole();

        List<SongEntity> userSongs = songRepository.findByArtist(user.getUsername());
        Map<String, Integer> nameLikeSongs = new HashMap<>();

        for (SongEntity song: userSongs) {
            nameLikeSongs.put(song.getTitle(), song.getLikes());
        }

        return nameLikeSongs;
    }

    public Map<String, Integer> showSubscriptionStatistics() {

        UserEntity user = userDetailsService.getCurrent();
        Map<String, Integer> artistSubs = new HashMap<>();

        artistSubs.put("Subscribes ", user.getSubscribers());

        return artistSubs;
    }

    public UserEntity checkingUserRole() {
        UserEntity user = userDetailsService.getCurrent();

        UserRole userRole = user.getUserRole();

        if (Objects.equals(userRole, UserRole.USER)) {
            throw new UserException(USER_NOT_AN_ARTIST_EXC);
        } else {
            return user;
        }
    }

    private final static String YOU_ARE_ARTIST= "User has already become an artist";
    private final static String ARTIST_NOT_ADDED_SONGS= "User hadn't add any songs";
    private final static String USER_NOT_AN_ARTIST_EXC = "user is not an artist";
}
