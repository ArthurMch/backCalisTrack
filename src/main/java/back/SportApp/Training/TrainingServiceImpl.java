package back.SportApp.Training;

import back.SportApp.Exercise.DTO.ExerciseDTO;
import back.SportApp.Exercise.Exercise;
import back.SportApp.Exercise.ExerciseRepository;
import back.SportApp.Training.DTO.TrainingDTO;
import back.SportApp.TrainingExercise.TrainingExercise;
import back.SportApp.TrainingExercise.TrainingExerciseRepository;
import back.SportApp.User.UserService;
import jakarta.transaction.Transactional;
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
    public Training update(Training training) {
        Optional<Training> existingTraining = trainingRepository.findById(training.getId());
        if (existingTraining.isPresent()) {
            Training updateTraining = existingTraining.get();
            updateTraining.setDate(training.getDate());
            updateTraining.setTotalMinutesOfTraining(training.getTotalMinutesOfTraining());
            updateTraining.setTotalMinutesOfRest(training.getTotalMinutesOfRest());
            updateTraining.setNumberOfExercise(training.getNumberOfExercise());
            return trainingRepository.save(updateTraining);
        } else {
            throw new RuntimeException("Wrong or inexistant ID" + training.getId());
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer trainingId) {
        trainingExerciseRepository.deleteByTrainingId(trainingId);
        trainingRepository.deleteById(trainingId);
    }

    public Training toTrainingEntity(TrainingDTO dto) {
        Training training = new Training();
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

    private ExerciseDTO toExerciseDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setSet(exercise.getSet());
        dto.setRep(exercise.getRep());
        dto.setRestTimeInMinutes(exercise.getRestTimeInMinutes());
        return dto;
    }

    private TrainingDTO toTrainingDTO(Training training) {
        final Set<TrainingExercise> relations = trainingExerciseRepository.findByTrainingId(training.getId());
        final Set<ExerciseDTO> exerciseDTOs = relations.stream()
                .map(rel -> toExerciseDTO(rel.getExercise())) // map à ton DTO
                .collect(Collectors.toSet());
        return new TrainingDTO(training, exerciseDTOs);
    }

}
