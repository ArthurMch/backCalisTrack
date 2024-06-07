package back.SportApp.Service;


import back.SportApp.DataBase.Account;
import back.SportApp.Repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements tableService<Account> {

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

    public Account lireById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
           throw new RuntimeException("Account not found with id "+ id) ;
        }
    }
    //  Objet lireById(Objet objet, Integer id);

    @Override
    public Account modifier(Integer id, Account account) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {

            Account updatedAccount = existingAccount.get();
            updatedAccount.setEmail(account.getEmail());
            updatedAccount.setName(account.getName());
            updatedAccount.setPassword(account.getPassword());
            return accountRepository.save(updatedAccount);
        } else {
            throw new RuntimeException("Le compte de l'utilisateur n'a pas été trouvé avec l'identifiant " + id);
        }
    }

    @Override
    public String supprimer(Integer id) {
        accountRepository.deleteById(id);
        return "Compte supprimé avec succès.";
    }
}
