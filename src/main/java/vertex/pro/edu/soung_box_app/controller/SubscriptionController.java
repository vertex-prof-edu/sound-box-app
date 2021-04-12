package vertex.pro.edu.soung_box_app.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;
import vertex.pro.edu.soung_box_app.service.subscription.SubscriptionService;

import static vertex.pro.edu.soung_box_app.controller.SubscriptionController.Links.SUBSCRIBE_TO_ARTIST;
import static vertex.pro.edu.soung_box_app.controller.SubscriptionController.Links.SUBSCRIBE_TO_GENRE;

@Slf4j
@Configuration
@RestController
@AllArgsConstructor
@RequestMapping("/user/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping(value = SUBSCRIBE_TO_ARTIST)
    SubscriptionEntity subscribeToArtist(@RequestParam("artist") String artist) throws Exception {
        log.info("Sub to songs this artist-: {}", artist);

        return subscriptionService.subscribeToArtist(artist);
    }

    @PostMapping(value = SUBSCRIBE_TO_GENRE)
    SubscriptionEntity subscribeToGenre(@RequestParam("genre") String genre) throws Exception {
        log.info("Sub to songs this genre-: {}", genre);

        return subscriptionService.subscribeToArtist(genre);
    }

    @UtilityClass
    public static class Links {
        public static final String SUBSCRIBE_TO_ARTIST = "/subscribeToArtist";
        public static final String SUBSCRIBE_TO_GENRE = "/subscribeToGenre";
    }
}
