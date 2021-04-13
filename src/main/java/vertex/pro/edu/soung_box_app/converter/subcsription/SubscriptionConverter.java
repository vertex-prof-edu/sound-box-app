package vertex.pro.edu.soung_box_app.converter.subcsription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.model.Subscription;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubscriptionConverter {

    public List<Subscription> fromEntities(List<SubscriptionEntity> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public Subscription fromEntity(SubscriptionEntity entity) {
        return Subscription.builder().
                id(entity.getId()).
                subscriptionTo(entity.getSubscriptionTo()).
                songs(entity.getSongs()).
                createdAt(entity.getCreatedAt()).
                build();
    }
}
