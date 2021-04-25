package vertex.pro.edu.soung_box_app.entity.song;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value= {"playlistEntities", "subscriptionEntities"})
@Table(name = "songs")
public class SongEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @EqualsAndHashCode.Include
    private String id;

    @EqualsAndHashCode.Include
    private String title;

    @EqualsAndHashCode.Include
    private String artist;

    @EqualsAndHashCode.Include
    private String album;

    @EqualsAndHashCode.Include
    private String genre;

    @CreationTimestamp
    @EqualsAndHashCode.Include
    private LocalDateTime releaseDate;

    @EqualsAndHashCode.Include
    private int likes;

    @ManyToMany(mappedBy = "playlistSongs")
    private Set<PlaylistEntity> playlistEntities = new HashSet<>();

    @ManyToMany(mappedBy = "subscriptionSongs")
    private Set<SubscriptionEntity> subscriptionEntities = new HashSet<>();

    public SongEntity(String name) {
        this.title = name;
    }
}