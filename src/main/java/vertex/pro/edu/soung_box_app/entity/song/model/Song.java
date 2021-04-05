package vertex.pro.edu.soung_box_app.entity.song.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.Builder;
import lombok.Value;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;

import java.time.OffsetDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Value
@Builder
public class Song {

    String id;

    String title;

    String artist;

    String album;

    String genre;

    @JsonFormat(shape = STRING)
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    OffsetDateTime releaseDate;

    int likes;
}
