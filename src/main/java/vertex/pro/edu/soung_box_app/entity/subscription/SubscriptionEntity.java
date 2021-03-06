package vertex.pro.edu.soung_box_app.entity.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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

@Entity
@Component
@Getter
@EqualsAndHashCode(exclude = "subscriptionSongs")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String subscriptionTo;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public SubscriptionEntity(String subscriptionTo, UserEntity user, LocalDateTime createdAt) {
        this.subscriptionTo = subscriptionTo;
        this.user = user;
        this.createdAt = createdAt;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "subscription_song",
            joinColumns = @JoinColumn(name = "subscription_id", nullable=false),
            inverseJoinColumns = @JoinColumn(name = "song_id", nullable=false))
    private Set<SongEntity> subscriptionSongs = new HashSet<>();
}
