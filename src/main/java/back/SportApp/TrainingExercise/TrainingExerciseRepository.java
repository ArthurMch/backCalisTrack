package back.SportApp.TrainingExercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Integer> {
    List<TrainingExercise> findByExerciseId(Integer id);

    void deleteById(TrainingExerciseId id);
}
