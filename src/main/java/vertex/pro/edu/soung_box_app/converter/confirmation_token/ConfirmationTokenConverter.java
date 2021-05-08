package vertex.pro.edu.soung_box_app.converter.confirmation_token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationTokenEntity;
import vertex.pro.edu.soung_box_app.entity.token.model.ConfirmationToken;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConfirmationTokenConverter {
    public List<ConfirmationToken> fromEntities(List<ConfirmationTokenEntity> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public ConfirmationToken fromEntity(ConfirmationTokenEntity entity) {
        return ConfirmationToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .createdAt(entity.getCreatedAt())
                .expiresAt(entity.getExpiresAt())
                .confirmedAt(entity.getConfirmedAt())
                .build();
    }

}
