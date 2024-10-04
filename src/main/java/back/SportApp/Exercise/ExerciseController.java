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
    public Exercise update(@PathVariable Integer id, @RequestBody Exercise exercise) {
        return exerciseService.modifier(id, exercise);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return exerciseService.supprimer(id);
    }
}
