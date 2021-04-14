package vertex.pro.edu.soung_box_app.entity.playlist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Getter
@Builder
public class Playlist {
    String id;

    String playlist_title;

    Set<SongEntity> songs;

    LocalDateTime createdAt;
}
