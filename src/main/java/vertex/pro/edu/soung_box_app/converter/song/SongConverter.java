package vertex.pro.edu.soung_box_app.converter.song;

import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SongConverter {
    public List<Song> fromEntities(List<SongEntity> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public Song fromEntity(SongEntity entity) {
        return Song.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .artist(entity.getArtist())
                .genre(entity.getGenre())
                .album(entity.getAlbum())
                .releaseDate(entity.getReleaseDate().atOffset(ZoneOffset.UTC))
                .likes(entity.getLikes())
                .build();
    }
}
