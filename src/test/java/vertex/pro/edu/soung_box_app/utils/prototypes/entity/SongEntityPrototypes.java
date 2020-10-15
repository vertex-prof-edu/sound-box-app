package vertex.pro.edu.soung_box_app.utils.prototypes.entity;

import lombok.experimental.UtilityClass;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class SongEntityPrototypes {

    public static List<SongEntity> aSongEntityList() {
        return List.of(aSongEntity());
    }

    public static SongEntity aSongEntity() {
        return SongEntity.builder()
                .title("title")
                .artist("artist")
                .album("album")
                .genre("genre")
                .releaseDate(LocalDateTime.parse("2020-01-01T21:00:00.000"))
                .build();
    }
}
