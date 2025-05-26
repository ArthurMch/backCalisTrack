package back.SportApp.Training;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Exercise.Exercise;
import back.SportApp.Exercise.ExerciseMapper;
import back.SportApp.Exercise.ExerciseRepository;
import back.SportApp.Training.DTO.TrainingDTO;
import back.SportApp.TrainingExercise.TrainingExercise;
import back.SportApp.TrainingExercise.TrainingExerciseRepository;
import back.SportApp.User.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TrainingExerciseRepository trainingExerciseRepository;
    @Autowired
    private UserService userService;

    private final ExerciseMapper exerciseMapper;

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    public TrainingServiceImpl(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public void create(TrainingDTO trainingDTO, Set<ExerciseDTO> exercises) {
        final Training training = toTrainingEntity(trainingDTO);
        trainingRepository.save(training);
        for (ExerciseDTO exercise : exercises) {
            final TrainingExercise trainingExercise = new TrainingExercise(training, toExerciseEntity(exercise));
            trainingExerciseRepository.save(trainingExercise);
        }

    }

    @Override
    public List<TrainingDTO> findAll() {
        final List<Training> trainings = trainingRepository.findAll();
        final List<TrainingDTO> dtos = new ArrayList<>();
        for (Training training : trainings) {
            final TrainingDTO dto = toTrainingDTO(training);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public TrainingDTO findById(Integer id) {
        Optional<Training> training = trainingRepository.findById(id);
        if (training.isPresent()) {
            return toTrainingDTO(training.get());
        }else{
            throw new RuntimeException("Wrong id" + id);
        }
    }

    @Override
    public Set<TrainingDTO> findAllByUserId(Integer userId){
        final Set<Training> trainings = trainingRepository.findByTrainingUserId(userId);
        final Set<TrainingDTO> trainingDTOs = new HashSet<>();
        for (Training training : trainings) {
            final TrainingDTO dto = toTrainingDTO(training);
            trainingDTOs.add(dto);
        }
        return trainingDTOs;
    }

    public Boolean existById(Integer id) {
        return trainingRepository.existsById(id);
    }

    @Override
    @Transactional
    public void update(TrainingDTO trainingDTO) {
        Training existingTraining = trainingRepository.findById(trainingDTO.getId())
                .orElseThrow(() -> new RuntimeException("Training with ID " + trainingDTO.getId() + " not found"));

        try {
            // 1. Supprimer les anciennes relations
            trainingExerciseRepository.deleteByIdTrainingId(trainingDTO.getId());

            // 2. Ajouter les nouvelles relations
            Set<ExerciseDTO> exercises = trainingDTO.getExercises();
            if (exercises != null && !exercises.isEmpty()) {
                for (ExerciseDTO exerciseDTO : exercises) {
                    // Récupérer l'exercice complet
                    Exercise exercise = exerciseRepository.findById(exerciseDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Exercise with ID " + exerciseDTO.getId() + " not found"));

                    // Créer la nouvelle relation avec le constructeur
                    TrainingExercise trainingExercise = new TrainingExercise(existingTraining, exercise);

                    trainingExerciseRepository.save(trainingExercise);
                }
            }
            // 3. Mettre à jour le training
            existingTraining.setName(trainingDTO.getName());
            existingTraining.setDate(trainingDTO.getDate());
            existingTraining.setTotalMinutesOfTraining(trainingDTO.getTotalMinutesOfTraining());
            existingTraining.setTotalMinutesOfRest(trainingDTO.getTotalMinutesOfRest());
            existingTraining.setNumberOfExercise(exercises != null ? exercises.size() : 0);

            trainingRepository.save(existingTraining);

        } catch (RuntimeException e) {
            // Re-lancer les RuntimeException (comme les not found)
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update training: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer trainingId) {
        final Set<TrainingExercise> trainingExercises = trainingExerciseRepository.findByTrainingId(trainingId);
        trainingExerciseRepository.deleteAll(trainingExercises);
        trainingRepository.deleteById(trainingId);
    }

    public Training toTrainingEntity(TrainingDTO dto) {
        Training training = new Training();
        training.setName(dto.getName());
        training.setDate(dto.getDate());
        training.setNumberOfExercise(dto.getNumberOfExercise());
        training.setTotalMinutesOfRest(dto.getTotalMinutesOfRest());
        training.setTotalMinutesOfTraining(dto.getTotalMinutesOfTraining());
        training.setTrainingUser(userService.findById(dto.getTrainingUser()));
        return training;
    }

    private Set<Exercise> toSetExerciseEntity(Set<ExerciseDTO> dtos) {
        Set<Exercise> exercises = new HashSet<>();
        for (ExerciseDTO dto : dtos) {
            final Exercise exercise = toExerciseEntity(dto);
            exercises.add(exercise);
        }
        return exercises;
    }

    private Exercise toExerciseEntity(ExerciseDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setName(dto.getName());
        exercise.setSet(dto.getSet());
        exercise.setRep(dto.getRep());
        exercise.setRestTimeInMinutes(dto.getRestTimeInMinutes());
        return exercise;
    }

    private TrainingDTO toTrainingDTO(Training training) {
        final Set<TrainingExercise> relations = trainingExerciseRepository.findByTrainingId(training.getId());
        final Set<ExerciseDTO> exerciseDTOs = relations.stream()
                .map(rel -> exerciseMapper.toDTO(rel.getExercise())) // map à ton DTO
                .collect(Collectors.toSet());
        return new TrainingDTO(training, exerciseDTOs);
    }

}
