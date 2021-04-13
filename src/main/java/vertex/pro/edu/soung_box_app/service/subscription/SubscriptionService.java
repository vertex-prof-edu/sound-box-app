package vertex.pro.edu.soung_box_app.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.NoSubscriptionException;
import vertex.pro.edu.soung_box_app.repository.SubscriptionRepository;
import vertex.pro.edu.soung_box_app.service.song.SongService;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SubscriptionService implements Subscribe{

    private final SongService songService;
    private final CustomUserDetailsService userDetailsService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity subscribeToArtist(String artist) throws Exception {

        List<SongEntity> songListByArtist = songService.findSongsByArtist(artist);
        SubscriptionEntity subscription = findUserSubscriptionToSomething(artist);

        subscription.getSongs().addAll(songListByArtist);

        return subscription;
    }

    @Override
    public SubscriptionEntity subscribeToGenre(String genre) throws Exception {

        List<SongEntity> songListByGenre = songService.findSongsByGenre(genre);
        SubscriptionEntity subscription = findUserSubscriptionToSomething(genre);

        subscription.getSongs().addAll(songListByGenre);

        return subscription;
    }

    public List<SubscriptionEntity> showAllUserSubscription() throws Exception {

        String userId = userDetailsService.getCurrent().getId();

        List<SubscriptionEntity> allSubscription = subscriptionRepository.showAllUserSubscription(userId);

        if (allSubscription.isEmpty()) {
            throw new NoSubscriptionException(NO_SUBSCRIPTION);
        } else {
            return allSubscription;
        }
    }

    public SubscriptionEntity findUserSubscriptionToSomething(String subscriptionName) throws Exception {

        UserEntity user = userDetailsService.getCurrent();

        SubscriptionEntity requiredSubscription = subscriptionRepository.findByName(subscriptionName);

        return Objects.requireNonNullElseGet(requiredSubscription, () ->
                subscriptionRepository.save(new SubscriptionEntity(subscriptionName, user, LocalDateTime.now())));
    }

    private final static String NO_SUBSCRIPTION = "You have no subscriptions";
}
