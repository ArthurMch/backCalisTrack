package back.SportApp.TrainingExercise;


import back.SportApp.Exercise.ExerciseRepository;
import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingRepository;
import back.SportApp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TrainingExerciseServiceImpl {

    @Autowired
    private TrainingExerciseRepository trainingExerciseRepository;
    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;


    // Méthode pour récupérer les trainings d'un exercice
    public Set<Training> getTrainingsForExercise(Integer exerciseId) {
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

