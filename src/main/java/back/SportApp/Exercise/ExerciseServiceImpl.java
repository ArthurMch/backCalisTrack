package back.SportApp.Exercise;

import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    private TrainingRepository trainingRepository;

    @Override
    public Exercise create(Exercise exercise) {
        if (!exerciseRepository.existsById(exercise.getId())) {
            return exerciseRepository.save(exercise);
        } else {
            throw new RuntimeException("Training not found");
        }
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findById(Integer id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            return exercise.get();
        } else {
            throw new RuntimeException(("Exercise not found with id "+ id)) ;
        }
    }

    @Override
    public void update(Exercise exercise) {
        final Integer id = exercise.getId();
        Optional<Exercise> existingExercise = exerciseRepository.findById(id);
        if (existingExercise.isPresent()) {
            Exercise updateExercise = existingExercise.get();
            updateExercise.setName(exercise.getName());
            updateExercise.setRep(exercise.getRep());
            updateExercise.setSet(exercise.getSet());
            updateExercise.setTrainings(exercise.getTrainings());
            exerciseRepository.save(updateExercise);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + id);
        }
    }

    @Override
    public void deleteById(Integer id) {
        final Exercise exercise = this.findById(id);
        if(exercise != null) {
            exerciseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + id);
        }
    }
}
