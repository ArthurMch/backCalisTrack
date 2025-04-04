package back.SportApp.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User getUser(final String email) throws UsernameNotFoundException {
        final User user = getUserWithoutException(email);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        } else {
            return user;
        }
    }

    @Override
    @Transactional
    public User getUserWithoutException(final String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public User create(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(Role.USER);
        return userRepository.save(newUser);
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
