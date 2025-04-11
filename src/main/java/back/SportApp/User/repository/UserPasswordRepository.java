package back.SportApp.User.repository;

import back.SportApp.User.User;
import back.SportApp.User.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    Collection<UserPassword> findFirst5ByUserIdOrderByExpirationDesc(final Long userId);
}