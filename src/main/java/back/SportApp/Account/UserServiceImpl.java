package back.SportApp.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account creer(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> lire() {
        return accountRepository.findAll();
    }

    @Override
    public Account lireById(Long id) {
        Optional<Account> account = accountRepository.findByAccountId(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account not found with id " + id);
        }
    }

    @Override
    public Account modifier(Account account) {
        Optional<Account> existingAccount = accountRepository.findByAccountId(account.getAccountId());
        if (existingAccount.isPresent()) {
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Le compte de l'utilisateur n'a pas été trouvé avec l'identifiant " + account.getAccountId());
        }
    }

    @Override
    public String supprimer(Long id) {
        accountRepository.deleteByAccountId(id);
        return "Compte supprimé avec succès.";
    }
}
