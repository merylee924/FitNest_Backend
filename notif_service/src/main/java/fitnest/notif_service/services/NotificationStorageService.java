package fitnest.notif_service.services;

import fitnest.notif_service.dto.NotifRequest;
import fitnest.notif_service.entities.Notif;
import fitnest.notif_service.entities.NotifType;
import fitnest.notif_service.repos.NotifRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;


@Service
public class NotificationStorageService implements INotificationStorageServicee {
    
    private final NotifRepo notificationRepository;

    public NotificationStorageService(NotifRepo notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notif> getNotificationsForRecipient(Long recipientId) {
        try {
            // Fetch the notifications for the recipient from the database
            return notificationRepository.findByRecipient(recipientId);
        } catch (Exception e) {
            // Handle any errors that may occur during data fetching
            throw new RuntimeException("Failed to retrieve notifications", e);
        }
    }

    public void storeNotification(NotifRequest request ) {

        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        Notif notification = new Notif();
        notification.setRecipient(request.getRecipient());
        notification.setType(NotifType.valueOf(request.getType()));
        notification.setContent(request.getContent());
        notification.setTimestamp(localDateTime);
        notification.setToken(request.getToken());
        notificationRepository.save(notification);  // Save the notification to MongoDB
    }
}
