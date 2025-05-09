package back.SportApp.Training;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Exercise.Exercise;
import back.SportApp.Exercise.ExerciseController;
import back.SportApp.Training.DTO.TrainingDTO;
import back.SportApp.TrainingExercise.TrainingExerciseService;
import back.SportApp.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    private final TrainingService trainingService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final TrainingExerciseService trainingExerciseService;

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    public TrainingController(TrainingService trainingService, TrainingExerciseService trainingExerciseService, UserService userService) {
        this.trainingService = trainingService;
        this.trainingExerciseService = trainingExerciseService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TrainingDTO trainingDTO) {
        try {
            trainingService.create(trainingDTO, trainingDTO.getExercises());
            return new ResponseEntity<>("Training created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de training", e);
            return new ResponseEntity<>("Training not created", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TrainingDTO> findById(@PathVariable Integer id) {
        try {
            TrainingDTO training = trainingService.findById(id);
            if (training == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(training);
        }catch (Exception e){
            logger.error("Erreur lors de la récupération des entrainements");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<TrainingDTO>> findByUserId(@PathVariable Integer id) {
        try {
            Set<TrainingDTO> trainings = trainingService.findAllByUserId(id);
            if (trainings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(trainings);
        } catch (Exception e){
            logger.error("Erreur lors de la récupération des entrainements");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TrainingDTO>> findAll() {
        try {
            List<TrainingDTO> trainings = trainingService.findAll();
            if (trainings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
           return ResponseEntity.ok(trainings);
        } catch (Exception e){
            logger.error("Erreur lors de la récupération des entrainements");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/")
    public Training update(@RequestBody Training training) {
        return trainingService.update(training);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        trainingService.deleteById(id);
    }

    }






