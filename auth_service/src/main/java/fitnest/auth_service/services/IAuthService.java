package fitnest.auth_service.services;


import fitnest.auth_service.dto.AuthenticationRequest;
import fitnest.auth_service.dto.AuthenticationResponse;
import fitnest.auth_service.dto.RegisterRequest;

public interface IAuthService {

    void checkIfUserOrEmailExists(String email, String username);

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void sendPasswordResetToken(String username);

    void resetPassword(String token, String newPassword);
}