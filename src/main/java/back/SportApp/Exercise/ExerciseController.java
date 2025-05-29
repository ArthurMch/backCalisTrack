package back.SportApp.Exercise;

import back.SportApp.DevelopUtils;
import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Training.Training;
import back.SportApp.Training.TrainingService;
import back.SportApp.TrainingExercise.TrainingExerciseService;
import back.SportApp.User.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    @Autowired
    private final TrainingService trainingService;

    @Autowired
    private final TrainingExerciseService trainingExerciseService;

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);


    public ExerciseController(ExerciseService exerciseService, TrainingExerciseService trainingExerciseService, TrainingService trainingService, UserService userService) {
        this.exerciseService = exerciseService;
        this.trainingExerciseService = trainingExerciseService;
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ExerciseDTO exercise) {
        try {
            exerciseService.create(exercise);
            return new ResponseEntity<>("Exercise created", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erreur lors de la creation d'exercise", e);
            return new ResponseEntity<>("Exercise not created", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("createAndAffiliateToTraining/{trainingId}")
    public ResponseEntity<String> createAndAffiliateToTraining(@RequestBody ExerciseDTO exercise, @PathVariable Integer trainingId) {
        try {
            exerciseService.create(exercise);
            final Boolean doTrainingExist = trainingService.existById(trainingId);
            if(doTrainingExist) {
                Training newTraining = new Training();
                newTraining.setUser(DevelopUtils.getUser(userService));
              //  trainingService.create(newTraining, new Set<Exercise>() {
               // });
                trainingExerciseService.addExerciseTraining(newTraining.getId(), exercise.getId());
            } else {
                trainingExerciseService.addExerciseTraining(trainingId, exercise.getId());
            }
            return new ResponseEntity<>("Exercise created", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erreur lors de la creation d'exercise", e);
            return new ResponseEntity<>("Exercise not created", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("AffiliateToTraining/{exerciseId}/{trainingId}")
    public ResponseEntity<String> affiliateToTraining(@PathVariable Integer exerciseId, @PathVariable Integer trainingId){
        try{
            final Boolean doTrainingExist = trainingService.existById(trainingId);
            final Boolean doExerciseExist = exerciseService.existsById(exerciseId);
            if(doTrainingExist && doExerciseExist) {
                trainingExerciseService.addExerciseTraining(trainingId, exerciseId);
                return ResponseEntity.ok("Exercise affiliated");
            } else {
                return new ResponseEntity<>("Exercise not affiliated", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Exercise not affiliated", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ExerciseDTO>> findAll() {
        try {
            List<Exercise> exercises = exerciseService.findAll();
            logger.info("Nombre d'exercices trouvés: {}", exercises.size());
            if (exercises.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            List<ExerciseDTO> exerciseDTOs = exercises.stream()
                    .map(ExerciseDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(exerciseDTOs); // 200 OK avec les données
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des exercises", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 Internal Server Error
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<ExerciseDTO>> findByUserId(@PathVariable Integer id) {
        try {
            Set<ExerciseDTO> exercises = exerciseService.findAllByUserId(id);
            if(exercises.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des exercises", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Integer id) {
        try {
            final Exercise exercise = exerciseService.findById(id);
            if(exercise == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(exercise);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Exercise exercise) {
        try {
            exerciseService.update(exercise);
            return ResponseEntity.ok("Exercise updated");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not updated");
        }
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<String> delete(@PathVariable Integer exerciseId) {
        try {
            exerciseService.deleteById(exerciseId);
            return ResponseEntity.ok("Exercise deleted successfully");
        } catch (RuntimeException e) {
            logger.error("Erreur lors de la suppression de l'exercice", e);

            // Différencier les types d'erreurs
            if (e.getMessage().contains("Cannot delete exercise: it is used")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            } else if (e.getMessage().contains("Wrong or inexistant ID")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur");
            }
        }
    }
}
