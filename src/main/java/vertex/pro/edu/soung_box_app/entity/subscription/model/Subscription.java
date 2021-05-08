package vertex.pro.edu.soung_box_app.entity.subscription.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Value
@Getter
@Builder
public class Subscription {

    String id;

    String subscriptionTo;

    Set<SongEntity> songs;

    LocalDateTime createdAt;
}
