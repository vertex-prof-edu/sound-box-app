package vertex.pro.edu.soung_box_app.service.song;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.song.SongConverter;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import vertex.pro.edu.soung_box_app.repository.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService implements SongFinder {

    private final SongRepository songRepository;
    private final SongConverter songConverter;

    @Override
    public List<Song> getSongs(String genre, String artist) {
        List<SongEntity> entities = songRepository.findByParams(genre, artist);

        return songConverter.fromEntities(entities);
    }

    public void likeSong(String id) {

    }

    public Song findSongById(String id) {
        SongEntity song = songRepository.getOne(id);

        return songConverter.fromEntity(song);
    }
}
