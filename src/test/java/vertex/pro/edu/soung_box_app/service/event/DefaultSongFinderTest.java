package vertex.pro.edu.soung_box_app.service.event;

import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static vertex.pro.edu.soung_box_app.utils.prototypes.model.SongPrototypes.aSongList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultSongFinderTest {

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

        songFinder = new DefaultSongFinder(songRepository, songConverter);
    }

    @Test
    void returnsConvertedSongs() {
        List<Song> songs = songFinder.getSongs();

        assertThat(songs).containsExactlyInAnyOrderElementsOf(convertedSongs);

        verify(songRepository).findAll();

        verify(songConverter).fromEntities(songEntities);
    }
}