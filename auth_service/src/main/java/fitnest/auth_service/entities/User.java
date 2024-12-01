package fitnest.auth_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long phoneNumber;

    // Stocker la date de naissance comme jour, mois, année
    private LocalDate dateBirth;
    private String gender;
    private String description;

    @Column(name = "id_face",columnDefinition = "TEXT")
    private String idFace;  // Changer en String
    @Column(name = "id_back",columnDefinition = "TEXT")
    private String idBack;  // Changer en String
    @Column(name = "profile_picture",columnDefinition = "TEXT")
    private String profilePicture;  // Changer en String

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", unique = true)
    private Account account;

    @ElementCollection
    @CollectionTable(name = "interests", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "interest")
    private List<String> interests;
}