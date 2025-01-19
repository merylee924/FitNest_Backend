package fitnest.notif_service.repos;

import fitnest.notif_service.entities.Notif;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotifRepo extends MongoRepository<Notif,Long> {
    List<Notif> findByRecipient(Long userid);
}
