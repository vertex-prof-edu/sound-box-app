package vertex.pro.edu.soung_box_app.converter.playlist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaylistConverter {
    public List<Playlist> fromEntities(List<PlaylistEntity> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public Playlist fromEntity(PlaylistEntity entity) {
        return Playlist.builder().
                id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
