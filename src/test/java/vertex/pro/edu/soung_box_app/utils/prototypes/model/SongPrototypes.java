package vertex.pro.edu.soung_box_app.utils.prototypes.model;

import vertex.pro.edu.soung_box_app.model.song.Song;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class SongPrototypes {

    public static List<Song> aSongList() {
        return List.of(aSong());
    }

    public static Song aSong() {
        return Song.builder()
                .id(UUID.randomUUID().toString())
                .title("title")
                .artist("artist")
                .album("album")
                .genre("genre")
                .releaseDate(OffsetDateTime.parse("2020-01-01T21:00:00.000Z"))
                .build();
    }
}
