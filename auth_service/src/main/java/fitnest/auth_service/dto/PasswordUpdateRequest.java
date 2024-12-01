
// fitnest.auth_service.dto.PasswordResetRequest
package fitnest.auth_service.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private String token;
    private String newPassword;
}
