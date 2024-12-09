package fitnest.notif_service.entities;

public enum NotifType {
    EVENT_CREATED,          // A new event has been created.
    EVENT_MODIFIED,         // An existing event has been updated.
    EVENT_CANCELED,         // An event has been canceled.
    PARTICIPATION_CONFIRMED, // A user's participation in an event has been confirmed.
    PARTICIPATION_DECLINED,  // A user's participation request has been declined.
    REMINDER,               // Reminder for an upcoming event.
    EVENT_INVITATION,       // Invitation to join an event.
    PROFILE_UPDATE,         // Notification about profile updates.
}