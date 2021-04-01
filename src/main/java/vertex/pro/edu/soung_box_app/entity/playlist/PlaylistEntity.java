package vertex.pro.edu.soung_box_app.entity.playlist;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Component
@Getter
@EqualsAndHashCode(exclude = "songs")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlists")
public class PlaylistEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private LocalDateTime createdAt;

    public PlaylistEntity(String name, UserEntity user, LocalDateTime createdAt) {
        this.name = name;
        this.user = user;
        this.createdAt = createdAt;
    }

    @Transient
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "playlist_song",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"))
    private Set<SongEntity> songs;

    public PlaylistEntity(String name, UserEntity user, LocalDateTime createdAt, SongEntity... songs) {
        this.name = name;
        this.user = user;
        this.createdAt = createdAt;
        this.songs = Stream.of(songs).collect(Collectors.toSet());
        this.songs.forEach(x -> x.getPlaylistEntities().add(this));
    }
}
