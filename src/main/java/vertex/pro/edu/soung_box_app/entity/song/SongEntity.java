package vertex.pro.edu.soung_box_app.entity.song;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "songs")
public class SongEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String title;

    private String artist;

    private String album;

    private String genre;

    @CreationTimestamp
    private LocalDateTime releaseDate;

    private int likes;

    @Transient
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "songs")
    private Set<PlaylistEntity> playlistEntities = new HashSet<>();

    @Transient
    @ManyToMany(mappedBy = "songs")
    @EqualsAndHashCode.Exclude
    private Set<SubscriptionEntity> subscriptionEntities = new HashSet<>();

    public SongEntity(String name) {
        this.title = name;
    }
}