package fitnest.auth_service.dto;

import lombok.Data;

@Data
public class UpdateUsernameRequest {
    private Long accountId;
    private String username;
}