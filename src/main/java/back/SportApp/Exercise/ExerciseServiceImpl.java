package back.SportApp.Exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise creer(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> lire() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise lireById(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {

            return exercise.get();
        } else {
            throw new RuntimeException(("Exercise not found with id "+ id)) ;
        }
    }

    @Override
    public void modifier(Long id, Exercise exercise) {
        Optional<Exercise> existingExercise = exerciseRepository.findById(id);
        if (existingExercise.isPresent()) {
            Exercise updateExercise = existingExercise.get();
            updateExercise.setName(exercise.getName());
            updateExercise.setRep(exercise.getRep());
            updateExercise.setSet(exercise.getSet());
            updateExercise.setTraining(exercise.getTraining());
            exerciseRepository.save(updateExercise);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + id);
        }
    }

    @Override
    public void supprimer(Long id) {
        exerciseRepository.deleteById(id);
    }

}
