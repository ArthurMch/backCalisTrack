package back.SportApp.Exercise;

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
    public List<Exercise> findAll() {
        return  exerciseService.findAll();
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
