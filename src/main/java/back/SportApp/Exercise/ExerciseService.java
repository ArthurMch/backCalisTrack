package back.SportApp.Exercise;

import java.util.List;

public interface ExerciseService {
    public Exercise creer(Exercise exercise);
    public List<Exercise> lire();
    public Exercise lireById(Long id);
    public void supprimer(Long id);
    public void modifier(Long id, Exercise exercise);
}
