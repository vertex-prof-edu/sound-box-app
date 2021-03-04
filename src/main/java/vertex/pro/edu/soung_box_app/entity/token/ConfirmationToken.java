package vertex.pro.edu.soung_box_app.entity.token;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Component
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, UserEntity user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
