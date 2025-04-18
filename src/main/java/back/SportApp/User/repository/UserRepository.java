package back.SportApp.User.repository;
import back.SportApp.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer Id);

    void deleteById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLostPasswordToken(final String token);
}
