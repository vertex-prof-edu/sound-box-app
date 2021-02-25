package vertex.pro.edu.soung_box_app.entity.playlist_song;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlist_song")
public class PlaylistSong {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToMany
    @JoinColumn(nullable = false, name = "song_id")
    private Set<SongEntity> song;

    @ManyToMany
    @JoinColumn(nullable = false, name = "playlist_id")
    private Set<Playlist> playlist;
}
