package vertex.pro.edu.soung_box_app.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.repository.SubscriptionRepository;
import vertex.pro.edu.soung_box_app.service.song.SongService;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class SubscriptionService implements Subscribe{

    private final SongService songService;
    private final CustomUserDetailsService userDetailsService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity subscribeToArtist(String artist) throws Exception {

        List<SongEntity> songListByArtist = songService.findSongsByArtist(artist);
        System.out.println(songListByArtist);
        SubscriptionEntity subscription = findSubscriptionToSomething(artist);
        System.out.println(subscription);

        subscription.getSongs().addAll(songListByArtist);

        return subscription;
    }

    @Override
    public SubscriptionEntity subscribeToGenre(String genre) throws Exception {

        List<SongEntity> songListByGenre = songService.findSongsByArtist(genre);
        System.out.println(songListByGenre);
        SubscriptionEntity subscription = findSubscriptionToSomething(genre);

        subscription.getSongs().addAll(songListByGenre);
//        subscription.setSongs((Set<SongEntity>) songListByGenre);

        return subscription;
    }

    public SubscriptionEntity findSubscriptionToSomething(String subscriptionName) throws Exception {

        UserEntity user = userDetailsService.getCurrent();

        SubscriptionEntity requiredSubscription = subscriptionRepository.findByName(subscriptionName);

        return Objects.requireNonNullElseGet(requiredSubscription, () ->
                subscriptionRepository.save(new SubscriptionEntity(subscriptionName, user, LocalDateTime.now())));
    }
}
