package vertex.pro.edu.soung_box_app.service.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SongServiceTest {

//    private List<Song> convertedSongs;
//    private List<SongEntity> songEntities;
//
//    private SongConverter songConverter;
//    private SongRepository songRepository;
//
//    private SongFinder songFinder;
//
//    @BeforeEach
//    void setUp() {
//        convertedSongs = aSongList();
//        songEntities = Collections.emptyList();
//
//        songConverter = mock(SongConverter.class);
//        when(songConverter.fromEntities(anyList())).thenReturn(convertedSongs);
//
//        songRepository = mock(SongRepository.class);
//        when(songRepository.findAll()).thenReturn(songEntities);
//
//        songFinder = new SongService(songRepository, songConverter);
//    }
//
//    @Test
//    void returnsConvertedSongs() {
//        List<Song> songs = songFinder.getSongs(null, null);
//
//        assertThat(songs).containsExactlyInAnyOrderElementsOf(convertedSongs);
//
//        verify(songRepository).findByParams(null, null);
//
//        verify(songConverter).fromEntities(songEntities);
//    }
//
//    @Test
//    void returnsSongsByGenre() {
//        String genre = "Rock";
//        songFinder.getSongs(genre, null);
//
//        verify(songRepository).findByParams(genre, null);
//    }
//
//    @Test
//    void returnsSongsByArtist() {
//        String artist = "MONATIK";
//        songFinder.getSongs(null, artist);
//
//        verify(songRepository).findByParams(null, artist);
//    }
//
//    @Test
//    void returnsSongsByGenreAndArtist() {
//        String genre = "Rock";
//        String artist = "MONATIK";
//        songFinder.getSongs(genre, artist);
//
//        verify(songRepository).findByParams(genre, artist);
//    }
}