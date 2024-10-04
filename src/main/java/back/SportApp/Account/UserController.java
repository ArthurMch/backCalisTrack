package back.SportApp.Account;


import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @PostMapping("create")
    public Account create(@RequestBody Account account) {
        return accountService.creer(account);
    }

    @GetMapping("read")
    public List<Account> read(@RequestParam(name = "ordreAlpha", required = false) String sortParam) {
        List<Account> accounts = accountService.lire();
        if (sortParam != null && sortParam.equals("ordreAlpha")) {
            accounts.sort(Comparator.comparing(Account::getName));
        }
        return accounts;
    }

    @PutMapping("/update/{id}")
    public Account update(@RequestBody Account account, @PathVariable String id) {
        return accountService.modifier(account);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return accountService.supprimer(id);
    }
}
