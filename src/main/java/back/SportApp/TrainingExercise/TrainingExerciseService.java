package back.SportApp.TrainingExercise;

import back.SportApp.Exercise.Exercise;
import back.SportApp.Training.Training;

import java.util.Set;

public interface TrainingExerciseService {
    public Set<Training> getTrainingsFromExercise(Integer exerciseId);
    public Set<Exercise> getExerciseFromTraining(Integer trainingId);
    public void addExerciseTraining(Integer trainingId, Integer exerciseId);

    public void deleteExerciseTraining(Integer trainingId, Integer exerciseId);
}
