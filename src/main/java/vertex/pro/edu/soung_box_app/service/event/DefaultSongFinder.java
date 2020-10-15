package vertex.pro.edu.soung_box_app.service.event;

import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.model.song.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultSongFinder implements SongFinder {

    private final SongRepository songRepository;
    private final SongConverter songConverter;

    @Override
    public List<Song> getSongs() {
        return songConverter.fromEntities(songRepository.findAll());
    }
}
