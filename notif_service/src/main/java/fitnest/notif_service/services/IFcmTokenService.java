package fitnest.notif_service.services;

import fitnest.notif_service.dto.TokenRequest;
import fitnest.notif_service.entities.FcmToken;

import java.util.List;

public interface IFcmTokenService {
    public FcmToken associateTokenWithUser(TokenRequest request  ) ;
    public FcmToken getTokenByUser(Long userId);
    public List<FcmToken> getAll();
}
