package fitnest.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthenticationResponse {
    private String token;
    private Long user_id;



    // Getter et Setter pour le message


    // Getter et Setter pour le token (si applicable)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}