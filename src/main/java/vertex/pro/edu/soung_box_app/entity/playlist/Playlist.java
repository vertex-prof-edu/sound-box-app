package vertex.pro.edu.soung_box_app.entity.playlist;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.User;

import javax.persistence.*;

@Data
@Entity
@Builder
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
