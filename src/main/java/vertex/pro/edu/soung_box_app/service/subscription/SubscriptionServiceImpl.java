package vertex.pro.edu.soung_box_app.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.EntityNotFoundException;
import vertex.pro.edu.soung_box_app.exception.NoSubscriptionException;
import vertex.pro.edu.soung_box_app.exception.UserException;
import vertex.pro.edu.soung_box_app.repository.SubscriptionRepository;
import vertex.pro.edu.soung_box_app.service.song.SongServiceImpl;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SongServiceImpl songServiceImpl;
    private final CustomUserDetailsService userDetailsService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public SubscriptionEntity subscribeToArtist(String artist) {

        UserEntity user = userDetailsService.getCurrent();

        if (user.getUsername().equals(artist)) {
            throw new UserException(SUBSCRIPTION_ERROR);
        } else {
            List<SongEntity> songListByArtist = songServiceImpl.findSongsByArtist(artist);

            if (songListByArtist.isEmpty()) {
                throw new EntityNotFoundException(ARTIST_SUBSCRIPTION_ERROR);
            }
            SubscriptionEntity subscription = findUserSubscriptionToSomething(artist);

            int amountOfSubscribers = user.getSubscribers();
            user.setSubscribers(amountOfSubscribers + 1);

            for (SongEntity song: songListByArtist) {
                subscription.getSubscriptionSongs().add(song);
            }

            return subscriptionRepository.save(subscription);
        }
    }

    @Override
    @Transactional
    public SubscriptionEntity subscribeToGenre(String genre) {

        List<SongEntity> songListByGenre = songServiceImpl.findSongsByGenre(genre);
        SubscriptionEntity subscription = findUserSubscriptionToSomething(genre);

        for (SongEntity song: songListByGenre) {
            subscription.getSubscriptionSongs().add(song);
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public String unsubscribeFromArtist(String artist) {

        UserEntity user = userDetailsService.getCurrent();
        SubscriptionEntity subscription = findUserSubscriptionToSomething(artist);

        int amountOfSubscribers = user.getSubscribers();
        if (amountOfSubscribers == 0) {
            user.setSubscribers(amountOfSubscribers);
        } else {
            user.setSubscribers(amountOfSubscribers - 1);
        }

        subscriptionRepository.unsubscribe(artist, user.getId());

        subscriptionRepository.save(subscription);

        return "successfully unsubscribe from " + artist;
    }

    @Override
    @Transactional
    public String unsubscribeFromGenre(String genre) {

        UserEntity user = userDetailsService.getCurrent();
        SubscriptionEntity subscription = findUserSubscriptionToSomething(genre);

        subscriptionRepository.unsubscribe(genre, user.getId());

        subscriptionRepository.save(subscription);

        return "successfully unsubscribe from " + genre;
    }

    public List<SubscriptionEntity> showAllUserSubscription() {

        String userId = userDetailsService.getCurrent().getId();

        List<SubscriptionEntity> allSubscription = subscriptionRepository.showAllUserSubscription(userId);

        if (allSubscription.isEmpty()) {
            throw new NoSubscriptionException(NO_SUBSCRIPTION);
        } else {
            return allSubscription;
        }
    }

    public SubscriptionEntity findUserSubscriptionToSomething(String subscriptionName) {

        UserEntity user = userDetailsService.getCurrent();

        SubscriptionEntity requiredSubscription = subscriptionRepository.findByName(subscriptionName, user.getId());

        return Objects.requireNonNullElseGet(requiredSubscription, () ->
                subscriptionRepository.save(new SubscriptionEntity(subscriptionName, user, LocalDateTime.now())));
    }

    private final static String NO_SUBSCRIPTION = "You have no subscriptions";
    private final static String SUBSCRIPTION_ERROR = "You cannot subscribe to yourself";
    private final static String ARTIST_SUBSCRIPTION_ERROR = "such artist doesnt have any songs";
}
