package back.SportApp.TrainingExercise;

import back.SportApp.Training.Training;

import java.util.Set;

public interface TrainingExerciseService {
    public Set<Training> getTrainingsFromExercise(Integer exerciseId);
}
