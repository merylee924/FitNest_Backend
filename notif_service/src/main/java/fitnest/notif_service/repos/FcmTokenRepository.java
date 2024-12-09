package fitnest.notif_service.repos;

import fitnest.notif_service.entities.FcmToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmTokenRepository extends MongoRepository<FcmToken, Long> {
    FcmToken findByUserid(Long userid);
}
