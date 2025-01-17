package back.SportApp.Exercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/")
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
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
    public Exercise findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Exercise exercise) {
        exerciseService.update(exercise);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }
}
