package vertex.pro.edu.soung_box_app.service.subscription;

import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;

public interface Subscribe {

    SubscriptionEntity subscribeToArtist(String artist) throws Exception;

    SubscriptionEntity subscribeToGenre(String genre) throws Exception;

    String unsubscribeFromArtist(String artist) throws Exception;

    String unsubscribeFromGenre(String genre) throws Exception;
}
