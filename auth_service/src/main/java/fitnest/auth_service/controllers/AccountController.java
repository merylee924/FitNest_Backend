package fitnest.auth_service.controllers;

import fitnest.auth_service.dto.ErrorResponse;
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

}