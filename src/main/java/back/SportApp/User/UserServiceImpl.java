package back.SportApp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Account not found with id " + id);
        }
    }

    @Override
    public User update(User user) {
        Optional<User> existingAccount = userRepository.findById(user.getId());
        if (existingAccount.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Le compte de l'utilisateur n'a pas été trouvé avec l'identifiant " + user.getId());
        }
    }

    @Override
    public String deleteById(Integer id) {
        userRepository.deleteById(id);
        return "Compte supprimé avec succès.";
    }
}
