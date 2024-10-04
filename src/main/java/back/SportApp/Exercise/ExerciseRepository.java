package back.SportApp.Exercise;
import back.SportApp.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findById(Long Id);

    void deleteById(Long id);
}
