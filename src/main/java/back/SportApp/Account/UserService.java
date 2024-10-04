package back.SportApp.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account creer(Account account);
    List<Account> lire();
    Account lireById(Long id);
    Account modifier(Account account);
    String supprimer(Long id);
}
