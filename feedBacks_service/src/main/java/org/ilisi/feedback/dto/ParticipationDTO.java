package org.ilisi.feedback.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationDTO {
    private Long id;  // ID de la participation
    private Long userId;  // ID de l'utilisateur
    private String userName;  // Nom de l'utilisateur
    private String userLastName;  // Prénom de l'utilisateur
    private String userImage;  // URL ou chemin de l'image de l'utilisateur
    private Long eventId;  // ID de l'événement
    private String eventName;  // Nom de l'événement
    private Long organizerId;  // ID de l'organisateur de l'événement
    private LocalDate dateParticipation;  // Date de participation
    private StatusParticipation statusParticipation;  // Statut de la participation
}
