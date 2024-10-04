package back.SportApp.Exercise;

import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @PostMapping("create")
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.creer(exercise);
    }

    @GetMapping("read")
    public List<Exercise> read(@RequestParam(name = "ordreAlpha", required = false)String sortParam) {
        List<Exercise> exercises = exerciseService.lire();
        if (sortParam != null && sortParam.equals("ordreAlpha")) {
            exercises.sort(Comparator.comparing(Exercise::getName));
        }
        return exercises;
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Exercise exercise) {
        exerciseService.modifier(id, exercise);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.supprimer(id);
    }
}
