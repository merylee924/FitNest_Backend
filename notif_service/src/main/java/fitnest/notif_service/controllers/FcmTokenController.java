package fitnest.notif_service.controllers;

import fitnest.notif_service.dto.TokenRequest;
import fitnest.notif_service.entities.FcmToken;
import fitnest.notif_service.services.FcmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/fcm-token")
public class FcmTokenController {

    @Autowired
    private FcmTokenService fcmTokenService;

    @PostMapping("/associate")
    public FcmToken associateToken(@RequestBody TokenRequest request) {
        return fcmTokenService.associateTokenWithUser(request);
    }

    @GetMapping("/get/{userid}")
    public FcmToken getToken(@PathVariable Long userid) {
        return fcmTokenService.getTokenByUser(userid);
    }
}