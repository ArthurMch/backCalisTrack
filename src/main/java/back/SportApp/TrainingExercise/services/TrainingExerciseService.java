package back.SportApp.TrainingExercise.services;

import back.SportApp.Exercise.models.Exercise;
import back.SportApp.Training.models.Training;

import java.util.Set;

public interface TrainingExerciseService {
    public Set<Training> getTrainingsFromExercise(Integer exerciseId);
    public Set<Exercise> getExerciseFromTraining(Integer trainingId);
    public void addExerciseTraining(Integer trainingId, Integer exerciseId);

    public void deleteExerciseTraining(Integer trainingId, Integer exerciseId);
}
