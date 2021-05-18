package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.converter.SubscriptionConverter;
import vertex.pro.edu.soung_box_app.entity.subscription.model.Subscription;
import vertex.pro.edu.soung_box_app.service.subscription.SubscriptionServiceImpl;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SubscriptionController.Links.*;

@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/subscription")
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionServiceImpl;
    private final SubscriptionConverter subscriptionConverter;

    @PostMapping(value = SUBSCRIBE_TO_ARTIST)
    Subscription subscribeToArtist(@RequestParam("artist") String artist) throws Exception {
        log.info("Sub to songs this artist-: {}", artist);

        return subscriptionConverter.fromEntity(subscriptionServiceImpl.subscribeToArtist(artist));
    }

    @PostMapping(value = UNSUBSCRIBE_FROM_ARTIST)
    String unsubscribeFromArtist(@RequestParam("artist") String artist) {
        log.info("unsub to songs this artist-: {}", artist);

        return subscriptionServiceImpl.unsubscribeFromArtist(artist);
    }

    @PostMapping(value = SUBSCRIBE_TO_GENRE)
    Subscription subscribeToGenre(@RequestParam("genre") String genre) {
        log.info("Sub to songs this genre-: {}", genre);

        return subscriptionConverter.fromEntity(subscriptionServiceImpl.subscribeToGenre(genre));
    }

    @PostMapping(value = UNSUBSCRIBE_FROM_GENRE)
    String unsubscribeFromGenre(@RequestParam("genre") String genre) {
        log.info("unsub to songs this genre-: {}", genre);

        return subscriptionServiceImpl.unsubscribeFromGenre(genre);
    }

    @GetMapping(value = SHOW_ALL_SUBSCRIPTION)
    List<Subscription> showAllUserSubscription() {
        return subscriptionConverter.fromEntities(subscriptionServiceImpl.showAllUserSubscription());
    }

    @UtilityClass
    public static class Links {
        public static final String SUBSCRIBE_TO_ARTIST = "/subscribeToArtist";
        public static final String SUBSCRIBE_TO_GENRE = "/subscribeToGenre";
        public static final String SHOW_ALL_SUBSCRIPTION = "/showAllSubscription";
        public static final String UNSUBSCRIBE_FROM_ARTIST = "/unsubscribeFromArtist";
        public static final String UNSUBSCRIBE_FROM_GENRE = "/unsubscribeFromGenre";
    }
}
