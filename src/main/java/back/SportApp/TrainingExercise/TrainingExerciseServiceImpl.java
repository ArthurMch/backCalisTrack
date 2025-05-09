package back.SportApp.TrainingExercise;


import back.SportApp.Exercise.Exercise;
import back.SportApp.Exercise.ExerciseRepository;
import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingRepository;
import back.SportApp.User.repository.UserPasswordRepository;
import back.SportApp.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrainingExerciseServiceImpl implements TrainingExerciseService {

    @Autowired
    private TrainingExerciseRepository trainingExerciseRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;


    public void addExerciseTraining(Integer trainingId, Integer exerciseId) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        TrainingExercise trainingExercise = new TrainingExercise(training, exercise);
        trainingExerciseRepository.save(trainingExercise);
    }

    public void deleteExerciseTraining(Integer trainingId, Integer exerciseId) {
        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new RuntimeException("Training not found"));
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new RuntimeException("Exercise not found"));
        trainingRepository.delete(training);
    }

    public Set<Exercise> getExerciseFromTraining(Integer trainingId) {
        Set<TrainingExercise> trainingExercises = trainingExerciseRepository.findByTrainingId(trainingId);
        Set<Exercise> exercises = new HashSet<>();
        if(!trainingExercises.isEmpty()){
            for (TrainingExercise trainingExercise : trainingExercises) {
                exercises.add(trainingExercise.getExercise());

            }
            return exercises;
        } else {
            throw new RuntimeException("Training exercise not found");
        }

    }

    // Méthode pour récupérer les trainings d'un exercice
    public Set<Training> getTrainingsFromExercise(Integer exerciseId) {
        // On récupère les TrainingExercise associés à cet exercise
        List<TrainingExercise> trainingExercises = trainingExerciseRepository.findByExerciseId(exerciseId);
        Set<Training> trainings = new HashSet<>();
        if(!trainingExercises.isEmpty()) {
            for (TrainingExercise trainingExercise : trainingExercises) {
                trainings.add(trainingExercise.getTraining());
            }
            return trainings;
        } else {
            throw new RuntimeException("Training exercise not found");
        }

    }
}

