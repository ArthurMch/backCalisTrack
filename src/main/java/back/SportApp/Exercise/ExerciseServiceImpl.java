package back.SportApp.Exercise;
import back.SportApp.Exercise.DTO.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    public ExerciseServiceImpl(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public boolean existsById(Integer id){
        return exerciseRepository.existsById(id);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Set<ExerciseDTO> findAllByUserId(Integer userId) {
        final Set<Exercise> exercises = exerciseRepository.findAllByUserId(userId);
        final Set<ExerciseDTO> exerciseDTOs = new HashSet<>();
        for (Exercise exercise : exercises) {
            final ExerciseDTO dto = exerciseMapper.toDTO(exercise);
            exerciseDTOs.add(dto);
        }
        return exerciseDTOs;
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
            updateExercise.setRestTimeInMinutes(exercise.getRestTimeInMinutes());
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
