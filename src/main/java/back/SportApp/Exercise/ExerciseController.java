package back.SportApp.Exercise;

import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingRepository;
import back.SportApp.Training.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody Exercise exercise) {
        try {
            exerciseService.create(exercise);
            return new ResponseEntity<>("Exercise created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Exercise not created", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Exercise>> findAll() {
        try {
            List<Exercise> exercises = exerciseService.findAll();
            if (exercises.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content si aucune donnée
            }
            return ResponseEntity.ok(exercises); // 200 OK avec les données
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des exercices : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }


    @GetMapping("/{id}")
    public Exercise findById(@PathVariable Integer id) {
        return exerciseService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Exercise exercise) {
        exerciseService.update(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Integer id) {
        try {
            exerciseService.deleteById(id);
            return ResponseEntity.ok("Exercise deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}
