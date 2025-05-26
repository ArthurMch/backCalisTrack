package back.SportApp.TrainingExercise;

import back.SportApp.Exercise.Exercise;
import back.SportApp.Training.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Integer> {
    List<TrainingExercise> findByExerciseId(Integer id);
    List<TrainingExercise> findByExerciseName(String name);
    Optional<TrainingExercise> findByExerciseNameAndExerciseId(String name, int id);
    Set<TrainingExercise> findByTrainingId(int id);

    void deleteByIdTrainingId(Integer trainingId);
    void deleteByIdExerciseId(Integer exerciseId);

    void deleteByTraining(Training training);
    void deleteByExercise(Exercise exercise);

    @Modifying
    @Query("DELETE FROM TrainingExercise te WHERE te.training.id = :trainingId")
    void deleteByTrainingId(@Param("trainingId") Integer trainingId);
}
