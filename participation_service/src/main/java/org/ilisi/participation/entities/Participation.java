package org.ilisi.participation.entities;
import org.ilisi.participation.model.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Long userId;

    @Transient
    private Event event;
    @Transient
    private User user;

    private LocalDate date_participation;
    private StatusParticipation status_participation;

}
