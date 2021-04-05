package vertex.pro.edu.soung_box_app.entity.playlist.model;

import lombok.Builder;
import lombok.Value;
import vertex.pro.edu.soung_box_app.entity.song.model.Song;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Value
@Builder
public class Playlist {
    String id;

    String name;

    Set<Song> songs;
}
