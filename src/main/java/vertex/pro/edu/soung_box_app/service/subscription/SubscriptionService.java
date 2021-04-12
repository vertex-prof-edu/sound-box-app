package vertex.pro.edu.soung_box_app.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.repository.SubscriptionRepository;
import vertex.pro.edu.soung_box_app.service.song.SongService;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionService implements Subscribe{

    private final SongService songService;
    private final CustomUserDetailsService userDetailsService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity subscribe(String artist, String genre) throws Exception {

        List<SongEntity> requiredSongList = songService.getSongs(artist, genre);




        return null;
    }

    public SubscriptionEntity findSubscriptionToSomething(String subscriptionName) throws Exception {

        UserEntity user = userDetailsService.getCurrent();

        SubscriptionEntity requiredSubscription = subscriptionRepository.findByName(subscriptionName);

        return Objects.requireNonNullElseGet(requiredSubscription, () ->
                subscriptionRepository.save(new SubscriptionEntity(subscriptionName, user)));
    }
}
