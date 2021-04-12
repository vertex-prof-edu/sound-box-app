package vertex.pro.edu.soung_box_app.service.subscription;

import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;

public interface Subscribe {

    SubscriptionEntity subscribe(String artist, String genre) throws Exception;

//    SubscriptionEntity subscribeToArtist(String artist) throws Exception;
//
//    SubscriptionEntity subscribeToGenre(String genre) throws Exception;
}
