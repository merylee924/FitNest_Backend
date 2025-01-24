package org.ilisi.feedback.entities;

import jakarta.persistence.*;
import lombok.*;
import org.ilisi.feedback.dto.ParticipationDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Champ pour stocker l'ID de participation
    @Column(name = "participation_id", nullable = false)
    private Long participationId;

    @Transient
    private ParticipationDTO participationDTO;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "rating")
    private Integer rating;


}
