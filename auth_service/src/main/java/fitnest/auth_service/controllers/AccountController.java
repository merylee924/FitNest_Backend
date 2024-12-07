package fitnest.auth_service.controllers;

import fitnest.auth_service.dto.ErrorResponse;
import fitnest.auth_service.dto.UpdateEmailRequest;
import fitnest.auth_service.dto.UpdateUsernameRequest;
import fitnest.auth_service.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService; // Utilisation de l'interface

    // Récupérer le compte par ID
    @GetMapping("/id")
    public ResponseEntity<?> getUserAccountById(@RequestParam Long id) {
        try {
            return accountService.findAccountById(id);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error occurred while fetching account: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);  // Retourner une réponse avec l'objet ErrorResponse
        }
    }

    // Récupérer un compte par le nom d'utilisateur (ici, email)
    @GetMapping("/username")
    public ResponseEntity<?> getAccountByUsername(@RequestParam String email) {
        try {
            return accountService.findAccountByUsername(email);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error occurred while fetching account: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);  // Retourner une réponse avec l'objet ErrorResponse
        }
    }

    // Récupérer tous les comptes
    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        try {
            return accountService.retrieveAllAccounts();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error occurred while fetching accounts: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);  // Retourner une réponse avec l'objet ErrorResponse
        }
    }
    @PutMapping("/update-username")
    public ResponseEntity<?> updateUsername(@RequestBody UpdateUsernameRequest updateRequest) {
        try {
            // Extraire les champs du DTO
            Long accountId = updateRequest.getAccountId();
            String newUsername = updateRequest.getUsername();

            // Appeler le service avec les données extraites
            return accountService.updateUsername(accountId, newUsername);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error occurred while updating username: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @PutMapping("/update-email")
    public ResponseEntity<?> updateEmail(@RequestBody
                                         UpdateEmailRequest updateRequest) {
        try {
            Long accountId = updateRequest.getAccountId();
            String newEmail = updateRequest.getEmail();

            return accountService.updateEmail(accountId, newEmail);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Error occurred while updating email: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}