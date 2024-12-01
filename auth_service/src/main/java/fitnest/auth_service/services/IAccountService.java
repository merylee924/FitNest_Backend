package fitnest.auth_service.services;

import fitnest.auth_service.entities.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountService {

    ResponseEntity<Account> findAccountById(Long id);

    ResponseEntity<Account> findAccountByUsername(String email);

    ResponseEntity<List<Account>> retrieveAllAccounts();
}
