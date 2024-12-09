package fitnest.notif_service.services;

import fitnest.notif_service.dto.TokenRequest;
import fitnest.notif_service.entities.FcmToken;
import fitnest.notif_service.repos.FcmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FcmTokenService {

    @Autowired
    private FcmTokenRepository fcmTokenRepository;

    public FcmToken associateTokenWithUser(TokenRequest request  ) {
        FcmToken existingToken = getTokenByUser(request.getUserid());
        if (existingToken != null) {
            existingToken.setToken(request.getToken());
        } else {
            existingToken = new FcmToken(request.getToken(), request.getUserid());
        }

        return fcmTokenRepository.save(existingToken);
    }

    public FcmToken getTokenByUser(Long userId) {
        return fcmTokenRepository.findByUserid(userId);
    }
}
