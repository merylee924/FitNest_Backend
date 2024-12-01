package fitnest.auth_service.Repos;

import fitnest.auth_service.entities.Account;
import fitnest.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByAccount(Account account);
}
