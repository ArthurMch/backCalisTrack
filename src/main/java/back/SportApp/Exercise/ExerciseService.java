package back.SportApp.Exercise;

import java.util.List;

public interface ExerciseService {
    public Exercise create(Exercise exercise);
    public List<Exercise> findAll();
    public Exercise findById(Long id);
    public void deleteById(Long id);
    public void update(Exercise exercise);
}
