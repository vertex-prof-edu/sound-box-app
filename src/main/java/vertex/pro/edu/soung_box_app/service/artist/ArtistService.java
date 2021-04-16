package vertex.pro.edu.soung_box_app.service.artist;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.controller.request_body.SongRequest;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;
import vertex.pro.edu.soung_box_app.exception.SongNotFoundException;
import vertex.pro.edu.soung_box_app.exception.UserHasAlreadyBecomeAnArtistException;
import vertex.pro.edu.soung_box_app.exception.UserNotAnArtistException;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.security.jwt.JwtProvider;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ArtistService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final CustomUserDetailsService userDetailsService;

    @Transactional
    @Modifying(clearAutomatically=true)
    public String becameAnArtist() throws Exception {

        UserEntity user = userDetailsService.getCurrent();
        UserRole userRole = user.getUserRole();

        if (Objects.equals(userRole, UserRole.ARTIST)) {
            throw new UserHasAlreadyBecomeAnArtistException(YOU_ARE_ARTIST);
        } else {
            user.setUserRole(UserRole.ARTIST);

            userRepository.save(user);

            return jwtProvider.generateToken(user.getUsername());
        }
    }

    public SongEntity addNewSong(SongRequest song) throws Exception {

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

    public List<SongEntity> showAllArtistSongs() throws Exception {

        UserEntity artist = checkingUserRole();

        List<SongEntity> songs = songRepository.findByArtist(artist.getUsername());

        if (songs.isEmpty()) {
            throw new SongNotFoundException(ARTIST_NOT_ADDED_SONGS);
        }

        return songs;
    }

    public List<SongEntity> showArtistStatistics() throws Exception {
        UserEntity artist = checkingUserRole();

        return songRepository.showSongsStatistics(artist.getUsername());
    }

    public UserEntity checkingUserRole() throws Exception {
        UserEntity user = userDetailsService.getCurrent();

        UserRole userRole = user.getUserRole();

        if (Objects.equals(userRole, UserRole.USER)) {
            throw new UserNotAnArtistException(USER_NOT_AN_ARTIST_EXC);
        } else {
            return user;
        }
    }

    private final static String YOU_ARE_ARTIST= "User has already become an artist";
    private final static String ARTIST_NOT_ADDED_SONGS= "User hadn't add any songs";
    private final static String USER_NOT_AN_ARTIST_EXC = "user is not an artist";
}
