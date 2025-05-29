package back.SportApp.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query("SELECT e FROM Exercise e WHERE e.user.id = :userId")
    Set<Exercise> findAllByUserId(@Param("userId") Integer userId);

}
