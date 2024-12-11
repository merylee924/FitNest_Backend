package fitnest.notif_service.services;

import fitnest.notif_service.dto.NotifRequest;
import fitnest.notif_service.entities.Notif;
import fitnest.notif_service.repos.NotifRepo;

import java.util.List;

public interface INotificationStorageServicee {
    public void storeNotification(NotifRequest request ) ;
    public List<Notif> getNotificationsForRecipient(Long recipientId) ;

}
