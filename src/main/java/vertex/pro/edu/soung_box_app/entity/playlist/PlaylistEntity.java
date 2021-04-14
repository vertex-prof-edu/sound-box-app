package vertex.pro.edu.soung_box_app.entity.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String playlistTitle;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private LocalDateTime createdAt;

    public PlaylistEntity(String playlistTitle, UserEntity user, LocalDateTime createdAt) {
        this.playlistTitle = playlistTitle;
        this.user = user;
        this.createdAt = createdAt;
    }

    @Transient
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id", nullable=false),
            inverseJoinColumns = @JoinColumn(name = "song_id", nullable=false))
    private Set<SongEntity> songs = new HashSet<>();

}
