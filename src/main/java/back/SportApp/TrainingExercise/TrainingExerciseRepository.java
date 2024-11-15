package back.SportApp.TrainingExercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Integer> {
    Optional<TrainingExercise> findById(TrainingExerciseId id);

    void deleteById(TrainingExerciseId id);
}
