package fitnest.notif_service.entities;

public enum NotifType {
    EVENT_CREATED,          // A new event has been created
    PARTICIPATION_CONFIRMED, // A user's participation in an event has been confirmed.
    PARTICIPATION_DECLINED,  // A user's participation request has been declined.
    REMINDER,               // Reminder for an upcoming event.
    NEW_REGISTRATION,       // Invitation to join an event.
}