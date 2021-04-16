package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vertex.pro.edu.soung_box_app.converter.subcsription.SubscriptionConverter;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.entity.subscription.model.Subscription;
import vertex.pro.edu.soung_box_app.service.subscription.SubscriptionService;

import java.util.List;

import static vertex.pro.edu.soung_box_app.controller.SubscriptionController.Links.*;

@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionConverter subscriptionConverter;

    @Transactional
    @PostMapping(value = SUBSCRIBE_TO_ARTIST)
    Subscription subscribeToArtist(@RequestParam("artist") String artist) throws Exception {
        log.info("Sub to songs this artist-: {}", artist);

        return subscriptionConverter.fromEntity(subscriptionService.subscribeToArtist(artist));
    }

    @Transactional
    @PostMapping(value = SUBSCRIBE_TO_GENRE)
    Subscription subscribeToGenre(@RequestParam("genre") String genre) throws Exception {
        log.info("Sub to songs this genre-: {}", genre);

        return subscriptionConverter.fromEntity(subscriptionService.subscribeToGenre(genre));
    }

    @GetMapping(value = SHOW_ALL_SUBSCRIPTION)
    List<Subscription> showAllUserSubscription() throws Exception{
        return subscriptionConverter.fromEntities(subscriptionService.showAllUserSubscription());
    }

    @UtilityClass
    public static class Links {
        public static final String SUBSCRIBE_TO_ARTIST = "/subscribeToArtist";
        public static final String SUBSCRIBE_TO_GENRE = "/subscribeToGenre";
        public static final String SHOW_ALL_SUBSCRIPTION = "/showAllSubscription";
    }
}
