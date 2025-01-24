package fitnest.notif_service.controllers;

import fitnest.notif_service.dto.NotifRequest;
import fitnest.notif_service.entities.Notif;
import fitnest.notif_service.services.NotificationStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/notifs")
public class NotifController {

    @Autowired
    NotificationStorageService notificationService ;
    @GetMapping("/get/{recipient}")
    public ResponseEntity<List<Notif>> getNotifications(@PathVariable Long recipient) {
        try {
            List<Notif> notifications = notificationService.getNotificationsForRecipient(recipient);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    @PostMapping("/post")
    public ResponseEntity<Void> storeNotification(@RequestBody NotifRequest request) {
          try {
            System.out.println("Received Request: " + request);
            notificationService.storeNotification(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
