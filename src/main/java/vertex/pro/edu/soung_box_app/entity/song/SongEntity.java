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

@Entity
@Builder
@Getter
@Setter
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

    private String title;

    private String artist;

    private String album;

    private String genre;

    @CreationTimestamp
    private LocalDateTime releaseDate;

    private int likes;

    @ManyToMany(mappedBy = "playlistSongs")
    private Set<PlaylistEntity> playlistEntities = new HashSet<>();

    @ManyToMany(mappedBy = "subscriptionSongs")
    private Set<SubscriptionEntity> subscriptionEntities = new HashSet<>();

    public SongEntity(String name) {
        this.title = name;
    }
}