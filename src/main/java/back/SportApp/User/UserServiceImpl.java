package back.SportApp.User;

import back.SportApp.Auth.DTO.response.ResetPasswordStatus;
import back.SportApp.User.repository.UserPasswordRepository;
import back.SportApp.User.repository.UserRepository;
import back.SportApp.utils.PasswordUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserPasswordRepository userPasswordRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserPasswordRepository userPasswordRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userPasswordRepository = userPasswordRepository;
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

    @Transactional
    @Override
    public String startLostPassword(final String email) {
        final Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isPresent()) {
            final User user = optUser.get();
            final String token = manageLostPasswordToken(user, 15);
            userRepository.save(user);
            return token;

        }
        return null;
    }

    @Override
    public String manageLostPasswordToken(final User user, final int expiration) {
        
        final String token = RandomStringUtils.random(30, true, true);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiration);
        final Date expirationDate = cal.getTime();
        user.setLostPasswordToken(token);
        user.setLostPasswordExpiration(expirationDate);
        return token;
    }

    @Override
    public boolean isValidLostPasswordToken(final String token) {
        final Optional<User> opt = userRepository.findByLostPasswordToken(token);
        if (opt.isPresent()) {
            return opt.get().getLostPasswordExpiration().after(new Date());
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public ResetPasswordStatus resetPassword(final String token, final String password) {
        final Optional<User> opt = userRepository.findByLostPasswordToken(token);
        if (opt.isPresent()) {
            final User user = opt.get();
            final ResetPasswordStatus status = updatePassword(user, password);
            if (status == ResetPasswordStatus.DONE) {
                userRepository.save(user);
            }
            return status;
        } else {
            return ResetPasswordStatus.INCORRECT;
        }
    }

    private ResetPasswordStatus updatePassword(final User user, final String password) {
        if (!PasswordUtils.isPatternRespected(password)) {
            return ResetPasswordStatus.INCORRECT;
        }
        if (alreadyUsedPassword(password, user)) {
            return ResetPasswordStatus.ALREADY;
        }

        if (StringUtils.isNotEmpty(user.getPassword())) {
            final UserPassword userPassword = new UserPassword();
            userPassword.setUserId(user.getId());
            userPassword.setExpiration(new Date());
            userPassword.setPassword(user.getPassword());
            userPasswordRepository.save(userPassword);
        } // ELSE : pas de pwd défini : setup de compte !

        user.setPassword(PasswordUtils.cryptPassword(password));
        user.setLostPasswordExpiration(null);
        user.setLostPasswordToken(null);
        user.setPasswordExpiration(null);// contrôle à implémenter
        return ResetPasswordStatus.DONE;
    }

    private boolean alreadyUsedPassword(final String password, final User user) {
        final String currentUserPassword = user.getPassword();
        if (!PasswordUtils.matchPassword(password, currentUserPassword)) {
            final long userId = user.getId();
            final Collection<UserPassword> passwords = userPasswordRepository
                    .findFirst5ByUserIdOrderByExpirationDesc(userId);
            return passwords.stream().anyMatch(p -> PasswordUtils.matchPassword(password, p.getPassword()));
        } else {
            return true;
        }
    }

    @Override
    public Optional<User> getUserByLostPasswordToken(final String token) {
        return userRepository.findByLostPasswordToken(token);
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
