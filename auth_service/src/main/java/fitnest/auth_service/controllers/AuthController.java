package fitnest.auth_service.controllers;

import fitnest.auth_service.dto.*;
import fitnest.auth_service.exceptions.UserAlreadyExistsException;
import fitnest.auth_service.services.IAuthService;
import fitnest.auth_service.services.ImplServices.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authenticationService;

    @Autowired
    private MailService mailService;

    @GetMapping("/send-test-email")
    public ResponseEntity<?> sendTestEmail() {
        String email = "allamouissal007@gmail.com";
        String token = "jhhhh";

        try {
            mailService.sendVerificationEmail(email, token);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Verification email sent successfully");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }
    @PostMapping("/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");

        try {
            mailService.sendVerificationEmail(email, code);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Verification email sent successfully");
            return ResponseEntity.ok(response);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }
    }
    @PostMapping("/check")
    public ResponseEntity<String> checkIfUserOrEmailExists(@RequestBody UserCheckRequest request) {
        try {
            authenticationService.checkIfUserOrEmailExists(request.getEmail(), request.getUsername());
            return ResponseEntity.ok("Email and username are available");
        } catch (UserAlreadyExistsException e) {
            // Retourne un statut 409 Conflict si l'email ou le nom d'utilisateur existe déjà
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse response = authenticationService.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Si une erreur survient, retournez une réponse avec un message d'erreur dans AuthenticationResponse
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    AuthenticationResponse.builder()
                            .token(null)
                            .user_id(null)
                            .build()
            );
        }
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<AuthenticationResponse> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        try {
            authenticationService.sendPasswordResetToken(request.getUsername());
            AuthenticationResponse successResponse = new AuthenticationResponse();
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody PasswordUpdateRequest request) {
        try {
            authenticationService.resetPassword(request.getToken(), request.getNewPassword());
            AuthenticationResponse successResponse = new AuthenticationResponse();
            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            AuthenticationResponse errorResponse = new AuthenticationResponse();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}