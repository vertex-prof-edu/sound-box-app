package vertex.pro.edu.soung_box_app.entity.playlist;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.User;

import javax.persistence.*;

@Data
@Entity
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Playlist (String name, User user) {
        this.name = name;
        this.user = user;
    }
}
