package vertex.pro.edu.soung_box_app.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserRole;
import vertex.pro.edu.soung_box_app.exception.NoSubscriptionException;
import vertex.pro.edu.soung_box_app.repository.SubscriptionRepository;
import vertex.pro.edu.soung_box_app.repository.UserRepository;
import vertex.pro.edu.soung_box_app.service.song.SongService;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SubscriptionService implements Subscribe{

    private final SongService songService;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public SubscriptionEntity subscribeToArtist(String artistName) throws Exception {

        UserEntity user = userDetailsService.getCurrent();
        UserEntity artist = userRepository.findByUsername(artistName);

        if (user.getUsername().equals(artistName) || artist.getUserRole().equals(UserRole.USER)) {
            throw new Exception("u cannot sub");
        } else {
            List<SongEntity> songListByArtist = songService.findSongsByArtist(artistName);

            if (songListByArtist.isEmpty()) {
                throw new Exception("such artist doesnt have any songs");
            }
            SubscriptionEntity subscription = findUserSubscriptionToSomething(artistName);

            int amountOfSubscribers = artist.getSubscribers();
            user.setSubscribers(amountOfSubscribers + 1);

            for (SongEntity song: songListByArtist) {
                subscription.getSubscriptionSongs().add(song);
            }
//            subscription.getSubscriptionSongs().addAll(songListByArtist);

            return subscriptionRepository.save(subscription);
        }
    }

    @Override
    @Transactional
    public SubscriptionEntity subscribeToGenre(String genre) {

        List<SongEntity> songListByGenre = songService.findSongsByGenre(genre);
        SubscriptionEntity subscription = findUserSubscriptionToSomething(genre);

        for (SongEntity song: songListByGenre) {
            subscription.getSubscriptionSongs().add(song);
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public String unsubscribeFromArtist(String artistName) {

        UserEntity user = userDetailsService.getCurrent();
        SubscriptionEntity subscription = findUserSubscriptionToSomething(artistName);

        UserEntity artist = userRepository.findByUsername(artistName);

        int amountOfSubscribers = artist.getSubscribers();
        if (amountOfSubscribers == 0) {
            artist.setSubscribers(amountOfSubscribers);
        } else {
            artist.setSubscribers(amountOfSubscribers - 1);
        }

        subscriptionRepository.unsubscribe(artistName, user.getId());

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

        SubscriptionEntity subscription = subscriptionRepository.findByName(subscriptionName, user.getId());

        return Objects.requireNonNullElseGet(subscription, () ->
                subscriptionRepository.save(new SubscriptionEntity(subscriptionName, user, LocalDateTime.now())));
    }

    private final static String NO_SUBSCRIPTION = "You have no subscriptions";
}
