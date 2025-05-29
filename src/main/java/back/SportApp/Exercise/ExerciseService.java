package back.SportApp.Exercise;

import back.SportApp.Exercise.DTO.ExerciseDTO;

import java.util.List;
import java.util.Set;

public interface ExerciseService {
    public Exercise create(ExerciseDTO exercise);
    public List<Exercise> findAll();
    public Set<ExerciseDTO> findAllByUserId(Integer userId);
    public Exercise findById(Integer id);
    public void deleteById(Integer id);
    public void update(Exercise exercise);
    public boolean existsById(Integer id);
}
