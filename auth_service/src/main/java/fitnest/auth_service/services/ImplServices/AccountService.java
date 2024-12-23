package fitnest.auth_service.services.ImplServices;

import fitnest.auth_service.Repos.AccountRepo;
import fitnest.auth_service.entities.Account;
import fitnest.auth_service.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepo accountRepo;

    @Override
    public ResponseEntity<Account> findAccountById(Long id) {
        return accountRepo.findById(id)
                .map(ResponseEntity::ok)  // If user is found, return 200 with user
                .orElseGet(() -> ResponseEntity.notFound().build());  // Else return 404
    }

    @Override
    public ResponseEntity<Account> findAccountByUsername(String email) {
        return accountRepo.findByUsername(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Account>> retrieveAllAccounts() {
        List<Account> users = accountRepo.findAll();
        return ResponseEntity.ok(users);
    }
    @Override
    public ResponseEntity<?> updateUsername(Long accountId, String newUsername) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        account.setUsername(newUsername); // Mise à jour du nom d'utilisateur
        accountRepo.save(account); // Sauvegarde dans la base de données

        return ResponseEntity.ok("Username updated successfully.");

    }

    @Override
    public ResponseEntity<?> updateEmail(Long accountId, String newEmail) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));
        account.setEmail(newEmail); // Mise à jour de l'email
        accountRepo.save(account); // Sauvegarde dans la base de données

        return ResponseEntity.ok("Email updated successfully.");

    }
}
