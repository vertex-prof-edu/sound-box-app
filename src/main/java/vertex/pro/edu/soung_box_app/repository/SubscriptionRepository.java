package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vertex.pro.edu.soung_box_app.entity.subscription.SubscriptionEntity;

import java.util.List;

@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<SubscriptionEntity, String> {

    @Query(value = "select * from subscription where subscription_to = :subscription_to", nativeQuery = true)
    SubscriptionEntity findByName(@Param("subscription_to") String subscription_to);

    @Query(value = "select * from subscription where user_id = :id", nativeQuery = true)
    List<SubscriptionEntity> showAllUserSubscription(@Param("id") String id);
}
