package vertex.pro.edu.soung_box_app.controller;

import org.junit.jupiter.api.Test;
import vertex.pro.edu.soung_box_app.common.utils.AbstractControllerTest;
import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.service.event.SongFinder;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vertex.pro.edu.soung_box_app.controller.SongController.Links.SONGS_BASE_URL;
import static vertex.pro.edu.soung_box_app.utils.prototypes.model.SongPrototypes.aSongList;

class SongControllerTest extends AbstractControllerTest<SongController> {

    private List<Song> songList;

    private SongFinder songFinder;
    private String genre;

    @Override
    protected SongController getControllerInstance() {
        genre = "Rock";
        songList = aSongList();

        songFinder = mock(SongFinder.class);
        when(songFinder.getSongs(any())).thenReturn(songList);

        return new SongController(songFinder);
    }

    @Test
    void returnsEvents() throws Exception {
        mockMvc().perform(get(SONGS_BASE_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getMapper().writeValueAsString(songList)));

        verify(songFinder).getSongs(null);
    }

    @Test
    void returnsEventsByGenre() throws Exception {
        mockMvc().perform(get(SONGS_BASE_URL)
                .param("genre", genre)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getMapper().writeValueAsString(songList)));

        verify(songFinder).getSongs(genre);
    }
}