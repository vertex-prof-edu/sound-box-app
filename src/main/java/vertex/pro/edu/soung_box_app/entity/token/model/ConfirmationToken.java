package vertex.pro.edu.soung_box_app.entity.token.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ConfirmationToken {
    String id;

    String token;

    LocalDateTime createdAt;

    LocalDateTime expiresAt;

    LocalDateTime confirmedAt;
}
