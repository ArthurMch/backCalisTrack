package back.SportApp.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

}
