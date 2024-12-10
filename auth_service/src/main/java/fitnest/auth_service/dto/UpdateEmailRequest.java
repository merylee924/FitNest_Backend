package fitnest.auth_service.dto;

import lombok.Data;

@Data
public class UpdateEmailRequest {
    private Long accountId;
    private String email;
}
