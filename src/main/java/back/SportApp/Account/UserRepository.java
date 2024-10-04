package back.SportApp.Account;

import back.SportApp.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountId(Long accountId);


    void deleteByAccountId(Long id);
}
