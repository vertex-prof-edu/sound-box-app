package vertex.pro.edu.soung_box_app.service.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import vertex.pro.edu.soung_box_app.service.song.SongService;
import vertex.pro.edu.soung_box_app.service.song.SongFinder;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static vertex.pro.edu.soung_box_app.utils.prototypes.model.SongPrototypes.aSongList;

class SongServiceTest {

    private List<Song> convertedSongs;
    private List<SongEntity> songEntities;

    private SongConverter songConverter;
    private SongRepository songRepository;

    private SongFinder songFinder;

    @BeforeEach
    void setUp() {
        convertedSongs = aSongList();
        songEntities = Collections.emptyList();

        songConverter = mock(SongConverter.class);
        when(songConverter.fromEntities(anyList())).thenReturn(convertedSongs);

        songRepository = mock(SongRepository.class);
        when(songRepository.findAll()).thenReturn(songEntities);

        songFinder = new SongService(songRepository, songConverter);
    }

    @Test
    void returnsConvertedSongs() {
        List<Song> songs = songFinder.getSongs(null, null);

        assertThat(songs).containsExactlyInAnyOrderElementsOf(convertedSongs);

        verify(songRepository).findByParams(null, null);

        verify(songConverter).fromEntities(songEntities);
    }

    @Test
    void returnsSongsByGenre() {
        String genre = "Rock";
        songFinder.getSongs(genre, null);

        verify(songRepository).findByParams(genre, null);
    }

    @Test
    void returnsSongsByArtist() {
        String artist = "MONATIK";
        songFinder.getSongs(null, artist);

        verify(songRepository).findByParams(null, artist);
    }

    @Test
    void returnsSongsByGenreAndArtist() {
        String genre = "Rock";
        String artist = "MONATIK";
        songFinder.getSongs(genre, artist);

        verify(songRepository).findByParams(genre, artist);
    }
}