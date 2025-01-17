package back.SportApp.Training;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/")
    public Training create(@RequestBody Training training) {
        return trainingService.create(training);
    }

    @GetMapping("/{id}")
    public Training findById(@PathVariable Long id) {
        return trainingService.findById(id);
    }

    @GetMapping("/")
    public List<Training> findAll() {
        return trainingService.findAll();
    }

    @PutMapping("/")
    public Training update(@RequestBody Training training) {
        return trainingService.update(training);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        trainingService.deleteById(id);
    }

    }






