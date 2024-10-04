package back.SportApp.User;


import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {

    private UserService accountService;

    @PostMapping("create")
    public User create(@RequestBody User user) {
        return accountService.creer(user);
    }

    @GetMapping("read")
    public List<User> read(@RequestParam(name = "ordreAlpha", required = false) String sortParam) {
        List<User> accounts = accountService.lire();
        if (sortParam != null && sortParam.equals("ordreAlpha")) {
            accounts.sort(Comparator.comparing(User::getName));
        }
        return accounts;
    }

    @PutMapping("/update/{id}")
    public User update(@RequestBody User user, @PathVariable String id) {
        return accountService.modifier(user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return accountService.supprimer(id);
    }
}
