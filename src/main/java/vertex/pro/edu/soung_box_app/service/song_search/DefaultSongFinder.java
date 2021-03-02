package vertex.pro.edu.soung_box_app.service.song_search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultSongFinder implements SongFinder {

    private final SongRepository songRepository;
    private final SongConverter songConverter;

    @Override
    public List<Song> getSongs(String genre, String artist) {
        List<SongEntity> entities = songRepository.findByParams(genre, artist);
        return songConverter.fromEntities(entities);
    }
}