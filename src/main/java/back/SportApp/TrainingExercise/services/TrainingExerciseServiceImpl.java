package back.SportApp.TrainingExercise.services;


import back.SportApp.Exercise.models.Exercise;
import back.SportApp.Exercise.repository.ExerciseRepository;
import back.SportApp.Training.models.Training;
import back.SportApp.Training.repository.TrainingRepository;
import back.SportApp.TrainingExercise.models.TrainingExercise;
import back.SportApp.TrainingExercise.repository.TrainingExerciseRepository;
import back.SportApp.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        TrainingExercise trainingExercise = new TrainingExercise(training,
                exercise);
        trainingExerciseRepository.save(trainingExercise);
    }

    public Set<Exercise> getExerciseFromTraining(Integer trainingId) {
        Set<TrainingExercise> trainingExercises =
                trainingExerciseRepository.findByTrainingId(trainingId);
        if (trainingExercises.isEmpty()) {
            throw new RuntimeException("Training exercise not found");
        }
        return trainingExercises.stream()
                .map(TrainingExercise::getExercise)
                .collect(Collectors.toSet());
    }


    public void deleteExerciseTraining(Integer trainingId, Integer exerciseId) {
        Training training =
                trainingRepository.findById(trainingId).orElseThrow(() -> new RuntimeException("Training not found"));
        Exercise exercise =
                exerciseRepository.findById(exerciseId).orElseThrow(() -> new RuntimeException("Exercise not found"));
        
        trainingExerciseRepository.deleteByTraining(training);
    }

    public Set<Training> getTrainingsFromExercise(Integer exerciseId) {
        // On récupère les TrainingExercise associés à cet exercise
        List<TrainingExercise> trainingExercises =
                trainingExerciseRepository.findByExerciseId(exerciseId);
        Set<Training> trainings = new HashSet<>();
        if (!trainingExercises.isEmpty()) {
            for (TrainingExercise trainingExercise : trainingExercises) {
                trainings.add(trainingExercise.getTraining());
            }
            return trainings;
        } else {
            throw new RuntimeException("Training exercise not found");
        }

    }
}

