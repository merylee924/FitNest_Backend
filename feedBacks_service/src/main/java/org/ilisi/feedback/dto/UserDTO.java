package org.ilisi.feedback.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String firstName;  // Prénom de l'utilisateur
    private String lastName;   // Nom de famille
}
